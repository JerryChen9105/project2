package au.edu.unimelb.swen.oop.mokepon.util;

import au.edu.unimelb.swen.oop.mokepon.data.ItemImgLoader;
import au.edu.unimelb.swen.oop.mokepon.state.ItemType;
import javafx.scene.image.Image;

public class ItemImageLoader {

    private static ItemImgLoader itemImgLoaderInner;

    public static void setItemLoader(ItemImgLoader itemImgLoader) {
        itemImgLoaderInner = itemImgLoader;
    }


    public static Image getItemImg(ItemType itemType) {
        if (itemType == null) return null;
        return itemImgLoaderInner.load(itemType.name());
    }

}
