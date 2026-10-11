class AuctionSystem {
    private UserState userState;
    private ItemState itemState;

    public AuctionSystem() {
        userState = new UserState();
        itemState = new ItemState();
    }
    
    public void addBid(int userId, int itemId, int bidAmount) {
        updateBid(userId, itemId, bidAmount);
    }
    
    public void updateBid(int userId, int itemId, int newAmount) {
        userState.updateBid(userId, itemId, newAmount);
        itemState.updateBid(userId, itemId, newAmount);
    }
    
    public void removeBid(int userId, int itemId) {
        userState.removeBid(userId, itemId);
    }
    
    public int getHighestBidder(int itemId) {
        return itemState.getValidBid(itemId, userState);
    }
}

class UserState {
    private Map<Long, Integer> userBids;
    private static final int SHIFT = 16;

    public UserState() {
        userBids = new HashMap<>();
    }

    public void updateBid(int userId, int itemId, int bidAmount) {
        userBids.put(toKey(userId, itemId), bidAmount);
    }

    public void removeBid(int userId, int itemId) {
        userBids.remove(toKey(userId, itemId));
    }

    public boolean isValid(int userId, int itemId, int amount) {
        return userBids.getOrDefault(toKey(userId, itemId), -1) == amount;
    }

    private long toKey(int userId, int itemId) {
        return ((long)userId << SHIFT) | itemId;
    }
}

class ItemState {
    private PriorityQueue<int[]>[] itemBids;
    private int ITEMIDRANGE = 50001;

    public ItemState() {
        itemBids = new PriorityQueue[ITEMIDRANGE];
    }

    public void updateBid(int userId, int itemId, int bidAmount) {
        initItem(itemId);
        itemBids[itemId].offer(new int[]{bidAmount, userId});
    }

    public int getValidBid(int itemId, UserState userState) {
        PriorityQueue<int[]> bids = itemBids[itemId];
        if (bids == null) return -1;
        while (!bids.isEmpty()) {
            int[] bid = bids.peek();
            int userId = bid[1];
            int amount = bid[0];
            if (userState.isValid(userId, itemId, amount)) return userId;
            else bids.poll();
        }
        return -1;
    }

    private void initItem(int itemId) {
        if (itemBids[itemId] == null) {
            itemBids[itemId] = new PriorityQueue<>(
                (a, b) -> {
                    if (b[0] != a[0]) {
                        return Integer.compare(b[0], a[0]);
                    } else return Integer.compare(b[1], a[1]);
                }
            );
        }
    }
}