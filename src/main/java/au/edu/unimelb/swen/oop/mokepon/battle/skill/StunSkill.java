package au.edu.unimelb.swen.oop.mokepon.battle.skill;

import au.edu.unimelb.swen.oop.mokepon.battle.BattleContext;
import au.edu.unimelb.swen.oop.mokepon.battle.BattleResult;
import au.edu.unimelb.swen.oop.mokepon.model.Mokepon;

public class StunSkill implements Skill {

    @Override
    public String getName() {
        return "Stun";
    }

    @Override
    public BattleResult execute(Mokepon user, Mokepon target, BattleContext battleContext) {
        return null;
    }

    @Override
    public int getEnergyCost() {
        return 4;
    }
}
