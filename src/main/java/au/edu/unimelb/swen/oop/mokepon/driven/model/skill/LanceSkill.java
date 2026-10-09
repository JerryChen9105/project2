package au.edu.unimelb.swen.oop.mokepon.driven.model.skill;

import au.edu.unimelb.swen.oop.mokepon.driven.battle.BattleContext;
import au.edu.unimelb.swen.oop.mokepon.driven.battle.BattleOutcome;
import au.edu.unimelb.swen.oop.mokepon.driven.battle.BattleResult;
import au.edu.unimelb.swen.oop.mokepon.driven.model.Mokepon;
import au.edu.unimelb.swen.oop.mokepon.driven.model.effect.BleedEffect;
import au.edu.unimelb.swen.oop.mokepon.driven.model.skill.util.DamageCalculator;
import au.edu.unimelb.swen.oop.mokepon.driven.model.skill.util.HitCalculator;

import java.util.ArrayList;

public class LanceSkill implements Skill {

    @Override
    public String getName() {
        return "Lance";
    }

    @Override
    public BattleResult execute(Mokepon user, Mokepon target, BattleContext battleContext, double damageMult) {
        boolean hit = HitCalculator.check(user.getMokeponStatus(), target.getMokeponStatus());
        ArrayList<String> messageList = new ArrayList<>();
        messageList.add(user.getMokeponStatus().getPlayerID() + "'s " + user.getName() + " used Lance!\n");

        if (!hit) {
            messageList.add("\tBut they missed.\n");
            return new BattleResult(messageList, BattleOutcome.ONGOING);
        }

        int damage = DamageCalculator.calculate(user.getMokeponStatus(),
                target.getMokeponStatus(),
                0.5 + (damageMult - 1));
        target.getMokeponStatus().takeDamage(damage);

        target.getMokeponStatus().addEffect(new BleedEffect(user.getMokeponStatus().getCurrentStrength()));

        messageList.add("\tHit " + target.getName() + " for " + damage + " damage! They started bleeding!\n");
        return new BattleResult(messageList, BattleOutcome.ONGOING);
    }

    @Override
    public int getEnergyCost() {
        return 4;
    }
}


