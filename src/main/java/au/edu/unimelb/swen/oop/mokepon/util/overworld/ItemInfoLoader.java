package au.edu.unimelb.swen.oop.mokepon.util.overworld;

import au.edu.unimelb.swen.oop.mokepon.data.GameData;
import au.edu.unimelb.swen.oop.mokepon.driven.overworld.Coordinate;
import au.edu.unimelb.swen.oop.mokepon.state.ItemType;
import au.edu.unimelb.swen.oop.mokepon.util.ImageLoader;
import javafx.scene.image.ImageView;

import java.util.LinkedHashMap;

public class ItemInfoLoader {

    private static final String itemURLPrefix = "map.items";

    public static ImageView getItemImgView() {
        String url = GameData.getGameData().getProperty(itemURLPrefix + ".image", null);
        return new ImageView(
                ImageLoader.load(url, 32, 32, true)
        );
    }

    public static LinkedHashMap<Coordinate, ItemType> getCoordinateItemMap() {
        LinkedHashMap<Coordinate, ItemType> map = new LinkedHashMap<>();
        for (int i = 0; i < 3; i++) {
            String itemTypeStr = GameData.getGameData().getProperty(itemURLPrefix + "." + i + ".type", "UNKNOWN");
            ItemType itemType = ItemType.valueOf(itemTypeStr);
            String itemCoordStr = GameData.getGameData().getProperty(itemURLPrefix + "." + i + ".location", "0,0");
            Coordinate itemCoordnate = CoordinateLoader.getCoordinate(itemCoordStr);
            map.put(itemCoordnate, itemType);
        }
        return map;
    }

}
