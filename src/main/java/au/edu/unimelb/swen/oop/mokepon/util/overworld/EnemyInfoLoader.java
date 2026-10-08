package au.edu.unimelb.swen.oop.mokepon.util.overworld;

import au.edu.unimelb.swen.oop.mokepon.data.GameData;
import au.edu.unimelb.swen.oop.mokepon.driven.overworld.Coordinate;
import au.edu.unimelb.swen.oop.mokepon.driven.overworld.EnemyType;
import au.edu.unimelb.swen.oop.mokepon.util.ImageLoader;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.util.LinkedHashMap;
import java.util.Properties;

public class EnemyInfoLoader {

    private static final String PREFIX = "map.mokepon";


    private static String keyOf(EnemyType type, String field) {
        return PREFIX + type.name() + "." + field;
    }

    public static ImageView getEnemyImageView(EnemyType enemyType) {
        Properties props = GameData.getGameData();


        String imgPath = props.getProperty(keyOf(enemyType, "image"));
        Image enemyImg = ImageLoader.load(imgPath, 32, 32, true);

        String colorStr = props.getProperty(keyOf(enemyType, "colour"), "white");
        Color enemyColor = parseColor(colorStr);

        ImageView iv = new ImageView(enemyImg);
        if (enemyColor != null && !Color.WHITE.equals(enemyColor)) {
            Light.Distant light = new Light.Distant();
            light.setColor(enemyColor);
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

    public static LinkedHashMap<Coordinate, EnemyType> getCoordinateEnemyMap() {
        Properties props = GameData.getGameData();
        LinkedHashMap<Coordinate, EnemyType> map = new LinkedHashMap<>();

        for (EnemyType enemyType : EnemyType.values()) {
            for (int i = 0; i < 4; i++) {
                String key = PREFIX + enemyType.name() + "." + i;
                String coordStr = props.getProperty(key);
                if (coordStr == null) break;
                map.put(CoordinateLoader.getCoordinate(coordStr), enemyType);
            }
        }
        return map;
    }
}