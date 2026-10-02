package au.edu.unimelb.swen.oop.mokepon.battle;

import java.util.ArrayList;

public class BattleResult {

    private ArrayList<String> messageList;
    private boolean ended;
    private BattleOutcome outcome;


    public BattleResult(ArrayList<String> messageList, BattleOutcome outcome) {
        this.messageList = messageList;
        this.outcome = outcome;
    }



    public ArrayList<String> getMessageList() {
        return null;
    }

    public boolean isEnded() {
        return this.outcome != BattleOutcome.ONGOING;
    }

    public BattleOutcome getOutcome() {
        return null;
    }

}
