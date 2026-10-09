package au.edu.unimelb.swen.oop.mokepon.driven.overworld;

import au.edu.unimelb.swen.oop.mokepon.driven.model.MokeponType;

import java.util.Map;

public class TerrainAdvantage {

    public static int hasTerrainAdvantage(MokeponType mokeponType, TileType tileType) {
        switch (mokeponType) {
            case ICE -> {
                if (tileType == TileType.ROCKS) return 1;
                else if (tileType == TileType.GRASS) return -1;
            }
            case WATER -> {
                if (tileType == TileType.WATER) return 1;
                else if (tileType == TileType.ROCKS) return -1;
            }
            case FIRE -> {
                if (tileType == TileType.FOREST) return 1;
                else if (tileType == TileType.WATER) return -1;
            }
            case LIGHTNING -> {
                if (tileType == TileType.GRASS) return 1;
                else if (tileType == TileType.FOREST) return -1;
            }
        }
        return 0;
    }

}
