package au.edu.unimelb.swen.oop.mokepon.driven.model.skill.util;

import au.edu.unimelb.swen.oop.mokepon.driven.model.MokeponStatus;

public class DamageCalculator {

    public static int calculate(MokeponStatus selfStatus, MokeponStatus otherStatus, double damageMultplier) {
        int baseDamage = (int) (damageMultplier * 50 * selfStatus.getCurrentStrength() / otherStatus.getCurrentDefence());

        if (selfStatus.hasEffect("Stun")) {
            baseDamage /= 2;
        } // add else if branch

        return baseDamage;
    }


}

