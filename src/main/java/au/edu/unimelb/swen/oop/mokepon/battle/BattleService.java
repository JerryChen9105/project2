package au.edu.unimelb.swen.oop.mokepon.battle;

import au.edu.unimelb.swen.oop.mokepon.battle.skill.Skill;
import au.edu.unimelb.swen.oop.mokepon.model.Mokepon;
import au.edu.unimelb.swen.oop.mokepon.util.random.RandomSource;

import java.util.ArrayList;
import java.util.HashMap;

public class BattleService {

    private RandomSource randomSource;

    private HashMap<String, Skill> skillMap = new HashMap<>();

    public BattleService(RandomSource randomSource) {
        this.randomSource = randomSource;
    }


    public BattleResult useSkill(Skill skill, Mokepon self, Mokepon other, BattleContext battleContext) {
        if (self.getCurrentEnergy() < skill.getEnergyCost()) {
            ArrayList<String> messageList = new ArrayList<>();
            messageList.add(self.getName() + " do not have enough energy for " + skill.getName() + "!");
            return new BattleResult(messageList, BattleOutcome.ONGOING);
        }

        self.setCurrentEnergy(self.getCurrentEnergy() - skill.getEnergyCost());
        BattleResult skillResult = skill.execute(self, other, battleContext);
        BattleOutcome outcome = checkBattleEnd(self, other);
        if (outcome != BattleOutcome.ONGOING) return new BattleResult(skillResult.getMessageList(), outcome);
        return skillResult;
    }

    public BattleResult useEnemyRandomSkill(Mokepon self, Mokepon other, BattleContext battleContext) {
        int randonSkillInt = randomSource.nextInt(0, self.getSkills().size());
        Skill skill = SkillRegistry.getSkill(self.getSkills().get(randonSkillInt));
        return useSkill(skill, self, other, battleContext);
    }

    public BattleOutcome checkBattleEnd(Mokepon self, Mokepon other) {
        if (other.getCurrentHP() <= 0) return BattleOutcome.PLAYER_WIN;
        if (self.getCurrentHP() <= 0) return BattleOutcome.PLAYER_LOSE;
        return BattleOutcome.ONGOING;
    }

}
