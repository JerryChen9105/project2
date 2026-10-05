package au.edu.unimelb.swen.oop.mokepon.data;

import javafx.scene.image.Image;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Properties;

public class ItemImgLoader extends GameDataLoader<String, Image> {


    private LinkedHashMap<String, Image> itemImgMap = new LinkedHashMap<>();

    private static final int ITEMLIMIT = 6;

    public ItemImgLoader(Properties gameData) {
        super(gameData);
        String propertyPrefix = "map.items.";
        int itemCount = 0;
        while (itemCount < ITEMLIMIT) {
            try {
                String type = gameData.getProperty(propertyPrefix + itemCount + "type");
                String url = gameData.getProperty("item." + type.toLowerCase(Locale.ROOT));
                itemImgMap.put(type, new Image(url, 32, 32, true, false));
                itemCount++;
            } catch (Exception e) {
                break;
            }
        }
    }


    @Override
    public Image load(String obj) {
        return itemImgMap.get(obj.toUpperCase(Locale.ROOT));
    }

    @Override
    public LinkedHashMap<String, Image> loadAll() {
        return itemImgMap;
    }

}
