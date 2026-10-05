package au.edu.unimelb.swen.oop.mokepon.driven.model.skill.util;

import au.edu.unimelb.swen.oop.mokepon.driven.model.Mokepon;
import au.edu.unimelb.swen.oop.mokepon.util.random.JavaRandomSource;
import au.edu.unimelb.swen.oop.mokepon.util.random.RandomSource;

public class HitCalculator {

    private static final RandomSource randomSource = new JavaRandomSource();

    public static boolean check(Mokepon self, Mokepon other) {
        double hitProb = Math.pow(self.getCurrentAccuracy(), 2) / (Math.pow(self.getCurrentAccuracy(), 2) + Math.pow(other.getCurrentDodge(), 2));
        return hitProb >= randomSource.nextDouble(0.0, 1.0);
    }


}
