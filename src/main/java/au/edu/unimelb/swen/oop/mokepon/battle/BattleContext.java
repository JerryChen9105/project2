package au.edu.unimelb.swen.oop.mokepon.battle;

import java.util.ArrayList;

public class BattleContext {

    ArrayList<String> logList;


    public BattleContext() {
        logList = new ArrayList<>();
    }


    public void log(String logMessage) {
        logList.add(logMessage);
    }

    public ArrayList<String> getLogList() {
        return logList;
    }

}
