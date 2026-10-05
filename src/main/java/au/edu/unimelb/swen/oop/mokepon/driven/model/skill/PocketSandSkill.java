package au.edu.unimelb.swen.oop.mokepon.driven.model.skill;

import au.edu.unimelb.swen.oop.mokepon.driven.battle.BattleContext;
import au.edu.unimelb.swen.oop.mokepon.driven.battle.BattleResult;
import au.edu.unimelb.swen.oop.mokepon.driven.model.Mokepon;

public class PocketSandSkill implements Skill {

    @Override
    public String getName() {
        return "Pocket Sand";
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
