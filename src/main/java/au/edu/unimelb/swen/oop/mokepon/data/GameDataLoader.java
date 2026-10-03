package au.edu.unimelb.swen.oop.mokepon.data;

import au.edu.unimelb.swen.oop.mokepon.model.Mokepon;

import java.util.LinkedHashMap;
import java.util.Properties;

public abstract class GameDataLoader<T, V> {

    private Properties gameData;


    public GameDataLoader(Properties gameData) {
        this.gameData = gameData;
    }

    public abstract V load(T obj);

    public abstract LinkedHashMap<T, V> loadAll();
}
