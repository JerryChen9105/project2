package au.edu.unimelb.swen.oop.mokepon.battle.skill;

import au.edu.unimelb.swen.oop.mokepon.battle.BattleContext;
import au.edu.unimelb.swen.oop.mokepon.battle.BattleResult;
import au.edu.unimelb.swen.oop.mokepon.model.Mokepon;

public interface Skill {

    public String getName();
    public int getEnergyCost();
    public BattleResult execute(Mokepon user, Mokepon target, BattleContext battleContext);

}
