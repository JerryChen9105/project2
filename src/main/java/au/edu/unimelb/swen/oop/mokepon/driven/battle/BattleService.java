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


    public BattleResult useSkill(Skill skill, Mokepon self, Mokepon other, BattleContext battleContext, double damageMult) {
        self.getMokeponStatus().setCurrentEnergy(self.getMokeponStatus().getCurrentEnergy() - skill.getEnergyCost());
        BattleResult skillResult = skill.execute(self, other, battleContext, damageMult);
        BattleOutcome outcome = checkBattleEnd(self, other);
        if (outcome != BattleOutcome.ONGOING) return new BattleResult(skillResult.getMessageList(), outcome);
        return skillResult;
    }

    public BattleResult useEnemyRandomSkill(Mokepon self, Mokepon other, BattleContext battleContext, double damageMult) {
        ArrayList<Skill> availableSkillList = new ArrayList<>();
        self.getMokeponStatus().getSkillList().forEach(skill -> {
            if (skill.getEnergyCost() <= self.getMokeponStatus().getCurrentEnergy()) {
                availableSkillList.add(skill);
            }
        });

        Skill chosenSkill = availableSkillList.get(randomSource.nextInt(0, availableSkillList.size()));

        return useSkill(chosenSkill, self, other, battleContext, damageMult);
    }

    public BattleOutcome resolveTurnEnd(Mokepon player, Mokepon enemy, BattleContext context) {
        enemy.getMokeponStatus().updateEffect(enemy, context);
        //System.out.println(enemy.getMokeponStatus().getEffectList().size());
        if (enemy.getMokeponStatus().getCurrentHP() <= 0) {
            return BattleOutcome.PLAYER_WIN;
        }
        player.getMokeponStatus().updateEffect(player, context);
        //System.out.println(player.getMokeponStatus().getEffectList().size());
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


