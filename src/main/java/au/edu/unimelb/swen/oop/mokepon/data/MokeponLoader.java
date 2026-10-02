package au.edu.unimelb.swen.oop.mokepon.data;

import au.edu.unimelb.swen.oop.mokepon.model.Mokepon;
import au.edu.unimelb.swen.oop.mokepon.model.MokeponType;

import java.util.*;

public class MokeponLoader extends GameDataLoader<String, Mokepon> {

    private LinkedHashMap<String, Mokepon> mokeponMap = new LinkedHashMap<>();

    private final static int MAXMOKEPONNUM = 50;


    public MokeponLoader(Properties gameData) {
        super(gameData);
        String propertyPrefix;
        int mokeponCount = 0;
        while (mokeponCount < MAXMOKEPONNUM) {
            propertyPrefix = "mokepon." + mokeponCount + ".";
            try {
                String name = gameData.getProperty(propertyPrefix + "name");
                String imgURL = gameData.getProperty(propertyPrefix + "image");
                String hp = gameData.getProperty(propertyPrefix + "hp");
                String energy = gameData.getProperty(propertyPrefix + "energy");
                String strength = gameData.getProperty(propertyPrefix + "strength");
                String accuracy = gameData.getProperty(propertyPrefix + "accuracy");
                String defence = gameData.getProperty(propertyPrefix + "defence");
                String dodge = gameData.getProperty(propertyPrefix + "dodge");
                String skillStr = gameData.getProperty(propertyPrefix + "skills");
                String type = gameData.getProperty(propertyPrefix + "type");
                ArrayList<String> skillStrList = new ArrayList<>();
                Collections.addAll(skillStrList, skillStr.split("\\|"));

                mokeponMap.put(name, new Mokepon(name, imgURL, Integer.parseInt(hp), Integer.parseInt(energy),
                        Integer.parseInt(strength), Integer.parseInt(accuracy), Integer.parseInt(defence),
                        Integer.parseInt(dodge), skillStrList, MokeponType.valueOf(type)));
                mokeponCount++;
            } catch (Exception e) {
                break;
            }
        }
    }

    @Override
    public Mokepon load(String obj) {
        return mokeponMap.get(obj);
    }

    @Override
    public LinkedHashMap<String, Mokepon> loadAll() {
        return mokeponMap;
    }

}
