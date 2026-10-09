package au.edu.unimelb.swen.oop.mokepon.driven.model.effect;

import au.edu.unimelb.swen.oop.mokepon.driven.battle.BattleContext;
import au.edu.unimelb.swen.oop.mokepon.driven.model.MokeponStatus;

public class BleedEffect implements Effect {

    private int remainingTurn;
    private int userStrength;


    public BleedEffect(int userStrength) {
        this.remainingTurn = 2;
        this.userStrength = userStrength;
    }

    @Override
    public void onTurnEnd(MokeponStatus owner, BattleContext battleContext) {
        if (remainingTurn > 0) {
            int bleedDamage = (int) (0.5 * userStrength * 50 / owner.getCurrentDefence());
            owner.takeDamage(bleedDamage);
            remainingTurn--;
        }
        if (isExpired()) battleContext.log("The bleeding stopped.");
    }

    @Override
    public boolean isExpired() {
        return remainingTurn <= 0;
    }

    @Override
    public String getName() {
        return "Bleed";
    }
}


