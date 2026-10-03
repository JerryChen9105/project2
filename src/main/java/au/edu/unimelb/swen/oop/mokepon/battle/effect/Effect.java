package au.edu.unimelb.swen.oop.mokepon.battle.effect;

import au.edu.unimelb.swen.oop.mokepon.battle.BattleContext;
import au.edu.unimelb.swen.oop.mokepon.model.Mokepon;

public interface Effect {

    public void onTurnEnd(Mokepon owner, BattleContext battleContext);

    public boolean isExpired();

    public String getName();

}
