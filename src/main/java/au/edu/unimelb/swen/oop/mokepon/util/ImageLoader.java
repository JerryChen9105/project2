package au.edu.unimelb.swen.oop.mokepon.util;

import javafx.scene.image.Image;

public class ImageLoader {

    public final static String scrollURL = "/assets/scroll.png";


    public static Image load(String url, double width, double height) {
        if (url == null || url.isBlank()) return null;
        try {
            return new Image(url, width, height, true, false);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }


    public static Image load(String url, double width, double height, boolean preserveRatio) {
        if (url == null || url.isBlank()) return null;
        try {
            return new Image(url, width, height, preserveRatio, false);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

}
