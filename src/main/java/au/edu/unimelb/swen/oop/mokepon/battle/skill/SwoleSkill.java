package au.edu.unimelb.swen.oop.mokepon.battle.skill;

import au.edu.unimelb.swen.oop.mokepon.battle.BattleContext;
import au.edu.unimelb.swen.oop.mokepon.battle.BattleOutcome;
import au.edu.unimelb.swen.oop.mokepon.battle.BattleResult;
import au.edu.unimelb.swen.oop.mokepon.battle.skill.util.GainCalculator;
import au.edu.unimelb.swen.oop.mokepon.model.Mokepon;

import java.util.ArrayList;

public class SwoleSkill implements Skill {

    @Override
    public String getName() {
        return "Swole";
    }

    @Override
    public BattleResult execute(Mokepon user, Mokepon target, BattleContext battleContext) {
        ArrayList<String> messageList = new ArrayList<>();
        messageList.add("\nPlayer's " + user.getName() + " used Swole!\n");
        if (user.getCurrentStrength() >= 100) {
            user.setCurrentStrength(100);
            messageList.add("\tBut their strength is filled.\n");
            return new BattleResult(messageList, BattleOutcome.ONGOING);
        }

        int strengthImcrement = GainCalculator.calculate(1, 9);
        user.setCurrentStrength(user.getCurrentStrength() + strengthImcrement);
        if (user.getCurrentStrength() > 100) {
            strengthImcrement = user.getCurrentStrength() - 100;
            user.setCurrentStrength(100);
        }
        messageList.add("\tThey raised their strength by " + strengthImcrement + " points!\n");
        return new BattleResult(messageList, BattleOutcome.ONGOING);
    }

    @Override
    public int getEnergyCost() {
        return 3;
    }
}
