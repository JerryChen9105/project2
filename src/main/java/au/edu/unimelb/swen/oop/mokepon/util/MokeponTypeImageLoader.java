package au.edu.unimelb.swen.oop.mokepon.util;

import au.edu.unimelb.swen.oop.mokepon.driven.model.MokeponType;
import javafx.scene.image.Image;

import java.util.Locale;

public class MokeponTypeImageLoader {

    private static final String TYPEIMGPREFIX = "/assets/sprite_";

    public static Image getTypeImg(MokeponType mokeponType) {
        if (mokeponType == null) return null;
        return ImageLoader.load(TYPEIMGPREFIX + mokeponType.toString().toLowerCase(Locale.ROOT) + ".png", 32, 32);
    }

    /*private static final EnumMap<MokeponType, Image> imageCache = new EnumMap<>(MokeponType.class);

    public static Image getTypeImg(MokeponType mokeponType) {
        if (mokeponType == null) return null;

        return imageCache.computeIfAbsent(mokeponType, type ->
                ImageLoader.load(TYPEIMGPREFIX + type.toString().toLowerCase(Locale.ROOT) + ".png", 32, 32)
        );
    }*/

}
