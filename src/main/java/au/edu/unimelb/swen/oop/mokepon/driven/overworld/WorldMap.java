package au.edu.unimelb.swen.oop.mokepon.driven.overworld;

import java.util.ArrayList;

public class WorldMap {

    private MapStatus[][] mapStatusMatrix;
    private int maxX = -1, maxY = -1;


    public WorldMap(ArrayList<ArrayList<Character>> worldMapListMatrix) {
        worldMapListMatrix.forEach(mapStatusList -> {
            if (mapStatusList.size() > maxX) maxX = mapStatusList.size();
        });
        maxY = worldMapListMatrix.size();

        mapStatusMatrix = new MapStatus[maxX][maxY];
        for (int i = 0; i < maxY; i++) {
            for (int j = 0; i < maxX; j++) {
                mapStatusMatrix[i][j] = getMapStatus(worldMapListMatrix.get(i).get(j));
            }
        }
    }

    private MapStatus getMapStatus(char c) {
        for (MapStatus status : MapStatus.values()) {
            if (status.name().equals(String.valueOf(c))) return status;
        }
        return MapStatus.EMPTY;
    }


    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }

}
