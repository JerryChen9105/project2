package au.edu.unimelb.swen.oop.mokepon.util;

import javafx.scene.text.Font;

import java.io.InputStream;
import java.util.HashMap;
import java.util.function.Function;

public class FontLoader {

    private static HashMap<Integer, Font> fontCacheMap = new HashMap<>();

    private final static String GOSTISCH_FONT_URL = "/assets/GrenzeGotisch-Black.ttf";


    public static Font getGostischFont(int size) {

        Function<Integer, Font> fontGenerator = fontSize -> {
            Font resultFont = new Font(fontSize);
            try (InputStream inputFile = FontLoader.class.getResourceAsStream(GOSTISCH_FONT_URL)) {
                resultFont = Font.loadFont(inputFile, fontSize);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                return resultFont;
            }
        };

        return fontCacheMap.computeIfAbsent(size, fontGenerator);
    }

}
