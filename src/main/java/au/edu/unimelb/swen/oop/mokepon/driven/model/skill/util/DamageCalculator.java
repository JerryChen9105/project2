package au.edu.unimelb.swen.oop.mokepon.driven.model.skill.util;

import au.edu.unimelb.swen.oop.mokepon.driven.model.Mokepon;

public class DamageCalculator {


    public static int calculate(Mokepon self, Mokepon other, double damageMultplier) {
        return (int) (damageMultplier * 50 * self.getCurrentStrength() / other.getCurrentDefence());
    }


}
