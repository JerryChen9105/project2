package au.edu.unimelb.swen.oop.mokepon.driven.model.skill;

import au.edu.unimelb.swen.oop.mokepon.driven.battle.BattleContext;
import au.edu.unimelb.swen.oop.mokepon.driven.battle.BattleOutcome;
import au.edu.unimelb.swen.oop.mokepon.driven.battle.BattleResult;
import au.edu.unimelb.swen.oop.mokepon.driven.model.Mokepon;
import au.edu.unimelb.swen.oop.mokepon.util.random.JavaRandomSource;
import au.edu.unimelb.swen.oop.mokepon.util.random.RandomSource;

import java.util.ArrayList;

public class WaitSkill implements Skill {

    private static final RandomSource randomSource = new JavaRandomSource();

    private static final String[] logTextArr = {
            "They get a little more bored.",
            "They wonder if this is a good strategy.",
            "They think about what they will have for dinner."
    };


    @Override
    public String getName() {
        return "Wait";
    }

    @Override
    public BattleResult execute(Mokepon user, Mokepon target, BattleContext battleContext, double damageMult) {
        ArrayList<String> messageList = new ArrayList<>();
        messageList.add(user.getMokeponStatus().getPlayerID() + "'s " + user.getName() + " used Wait!\n");
        if (user.getMokeponStatus().getCurrentEnergy() >= user.getMaxEnergy()) {
            messageList.add("\tBut their energy is filled.\n");
            return new BattleResult(messageList, BattleOutcome.ONGOING);
        }
        int index = randomSource.nextInt(0, 3);
        messageList.add("\t" + logTextArr[index] + "\n");
        return new BattleResult(messageList, BattleOutcome.ONGOING);
    }

    @Override
    public int getEnergyCost() {
        return -2;
    }
}


