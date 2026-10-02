package au.edu.unimelb.swen.oop.mokepon.battle;

import au.edu.unimelb.swen.oop.mokepon.battle.skill.Skill;
import au.edu.unimelb.swen.oop.mokepon.model.Mokepon;
import au.edu.unimelb.swen.oop.mokepon.util.random.RandomSource;

import java.util.HashMap;

public class BattleService {

    private RandomSource randomSource;

    private HashMap<String, Skill> skillMap = new HashMap<>();

    public BattleService(RandomSource randomSource) {
        this.randomSource = randomSource;
    }


    public BattleResult useSkill(Skill skill, Mokepon self, Mokepon other, BattleContext battleContext) {
        return skill.execute(self, other, battleContext);
    }

    public BattleResult useEnemyRandomSkill(Mokepon self, Mokepon other, BattleContext battleContext) {
        int randonSkillInt = randomSource.nextInt(0, self.getSkills().size());
        return null; // add code
    }

    public BattleOutcome checkBattleEnd(Mokepon self, Mokepon other) {
        return null;
    }

}
