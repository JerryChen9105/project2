package au.edu.unimelb.swen.oop.mokepon.battle.skill.util;

import au.edu.unimelb.swen.oop.mokepon.model.Mokepon;

public class DamageCalculator {


    public static int calculate(Mokepon self, Mokepon other, double damageMultplier) {
        return (int) (damageMultplier * 50 * self.getCurrentStrength() / other.getCurrentDefence());
    }


}
