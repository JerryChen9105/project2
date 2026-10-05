package au.edu.unimelb.swen.oop.mokepon.data;

import java.util.Properties;

public class GameData {

    private static Properties gameData;

    public static void setGameData(Properties gameData) {
        GameData.gameData = gameData;
    }

    public static Properties getGameData() {
        return gameData;
    }

}
