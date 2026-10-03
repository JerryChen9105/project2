package au.edu.unimelb.swen.oop.mokepon.state;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.LinkedHashMap;

public class Inventory {

    private LinkedHashMap<ItemType, IntegerProperty> itemMap = new LinkedHashMap<>();


    public Inventory() {
        for (ItemType itemType : ItemType.values()) {
            itemMap.put(itemType, new SimpleIntegerProperty(0));
        }
    }


    public LinkedHashMap<ItemType, IntegerProperty> getItemMap() {
        return itemMap;
    }

    public void add(ItemType itemType) {
        IntegerProperty itemNum = itemMap.get(itemType);
        itemNum.setValue(itemNum.getValue() + 1);
    }

    public int getCount(ItemType itemType) {
        return itemMap.get(itemType).getValue();
    }

    public boolean use(ItemType itemType) {
        IntegerProperty itemNum = itemMap.get(itemType);
        if (itemNum.getValue() <= 0) return false;

        itemNum.setValue(itemNum.getValue() - 1);
        return true;
    }

    public void clear() {
        itemMap.values().forEach(item -> {
            item.setValue(0);
        });
    }

}
