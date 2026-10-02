package au.edu.unimelb.swen.oop.mokepon.model;


import au.edu.unimelb.swen.oop.mokepon.util.ImageLoader;
import javafx.scene.image.Image;

import java.util.Locale;

public enum MokeponType {
    NORMAL, FIRE, ICE, WATER, LIGHTNING;

    private static final String TYPEIMGPREFIX = "/assets/sprite_";

    public static Image getTypeImg(MokeponType mokeponType) {
        if (mokeponType == null) return null;
        return ImageLoader.load(TYPEIMGPREFIX + mokeponType.toString().toLowerCase(Locale.ROOT) + ".png", 32, 32);
    }

}
