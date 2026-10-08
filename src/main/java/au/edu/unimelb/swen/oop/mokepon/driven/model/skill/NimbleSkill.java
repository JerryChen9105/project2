package au.edu.unimelb.swen.oop.mokepon.driven.model.skill;

import au.edu.unimelb.swen.oop.mokepon.driven.battle.BattleContext;
import au.edu.unimelb.swen.oop.mokepon.driven.battle.BattleOutcome;
import au.edu.unimelb.swen.oop.mokepon.driven.battle.BattleResult;
import au.edu.unimelb.swen.oop.mokepon.driven.model.skill.util.GainCalculator;
import au.edu.unimelb.swen.oop.mokepon.driven.model.Mokepon;

import java.util.ArrayList;

public class NimbleSkill implements Skill {

    @Override
    public String getName() {
        return "Nimble";
    }

    @Override
    public BattleResult execute(Mokepon user, Mokepon target, BattleContext battleContext) {
        ArrayList<String> messageList = new ArrayList<>();
        messageList.add("\nPlayer's " + user.getName() + " used Nimble!\n");
        if (user.getMokeponStatus().getCurrentDodge() >= 100) {
            user.getMokeponStatus().setCurrentDodge(100);
            messageList.add("\tBut their dodge is filled.\n");
            return new BattleResult(messageList, BattleOutcome.ONGOING);
        }
        int dodgeImcrement = GainCalculator.calculate(1, 9);
        user.getMokeponStatus().setCurrentDodge(user.getMokeponStatus().getCurrentDodge() + dodgeImcrement);
        if (user.getMokeponStatus().getCurrentDodge() > 100) {
            dodgeImcrement = user.getMokeponStatus().getCurrentDodge() - 100;
            user.getMokeponStatus().setCurrentDodge(100);
        }
        messageList.add("\tThey raised their dodge by " + dodgeImcrement + " points!\n");
        return new BattleResult(messageList, BattleOutcome.ONGOING);
    }

    @Override
    public int getEnergyCost() {
        return 3;
    }
}
