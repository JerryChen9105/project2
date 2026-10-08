package au.edu.unimelb.swen.oop.mokepon.util;

import au.edu.unimelb.swen.oop.mokepon.driven.overworld.TileType;
import javafx.scene.image.Image;

import java.util.HashMap;

public class TileImgLoader {

    private static HashMap<Character, Image> imgMap = new HashMap<>();

    private static final String urlPrefix = "assets/tile_";
    private static final String urlSuffix = ".png";

    public static Image getTileImg(char c) {
        TileType tileType = TileType.getTileType(c);
        if (tileType.getTileChar() == '\0' || tileType.getTileChar() == '?') return null;
        return imgMap.computeIfAbsent(c, _ -> ImageLoader.load(urlPrefix + tileType.name() + urlSuffix, 32, 32, true));
    }

}
