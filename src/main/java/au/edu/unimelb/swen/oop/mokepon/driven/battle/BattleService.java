package au.edu.unimelb.swen.oop.mokepon.driven.battle;

import au.edu.unimelb.swen.oop.mokepon.driven.model.effect.Effect;
import au.edu.unimelb.swen.oop.mokepon.driven.model.skill.Skill;
import au.edu.unimelb.swen.oop.mokepon.driven.model.Mokepon;
import au.edu.unimelb.swen.oop.mokepon.driven.model.skill.SkillRegistry;
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
        if (self.getMokeponStatus().getCurrentEnergy() < skill.getEnergyCost()) {
            ArrayList<String> messageList = new ArrayList<>();
            messageList.add(self.getName() + " do not have enough energy for " + skill.getName() + "!");
            return new BattleResult(messageList, BattleOutcome.ONGOING);
        }

        self.getMokeponStatus().setCurrentEnergy(self.getMokeponStatus().getCurrentEnergy() - skill.getEnergyCost());
        BattleResult skillResult = skill.execute(self, other, battleContext);
        BattleOutcome outcome = checkBattleEnd(self, other);
        if (outcome != BattleOutcome.ONGOING) return new BattleResult(skillResult.getMessageList(), outcome);
        return skillResult;
    }

    public BattleResult useEnemyRandomSkill(Mokepon self, Mokepon other, BattleContext battleContext) {
        ArrayList<String> affordableSkillList = new ArrayList<>();
        int currentEnergy = self.getMokeponStatus().getCurrentEnergy();

        for (String skillName : self.getSkills()) {
            Skill skill = SkillRegistry.getSkill(skillName);
            if (skill.getEnergyCost() <= currentEnergy) {
                affordableSkillList.add(skillName);
            }
        }

        int randomIndex = randomSource.nextInt(0, affordableSkillList.size());
        Skill chosenSkill = SkillRegistry.getSkill(affordableSkillList.get(randomIndex));

        return useSkill(chosenSkill, self, other, battleContext);
    }

    public BattleOutcome resolveTurnEnd(Mokepon player, Mokepon enemy, BattleContext context) {
        // 需求：先结算敌人的状态
        Effect[] enemyExpiredEffectArr = enemy.getMokeponStatus().updateEffect();
        // （这里需要确保您的 Effect 在触发 onTurnEnd 时，如果造成了伤害，要写进 context.log 中）
        if (enemy.getMokeponStatus().getCurrentHP() <= 0) {
            return BattleOutcome.PLAYER_WIN;
        }

        // 敌人没死，再结算玩家的状态
        Effect[] playerExpiredEffectArr = player.getMokeponStatus().updateEffect();
        if (player.getMokeponStatus().getCurrentHP() <= 0) {
            return BattleOutcome.PLAYER_LOSE;
        }

        return BattleOutcome.ONGOING;
    }


    public BattleOutcome checkBattleEnd(Mokepon self, Mokepon other) {
        if (other.getMokeponStatus().getCurrentHP() <= 0) return BattleOutcome.PLAYER_WIN;
        if (self.getMokeponStatus().getCurrentHP() <= 0) return BattleOutcome.PLAYER_LOSE;
        return BattleOutcome.ONGOING;
    }

}


