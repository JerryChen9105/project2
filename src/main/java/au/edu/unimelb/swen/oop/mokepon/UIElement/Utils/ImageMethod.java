package au.edu.unimelb.swen.oop.mokepon.UIElement.Utils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageMethod {

    public static Image createImage(String url, double width, double height, boolean preserveRatio)
    {
        if (url == null || url.isEmpty()) return null;

        Image image = new Image(url, width, height, preserveRatio, false);
        return image;
    }

    public static ImageView createImageView(Image image)
    {
        if (image == null) return null;

        ImageView imageView = new ImageView(image);
        return imageView;
    }


    public static ImageView createImageView(String url, double width, double height, boolean preserveRatio)
    {
        if (url == null || url.isEmpty()) return null;

        Image image = createImage(url, width, height, preserveRatio);
        ImageView imageView = createImageView(image);
        return imageView;
    }

    public static void setLocation(ImageView imageView, double x, double y)
    {
        imageView.setX(x);
        imageView.setY(y);
    }

    private ImageMethod()
    {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

}
