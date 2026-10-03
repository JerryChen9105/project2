package au.edu.unimelb.swen.oop.mokepon.battle.skill;

import au.edu.unimelb.swen.oop.mokepon.battle.BattleContext;
import au.edu.unimelb.swen.oop.mokepon.battle.BattleOutcome;
import au.edu.unimelb.swen.oop.mokepon.battle.BattleResult;
import au.edu.unimelb.swen.oop.mokepon.battle.skill.util.GainCalculator;
import au.edu.unimelb.swen.oop.mokepon.model.Mokepon;

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
        if (user.getCurrentDefence() >= 100) {
            user.setCurrentDefence(100);
            messageList.add("\tBut their defence is filled.\n");
            return new BattleResult(messageList, BattleOutcome.ONGOING);
        }

        int defenceImcrement = GainCalculator.calculate(1, 9);
        user.setCurrentDefence(user.getCurrentDefence() + defenceImcrement);
        if (user.getCurrentDefence() > 100) {
            defenceImcrement = user.getCurrentDefence() - 100;
            user.setCurrentDefence(100);
        }
        messageList.add("\tThey raised their defence by " + defenceImcrement + " points!\n");
        return new BattleResult(messageList, BattleOutcome.ONGOING);
    }

    @Override
    public int getEnergyCost() {
        return 3;
    }
}
