package au.edu.unimelb.swen.oop.mokepon.state;

import au.edu.unimelb.swen.oop.mokepon.data.ItemLoader;
import javafx.scene.image.Image;

public enum ItemType {
    HEAL, COFFEE, POISON;

    private static ItemLoader itemLoaderInner;


    public static void setItemLoader(ItemLoader itemLoader) {
        itemLoaderInner = itemLoader;
    }


    public static Image getItemImg(ItemType itemType) {
        if (itemType == null) return null;
        return itemLoaderInner.load(itemType.name());
    }

}
