package au.edu.unimelb.swen.oop.mokepon.battle.effect;

import au.edu.unimelb.swen.oop.mokepon.battle.BattleContext;
import au.edu.unimelb.swen.oop.mokepon.model.Mokepon;

public class BlindEffect implements Effect {





    @Override
    public void onTurnEnd(Mokepon owner, BattleContext battleContext) {

    }

    @Override
    public boolean isExpired() {
        return false;
    }

    @Override
    public String getName() {
        return "Blind";
    }
}
