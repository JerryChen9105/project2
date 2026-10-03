package au.edu.unimelb.swen.oop.mokepon.battle.skill;

import au.edu.unimelb.swen.oop.mokepon.battle.BattleContext;
import au.edu.unimelb.swen.oop.mokepon.battle.BattleOutcome;
import au.edu.unimelb.swen.oop.mokepon.battle.BattleResult;
import au.edu.unimelb.swen.oop.mokepon.battle.skill.util.GainCalculator;
import au.edu.unimelb.swen.oop.mokepon.model.Mokepon;

import java.util.ArrayList;

public class AimSkill implements Skill {

    @Override
    public String getName() {
        return "Aim";
    }

    @Override
    public BattleResult execute(Mokepon user, Mokepon target, BattleContext battleContext) {
        ArrayList<String> messageList = new ArrayList<>();
        messageList.add("\nPlayer's " + user.getName() + " used Aim!\n");
        if (user.getCurrentAccuracy() >= 100) {
            user.setCurrentAccuracy(100);
            messageList.add("\tBut their accuracy is filled.\n");
            return new BattleResult(messageList, BattleOutcome.ONGOING);
        }

        int accuracyImcrement = GainCalculator.calculate(1, 9);
        user.setCurrentAccuracy(user.getCurrentAccuracy() + accuracyImcrement);
        if (user.getCurrentAccuracy() > 100) {
            accuracyImcrement = user.getCurrentAccuracy() - 100;
            user.setCurrentAccuracy(100);
        }
        messageList.add("\tThey raised their accuracy by " + accuracyImcrement + " points!\n");
        return new BattleResult(messageList, BattleOutcome.ONGOING);
    }

    @Override
    public int getEnergyCost() {
        return 3;
    }
}
