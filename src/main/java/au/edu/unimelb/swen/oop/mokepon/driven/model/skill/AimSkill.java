package au.edu.unimelb.swen.oop.mokepon.driven.model.skill;

import au.edu.unimelb.swen.oop.mokepon.driven.battle.BattleContext;
import au.edu.unimelb.swen.oop.mokepon.driven.battle.BattleOutcome;
import au.edu.unimelb.swen.oop.mokepon.driven.battle.BattleResult;
import au.edu.unimelb.swen.oop.mokepon.driven.model.skill.util.GainCalculator;
import au.edu.unimelb.swen.oop.mokepon.driven.model.Mokepon;

import java.util.ArrayList;

public class AimSkill implements Skill {

    @Override
    public String getName() {
        return "Aim";
    }

    @Override
    public BattleResult execute(Mokepon user, Mokepon target, BattleContext battleContext, double damageMult) {
        ArrayList<String> messageList = new ArrayList<>();
        messageList.add(user.getMokeponStatus().getPlayerID() + "'s " + user.getName() + " used Aim!\n");
        if (user.getMokeponStatus().getCurrentAccuracy() >= 100) {
            user.getMokeponStatus().setCurrentAccuracy(100);
            messageList.add("\tBut their accuracy is filled.\n");
            return new BattleResult(messageList, BattleOutcome.ONGOING);
        }

        int accuracyImcrement = GainCalculator.calculate(1, 10);
        user.getMokeponStatus().setCurrentAccuracy(user.getMokeponStatus().getCurrentAccuracy() + accuracyImcrement);
        if (user.getMokeponStatus().getCurrentAccuracy() > 100) {
            accuracyImcrement = user.getMokeponStatus().getCurrentAccuracy() - 100;
            user.getMokeponStatus().setCurrentAccuracy(100);
        }
        messageList.add("\tThey raised their accuracy by " + accuracyImcrement + " points!\n");
        return new BattleResult(messageList, BattleOutcome.ONGOING);
    }

    @Override
    public int getEnergyCost() {
        return 3;
    }
}


