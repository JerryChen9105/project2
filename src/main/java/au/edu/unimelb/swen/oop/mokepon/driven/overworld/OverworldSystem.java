package au.edu.unimelb.swen.oop.mokepon.driven.overworld;

import au.edu.unimelb.swen.oop.mokepon.state.Inventory;
import au.edu.unimelb.swen.oop.mokepon.state.ItemType;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class OverworldSystem {

    private WorldMap worldMap;
    private Coordinate player;
    private Coordinate lastCoordinate;

    private LinkedHashMap<Coordinate, OverworldStatus> coordinateStatusMap;
    private LinkedHashMap<Coordinate, ItemType> coordinateItemMap;
    private LinkedHashMap<Coordinate, EnemyType> coordinateEnemyMap;

    private Inventory inventory;


    public OverworldSystem(ArrayList<ArrayList<Character>> worldMapListMatrix,
                           Coordinate playerCoordinate,
                           Coordinate exitCoordinate,
                           LinkedHashMap<Coordinate, EnemyType> coordinateEnemyMap,
                           LinkedHashMap<Coordinate, ItemType> coordinateItemMap) {
        worldMap = new WorldMap(worldMapListMatrix);
        player = new Coordinate(playerCoordinate.getX(), playerCoordinate.getY());
        lastCoordinate = new Coordinate(player.getX(), player.getY());

        this.coordinateEnemyMap = coordinateEnemyMap;
        this.coordinateItemMap = coordinateItemMap;

        coordinateStatusMap = new LinkedHashMap<>();
        coordinateEnemyMap.forEach((coordinate, enemyType) -> {
            coordinateStatusMap.put(coordinate, OverworldStatus.ENEMY);
        });
        coordinateItemMap.forEach((coordinate, status) -> {
            coordinateStatusMap.put(coordinate, OverworldStatus.ITEM);
        });
        coordinateStatusMap.put(exitCoordinate, OverworldStatus.EXIT);
    }

    public int getPlayerX() { return player.getX(); }
    public int getPlayerY() { return player.getY(); }
    public Coordinate getPlayerCoordinate() { return new Coordinate(player.getX(), player.getY()); }

    public ItemType checkOutItemType() {
        ItemType itemType = coordinateItemMap.get(player);
        if (itemType == null) return ItemType.UNKNOWN;

        coordinateItemMap.remove(player);
        coordinateStatusMap.remove(player);
        return itemType;
    }

    public OverworldStatus movePlayer(int dx, int dy) {
        int newX = player.getX() + dx;
        int newY = player.getY() + dy;
        Coordinate targetCoord = new Coordinate(newX, newY);

        TileType tileType = worldMap.getTileType(targetCoord);
        if (!tileType.isWalkable()) {
            return OverworldStatus.STOP;
        }

        lastCoordinate.setXY(player);
        player.setXY(newX, newY);

        OverworldStatus status = coordinateStatusMap.get(player);
        if (status != null) {
            return status;
        }

        return OverworldStatus.MOVE;
    }

    public void revertMove() {
        player.setXY(lastCoordinate);
    }

}


