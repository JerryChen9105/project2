package au.edu.unimelb.swen.oop.mokepon.driven.model.skill.util;

import au.edu.unimelb.swen.oop.mokepon.driven.model.MokeponStatus;
import au.edu.unimelb.swen.oop.mokepon.util.random.JavaRandomSource;
import au.edu.unimelb.swen.oop.mokepon.util.random.RandomSource;

public class HitCalculator {

    private static final RandomSource randomSource = new JavaRandomSource();

    public static boolean check(MokeponStatus selfStatus, MokeponStatus otherStatus) {
        double hitProb = Math.pow(selfStatus.getCurrentAccuracy(), 2) / (Math.pow(selfStatus.getCurrentAccuracy(), 2) + Math.pow(otherStatus.getCurrentDodge(), 2));
        return hitProb >= randomSource.nextDouble(0.0, 1.0);
    }


}
