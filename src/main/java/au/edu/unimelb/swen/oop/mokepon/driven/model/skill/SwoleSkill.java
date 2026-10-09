package au.edu.unimelb.swen.oop.mokepon.driven.model.skill;

import au.edu.unimelb.swen.oop.mokepon.driven.battle.BattleContext;
import au.edu.unimelb.swen.oop.mokepon.driven.battle.BattleOutcome;
import au.edu.unimelb.swen.oop.mokepon.driven.battle.BattleResult;
import au.edu.unimelb.swen.oop.mokepon.driven.model.skill.util.GainCalculator;
import au.edu.unimelb.swen.oop.mokepon.driven.model.Mokepon;

import java.util.ArrayList;

public class SwoleSkill implements Skill {

    @Override
    public String getName() {
        return "Swole";
    }

    @Override
    public BattleResult execute(Mokepon user, Mokepon target, BattleContext battleContext, double damageMult) {
        ArrayList<String> messageList = new ArrayList<>();
        messageList.add(user.getMokeponStatus().getPlayerID() + "'s " + user.getName() + " used Swole!\n");
        if (user.getMokeponStatus().getCurrentStrength() >= 100) {
            user.getMokeponStatus().setCurrentStrength(100);
            messageList.add("\tBut their strength is filled.\n");
            return new BattleResult(messageList, BattleOutcome.ONGOING);
        }

        int strengthImcrement = GainCalculator.calculate(1, 10);
        user.getMokeponStatus().setCurrentStrength(user.getMokeponStatus().getCurrentStrength() + strengthImcrement);
        if (user.getMokeponStatus().getCurrentStrength() > 100) {
            strengthImcrement = user.getMokeponStatus().getCurrentStrength() - 100;
            user.getMokeponStatus().setCurrentStrength(100);
        }
        messageList.add("\tThey raised their strength by " + strengthImcrement + " points!\n");
        return new BattleResult(messageList, BattleOutcome.ONGOING);
    }

    @Override
    public int getEnergyCost() {
        return 3;
    }
}


