package au.edu.unimelb.swen.oop.mokepon.util.overworld;

import au.edu.unimelb.swen.oop.mokepon.data.GameData;
import au.edu.unimelb.swen.oop.mokepon.driven.overworld.Coordinate;
import au.edu.unimelb.swen.oop.mokepon.util.ImageLoader;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class ExitInfoLoader {

    private static final String prefix = "map.exit";

    public static Coordinate getExitCoordinate() {
        String coordStr = GameData.getGameData().getProperty(prefix + ".location", "0,0");
        return CoordinateLoader.getCoordinate(coordStr);
    }

    public static ImageView getExitImgView() {
        String url = GameData.getGameData().getProperty(prefix + ".image", null);
        Image exitImg = ImageLoader.load(url, 32, 32, true);

        String colorStr = GameData.getGameData().getProperty(prefix + ".colour", "white");
        Color exitColor = parseColor(colorStr);

        ImageView iv = new ImageView(exitImg);
        if (exitColor != null && !Color.WHITE.equals(exitColor)) {
            Light.Distant light = new Light.Distant();
            light.setColor(exitColor);
            light.setAzimuth(-90);

            Lighting lighting = new Lighting(light);
            lighting.setSurfaceScale(0);
            lighting.setDiffuseConstant(1.0);
            iv.setEffect(lighting);
        }
        return iv;
    }

    private static Color parseColor(String s) {
        if (s == null || s.isBlank()) return Color.WHITE;
        try {
            return Color.web(s.trim());
        } catch (IllegalArgumentException e) {
            System.err.println("Unknown colour: " + s + ", fallback to WHITE");
            return Color.WHITE;
        }
    }

}
