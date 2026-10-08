package au.edu.unimelb.swen.oop.mokepon.util.overworld;

import au.edu.unimelb.swen.oop.mokepon.data.GameData;
import au.edu.unimelb.swen.oop.mokepon.driven.overworld.Coordinate;
import au.edu.unimelb.swen.oop.mokepon.util.ImageLoader;
import javafx.scene.image.ImageView;

public class PlayerInfoLoader {

    private static final String playerURLPrefix = "map.player";

    public static ImageView getImgView() {
        return new ImageView(ImageLoader.load(
                GameData.getGameData().getProperty(playerURLPrefix + ".image", "assets/sprite_light.png"),
                32, 32, true
        ));
    }

    public static Coordinate getStartingCoordinate() {
        String coordStr = GameData.getGameData().getProperty(playerURLPrefix + ".startingPos", "0,0");
        return CoordinateLoader.getCoordinate(coordStr);
    }

}
