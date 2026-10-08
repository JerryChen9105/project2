package au.edu.unimelb.swen.oop.mokepon.driven.model.skill.util;

import au.edu.unimelb.swen.oop.mokepon.driven.model.MokeponStatus;

public class DamageCalculator {


    public static int calculate(MokeponStatus selfStatus, MokeponStatus otherStatus, double damageMultplier) {
        return (int) (damageMultplier * 50 * selfStatus.getCurrentStrength() / otherStatus.getCurrentDefence());
    }


}
