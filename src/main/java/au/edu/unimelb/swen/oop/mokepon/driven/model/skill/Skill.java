package au.edu.unimelb.swen.oop.mokepon.driven.model.skill;

import au.edu.unimelb.swen.oop.mokepon.driven.battle.BattleContext;
import au.edu.unimelb.swen.oop.mokepon.driven.battle.BattleResult;
import au.edu.unimelb.swen.oop.mokepon.driven.model.Mokepon;

public interface Skill {

    public String getName();
    public int getEnergyCost();
    public BattleResult execute(Mokepon user, Mokepon target, BattleContext battleContext);

}
