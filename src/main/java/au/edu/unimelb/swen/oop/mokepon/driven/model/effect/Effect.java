package au.edu.unimelb.swen.oop.mokepon.driven.model.effect;

import au.edu.unimelb.swen.oop.mokepon.driven.battle.BattleContext;
import au.edu.unimelb.swen.oop.mokepon.driven.model.Mokepon;
import au.edu.unimelb.swen.oop.mokepon.driven.model.MokeponStatus;

public interface Effect {

    public void onTurnEnd(MokeponStatus owner);

    public boolean isExpired();

    public String getName();

}
