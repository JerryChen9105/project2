package au.edu.unimelb.swen.oop.mokepon.driven.model.skill;

import au.edu.unimelb.swen.oop.mokepon.driven.battle.BattleContext;
import au.edu.unimelb.swen.oop.mokepon.driven.battle.BattleOutcome;
import au.edu.unimelb.swen.oop.mokepon.driven.battle.BattleResult;
import au.edu.unimelb.swen.oop.mokepon.driven.model.skill.util.DamageCalculator;
import au.edu.unimelb.swen.oop.mokepon.driven.model.skill.util.HitCalculator;
import au.edu.unimelb.swen.oop.mokepon.driven.model.Mokepon;

import java.util.ArrayList;

public class TackleSkill implements Skill {

    @Override
    public String getName() {
        return "Tackle";
    }

    @Override
    public BattleResult execute(Mokepon user, Mokepon target, BattleContext battleContext) {
        boolean hit = HitCalculator.check(user, target);
        ArrayList<String> messageList = new ArrayList<>();
        messageList.add("\nPlayer's " + user.getName() + " used Tackle!\n");
        if (!hit) {
            messageList.add("\tBut they missed.\n");
            return new BattleResult(messageList, BattleOutcome.ONGOING);
        }

        int damage = DamageCalculator.calculate(user, target, 2.0);
        target.takeDamage(damage);
        messageList.add("\tHit " + target.getName() + " for " + damage + " damage!\n");
        return new BattleResult(messageList, BattleOutcome.ONGOING);
    }

    @Override
    public int getEnergyCost() {
        return 5;
    }
}
