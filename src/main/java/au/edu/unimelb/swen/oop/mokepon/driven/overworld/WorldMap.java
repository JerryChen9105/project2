package au.edu.unimelb.swen.oop.mokepon.driven.overworld;

import java.util.ArrayList;

public class WorldMap {

    private TileType[][] mapStatusMatrix;
    private int maxX;
    private int maxY;

    public WorldMap(ArrayList<ArrayList<Character>> worldMapListMatrix) {
        this.maxY = worldMapListMatrix.size();
        int maxX = 0;
        for (ArrayList<Character> row : worldMapListMatrix) {
            if (row.size() > maxX) maxX = row.size();
        }
        this.maxX = maxX;

        mapStatusMatrix = new TileType[maxY + 2][maxX + 2];

        for (int y = 0; y < maxY + 2; y++) {
            for (int x = 0; x < maxX + 2; x++) {
                mapStatusMatrix[y][x] = TileType.EMPTY;
            }
        }

        for (int y = 0; y < maxY; y++) {
            ArrayList<Character> row = worldMapListMatrix.get(y);
            for (int x = 0; x < row.size(); x++) {
                mapStatusMatrix[y + 1][x + 1] = getTileType(row.get(x));
            }
        }
    }

    private TileType getTileType(char c) {
        for (TileType tileType : TileType.values()) {
            if (tileType.getTileChar() == c) return tileType;
        }
        return TileType.EMPTY;
    }

    public TileType getTileType(Coordinate coordinate) {
        int realX = coordinate.getX();
        int realY = coordinate.getY();

        int mappedX = realX + 1;
        int mappedY = realY + 1;

        if (mappedX < 0 || mappedX >= maxX + 2 || mappedY < 0 || mappedY >= maxY + 2) {
            return TileType.EMPTY;
        }
        return mapStatusMatrix[mappedY][mappedX];
    }

    public int getMaxX() {
        return maxX;
    }
    public int getMaxY() {
        return maxY;
    }
}


