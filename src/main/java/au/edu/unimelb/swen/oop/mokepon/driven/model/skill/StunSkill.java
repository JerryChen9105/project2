package au.edu.unimelb.swen.oop.mokepon.driven.model.skill;

import au.edu.unimelb.swen.oop.mokepon.driven.battle.BattleContext;
import au.edu.unimelb.swen.oop.mokepon.driven.battle.BattleOutcome;
import au.edu.unimelb.swen.oop.mokepon.driven.battle.BattleResult;
import au.edu.unimelb.swen.oop.mokepon.driven.model.Mokepon;
import au.edu.unimelb.swen.oop.mokepon.driven.model.effect.BlindEffect;
import au.edu.unimelb.swen.oop.mokepon.driven.model.effect.StunEffect;
import au.edu.unimelb.swen.oop.mokepon.driven.model.skill.util.HitCalculator;

import java.util.ArrayList;

public class StunSkill implements Skill {

    @Override
    public String getName() {
        return "Stun";
    }

    @Override
    public BattleResult execute(Mokepon user, Mokepon target, BattleContext battleContext, double damageMult) {
        boolean hit = HitCalculator.check(user.getMokeponStatus(), target.getMokeponStatus());
        ArrayList<String> messageList = new ArrayList<>();
        messageList.add(user.getMokeponStatus().getPlayerID() + "'s " + user.getName() + " used Stun!\n");

        if (!hit) {
            messageList.add("\tBut they missed.\n");
            return new BattleResult(messageList, BattleOutcome.ONGOING);
        }

        target.getMokeponStatus().addEffect(new StunEffect());

        messageList.add("\t" + target.getName() + " was stunned! Their damage is halved for 3 turns.\n");
        return new BattleResult(messageList, BattleOutcome.ONGOING);
    }

    @Override
    public int getEnergyCost() {
        return 4;
    }
}


