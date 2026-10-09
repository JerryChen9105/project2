package au.edu.unimelb.swen.oop.mokepon.driven.model.skill;

import au.edu.unimelb.swen.oop.mokepon.driven.battle.BattleContext;
import au.edu.unimelb.swen.oop.mokepon.driven.battle.BattleOutcome;
import au.edu.unimelb.swen.oop.mokepon.driven.battle.BattleResult;
import au.edu.unimelb.swen.oop.mokepon.driven.model.Mokepon;
import au.edu.unimelb.swen.oop.mokepon.driven.model.effect.BlindEffect;
import au.edu.unimelb.swen.oop.mokepon.driven.model.skill.util.HitCalculator;

import java.util.ArrayList;

public class PocketSandSkill implements Skill {

    @Override
    public String getName() {
        return "Pocket Sand";
    }

    @Override
    public BattleResult execute(Mokepon user, Mokepon target, BattleContext battleContext) {
        boolean hit = HitCalculator.check(user.getMokeponStatus(), target.getMokeponStatus());
        ArrayList<String> messageList = new ArrayList<>();
        messageList.add("Player's " + user.getName() + " used Pocket Sand!\n");

        if (!hit) {
            messageList.add("\tBut they missed.\n");
            return new BattleResult(messageList, BattleOutcome.ONGOING);
        }

        target.getMokeponStatus().addEffect(new BlindEffect());

        messageList.add("\tPocket sand blinded " + target.getName() + "!\n\tTheir targeted skills may miss for 3 turns.\n");
        return new BattleResult(messageList, BattleOutcome.ONGOING);
    }

    @Override
    public int getEnergyCost() {
        return 4;
    }
}


