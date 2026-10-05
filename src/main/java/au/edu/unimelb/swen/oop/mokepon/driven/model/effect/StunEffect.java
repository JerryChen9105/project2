package au.edu.unimelb.swen.oop.mokepon.driven.model.effect;

import au.edu.unimelb.swen.oop.mokepon.driven.battle.BattleContext;
import au.edu.unimelb.swen.oop.mokepon.driven.model.Mokepon;
import au.edu.unimelb.swen.oop.mokepon.driven.model.MokeponStatus;

public class StunEffect implements Effect {





    @Override
    public void onTurnEnd(MokeponStatus mokeponStatus) {

    }

    @Override
    public boolean isExpired() {
        return false;
    }

    @Override
    public String getName() {
        return "Stun";
    }
}
