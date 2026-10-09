package au.edu.unimelb.swen.oop.mokepon.util.overworld;

import au.edu.unimelb.swen.oop.mokepon.data.GameData;
import au.edu.unimelb.swen.oop.mokepon.data.MokeponLoader;
import au.edu.unimelb.swen.oop.mokepon.driven.model.Mokepon;
import au.edu.unimelb.swen.oop.mokepon.driven.model.MokeponType;
import au.edu.unimelb.swen.oop.mokepon.driven.overworld.TerrainAdvantage;
import au.edu.unimelb.swen.oop.mokepon.driven.overworld.TileType;

import java.util.ArrayList;
import java.util.HashMap;

public class TypeMokeponLoader {

    private static final MokeponLoader mokeponLoader = new MokeponLoader(GameData.getGameData());

    public static ArrayList<Mokepon> getTypeMokeponList(TileType type, int hasAdv) {
        ArrayList<Mokepon> mokeponList = new ArrayList<>();
        mokeponLoader.loadAll().forEach((s, mokepon) -> {
            if (hasAdv == TerrainAdvantage.hasTerrainAdvantage(mokepon.getMokeponType(), type)) {
                mokeponList.add(mokepon);
            }
        });
        return mokeponList;
    }

}
