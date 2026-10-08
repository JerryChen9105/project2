package au.edu.unimelb.swen.oop.mokepon.driven.model.effect;

import au.edu.unimelb.swen.oop.mokepon.driven.model.MokeponStatus;

public class BleedEffect implements Effect {





    @Override
    public void onTurnEnd(MokeponStatus owner) {

    }

    @Override
    public boolean isExpired() {
        return false;
    }

    @Override
    public String getName() {
        return "Bleed";
    }
}
