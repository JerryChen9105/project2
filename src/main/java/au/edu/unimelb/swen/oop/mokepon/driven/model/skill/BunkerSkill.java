package au.edu.unimelb.swen.oop.mokepon.driven.model.skill;

import au.edu.unimelb.swen.oop.mokepon.driven.battle.BattleContext;
import au.edu.unimelb.swen.oop.mokepon.driven.battle.BattleOutcome;
import au.edu.unimelb.swen.oop.mokepon.driven.battle.BattleResult;
import au.edu.unimelb.swen.oop.mokepon.driven.model.skill.util.GainCalculator;
import au.edu.unimelb.swen.oop.mokepon.driven.model.Mokepon;

import java.util.ArrayList;

public class BunkerSkill implements Skill {

    @Override
    public String getName() {
        return "Bunker";
    }

    @Override
    public BattleResult execute(Mokepon user, Mokepon target, BattleContext battleContext) {
        ArrayList<String> messageList = new ArrayList<>();
        messageList.add("\nPlayer's " + user.getName() + " used Bunker!\n");
        if (user.getMokeponStatus().getCurrentDefence() >= 100) {
            user.getMokeponStatus().setCurrentDefence(100);
            messageList.add("\tBut their defence is filled.\n");
            return new BattleResult(messageList, BattleOutcome.ONGOING);
        }

        int defenceImcrement = GainCalculator.calculate(1, 9);
        user.getMokeponStatus().setCurrentDefence(user.getMokeponStatus().getCurrentDefence() + defenceImcrement);
        if (user.getMokeponStatus().getCurrentDefence() > 100) {
            defenceImcrement = user.getMokeponStatus().getCurrentDefence() - 100;
            user.getMokeponStatus().setCurrentDefence(100);
        }
        messageList.add("\tThey raised their defence by " + defenceImcrement + " points!\n");
        return new BattleResult(messageList, BattleOutcome.ONGOING);
    }

    @Override
    public int getEnergyCost() {
        return 3;
    }
}
