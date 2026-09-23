package au.edu.unimelb.swen.oop.mokepon.UIElement.Utils;

import javafx.scene.text.Font;

import java.io.InputStream;

public class GotischFont {

    private final static String url = "/assets/GrenzeGotisch-Black.ttf";
    public final static String gotischFontError = "Could not load GrenzeGotisch-Black.ttf";


    public static Font createGotischFont(int size, Object type) {
        Font txtFont = new Font(size);
        try (InputStream inputFile = type.getClass().getResourceAsStream(url)) {
            txtFont = Font.loadFont(inputFile, size);
        } catch (Exception expt) {
            expt.printStackTrace();
        }

        if (txtFont == null) {
            System.err.println(gotischFontError);
            return null;
        }
        return txtFont;
    }

    private GotischFont()
    {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

}
