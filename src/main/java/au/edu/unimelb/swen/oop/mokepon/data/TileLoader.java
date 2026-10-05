package au.edu.unimelb.swen.oop.mokepon.data;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Properties;

public class TileLoader extends GameDataLoader<Integer, ArrayList<Character>> {


    private LinkedHashMap<Integer, ArrayList<Character>> tileMap = new LinkedHashMap<>();

    private static final int MAXTILECOUNT = 20;


    public TileLoader(Properties gameData) {
        super(gameData);
        String propertyPrefix;
        int tileCount = 0;
        while (tileCount < MAXTILECOUNT) {
            propertyPrefix = "map.tiles." + tileCount;
            try {
                String tileStr = gameData.getProperty(propertyPrefix);
                ArrayList<Character> charList = new ArrayList<>();
                for (char c : tileStr.toCharArray()) {
                    charList.add(c);
                }
                tileMap.put(tileCount, charList);
                tileCount++;
            } catch (Exception e) {
                break;
            }
        }
    }

    @Override
    public ArrayList<Character> load(Integer obj) {
        return tileMap.get(obj);
    }

    public LinkedHashMap<Integer, ArrayList<Character>> loadAll() {
        return tileMap;
    }

}
