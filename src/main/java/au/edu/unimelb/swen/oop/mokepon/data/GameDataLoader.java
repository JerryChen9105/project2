package au.edu.unimelb.swen.oop.mokepon.data;

import java.util.LinkedHashMap;
import java.util.Properties;

public abstract class GameDataLoader<T, V> {

    private static Properties gameData = GameData.getGameData();


    public GameDataLoader(Properties gameData) {
        this.gameData = gameData;
    }

    public abstract V load(T obj);

    public abstract LinkedHashMap<T, V> loadAll();
}
