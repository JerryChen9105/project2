package au.edu.unimelb.swen.oop.mokepon.data;

import java.util.LinkedHashMap;
import java.util.Properties;

public class TileLoader extends GameDataLoader<Integer, Object> {

    public TileLoader(Properties gameData) {
        super(gameData);
    }

    @Override
    public Object load(Integer obj) {
        return null;
    }

    @Override
    public LinkedHashMap<Integer, Object> loadAll() {
        return null;
    }

}
