class RideSharingSystem {

    Queue<Integer> drivers;
    Riders riders; 

    public RideSharingSystem() {
        drivers = new LinkedList<>();
        riders = new Riders();
    }
    
    public void addRider(int riderId) {
        riders.add(riderId);
    }
    
    public void addDriver(int driverId) {
        drivers.offer(driverId);
    }
    
    public int[] matchDriverWithRider() {
        if (drivers.isEmpty() || riders.isEmpty()) return new int[]{-1, -1};
        return new int[]{drivers.poll(), riders.getAndDelete()};
    }
    
    public void cancelRider(int riderId) {
        riders.delete(riderId);
    }
}

class Riders {
    Queue<Integer> queue;
    Set<Integer> set;
    public Riders() {
        queue = new LinkedList<>();
        set = new HashSet<>();
    }

    public int getAndDelete() {
        while (!queue.isEmpty() && !set.contains(queue.peek())) queue.poll();
        if (queue.isEmpty()) return -1;
        int id = queue.poll();
        set.remove(id);
        return id;
    }

    public void delete(int id) {
        set.remove(id);
    }

    public void add(int id) {
        set.add(id);
        queue.offer(id);
    }

    public boolean isEmpty() {
        return set.isEmpty();
    }
}

/**
 * Your RideSharingSystem object will be instantiated and called as such:
 * RideSharingSystem obj = new RideSharingSystem();
 * obj.addRider(riderId);
 * obj.addDriver(driverId);
 * int[] param_3 = obj.matchDriverWithRider();
 * obj.cancelRider(riderId);
 */