# Map System · Development Notes

记录 **map-system 项目开发过程中遇到的问题、原因与解决方案**。  
重点是工程实践，而不是 API 细节。

---

# 1 数据入口 Pipeline 设计

## Problem
MQTT JSON 消息字段与内部 `AisMessage` 模型不一致。

例如：

```
JSON:   LON / LAT / MMSI
Domain: longitude / latitude / mmsi
```

直接反序列化会导致：

- 字段不匹配
- 类型转换困难
- Domain 被外部格式污染

---

## Solution

采用 **DTO → Mapper → Domain** 的数据入口模式。

```
MQTT JSON
     ↓
DTO (transport model)
     ↓
Mapper
     ↓
Domain (business model)
```

示例结构：

```
mqtt/
   MqttAisDto

mapper/
   AisMessageMapper

domain/
   AisMessage
```

---

## DTO 设计原则

DTO **完全对齐 payload**。

```java
public class MqttAisDto {

    @JsonProperty("MMSI")
    private String mmsi;

    @JsonProperty("LON")
    private double lon;

    @JsonProperty("LAT")
    private double lat;
}
```

特点：

- 不包含业务逻辑
- 字段名对齐 JSON
- 只负责承载外部数据

---

## Mapper 负责转换

```java
@Component
public class AisMessageMapper {

    public AisMessage toDomain(MqttAisDto dto) {

        return AisMessage.builder()
                .mmsi(Long.parseLong(dto.getMmsi()))
                .longitude(dto.getLon())
                .latitude(dto.getLat())
                .build();
    }
}
```

Mapper 负责：

- 字段重命名
- 类型转换
- 单位转换
- 异常值处理

---

# 2 JSON 解析

使用 Jackson `ObjectMapper`：

```java
MqttAisDto dto = objectMapper.readValue(json, MqttAisDto.class);
```

解析流程：

```
JSON
  ↓
DTO
  ↓
Mapper
  ↓
Domain
```

---

## readValue vs readTree

| 方法 | 用途 |
|-----|------|
readValue | JSON → Java对象 |
readTree | JSON → JsonNode |

选择原则：

```
JSON结构稳定 → DTO
JSON结构未知 → JsonNode
```

AIS 数据属于 **稳定结构**，适合 DTO。

---

# 3 Spring 依赖注入模式

## Problem
Bean 依赖注入混乱，例如：

- 字段注入
- static 方法
- Bean 注入失败

---

## 推荐模式

使用 **构造器注入**。

```java
@Component
public class AisMessageListener implements MqttCallback {

    private final ObjectMapper objectMapper;
    private final AisMessageMapper mapper;

    public AisMessageListener(ObjectMapper objectMapper,
                              AisMessageMapper mapper) {
        this.objectMapper = objectMapper;
        this.mapper = mapper;
    }
}
```

原则：

```
Spring Bean → 构造器注入
字段 → final
避免 static
```

优点：

- 依赖清晰
- 不可变对象
- 易于测试

---

# 4 ObjectMapper Bean 问题

## Problem

启动时报错：

```
required a bean of type 'ObjectMapper' that could not be found
```

---

## Cause

项目未引入 `spring-boot-starter-json`，  
Spring 没有自动创建 `ObjectMapper` Bean。

---

## Solution

手动注册 Bean：

```java
@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
```

---

# 5 日志设计（Logging Strategy）

## Problem

开启 DEBUG 后日志刷屏：

```
CommsReceiver : network read message
```

原因：

```
logging.level.root=DEBUG
```

导致所有第三方库输出 debug 日志。

---

## Solution

只开启自己代码的 DEBUG。

```
logging.level.root=INFO
logging.level.com.whut.map=DEBUG
logging.level.org.eclipse.paho=INFO
```

---

## 日志等级策略

| 等级 | 用途 |
|-----|------|
DEBUG | 高频调试信息 |
INFO | 系统状态 |
WARN | 可恢复问题 |
ERROR | 系统错误 |

示例：

```java
log.debug("AIS {}", aisMessage);
log.info("MQTT subscribed {}", topic);
log.error("Failed to parse AIS", e);
```

---

## 高频数据日志规则

AIS 属于 **高频流数据**：

```
几十 ~ 几百条 / 秒
```

因此：

```
AIS 数据日志 → DEBUG
系统事件 → INFO
```

避免：

```
每条数据使用 INFO
```

否则可能导致：

- 日志 IO 瓶颈
- 磁盘快速膨胀

---

# 6 当前数据入口 Pipeline

当前系统的数据流：

```
MQTT
 ↓
Listener
 ↓
DTO
 ↓
Mapper
 ↓
Domain
 ↓
Engine (后续)
```

未来将扩展：

```
Domain
 ↓
Risk Engine (CPA/TCPA)
 ↓
WebSocket Push
```

---

# Core Takeaways

开发过程中需要记住的是 **工程模式**：

```
DTO → Mapper → Domain
Constructor Injection
日志分级策略
```

而不是：

```
具体 API
框架方法
注解细节
```

API 可以随时查文档。