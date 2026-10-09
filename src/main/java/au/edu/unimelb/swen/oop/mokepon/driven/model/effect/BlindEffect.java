package au.edu.unimelb.swen.oop.mokepon.driven.model.effect;

import au.edu.unimelb.swen.oop.mokepon.driven.battle.BattleContext;
import au.edu.unimelb.swen.oop.mokepon.driven.model.MokeponStatus;

public class BlindEffect implements Effect {

    private int remainingTurn;

    public BlindEffect() {
        remainingTurn = 3;
    }

    @Override
    public void onTurnEnd(MokeponStatus mokeponStatus, BattleContext battleContext) {
        remainingTurn--;
    }

    @Override
    public boolean isExpired() {
        return remainingTurn <= 0;
    }


    @Override
    public String getName() {
        return "Blind";
    }
}


