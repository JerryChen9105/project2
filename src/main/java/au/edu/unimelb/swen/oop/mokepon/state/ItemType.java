package au.edu.unimelb.swen.oop.mokepon.state;

import java.util.Arrays;

public enum ItemType {
    UNKNOWN(-1), HEALTH(0), COFFEE(1), POISON(2);

    private final int itemCode;

    private static final ItemType[] REAL_ITEMS = Arrays.stream(values()).filter(ItemType::isReal).toArray(ItemType[]::new);

    ItemType(int itemCode) {
        this.itemCode = itemCode;
    }

    public int getItemCode() {
        return itemCode;
    }

    public static ItemType getItemType(int itemCode) {
        for (ItemType itemType : values()) {
            if (itemType.itemCode == itemCode) return itemType;
        }
        return UNKNOWN;
    }


    public boolean isReal() {
        return this != UNKNOWN;
    }
    public static ItemType[] realValues() {
        return REAL_ITEMS;
    }
}

