package au.edu.unimelb.swen.oop.mokepon.driven.model.skill.util;

import au.edu.unimelb.swen.oop.mokepon.util.random.JavaRandomSource;
import au.edu.unimelb.swen.oop.mokepon.util.random.RandomSource;

public class GainCalculator {

    private static final RandomSource randomSource = new JavaRandomSource();

    public static int calculate(int start, int range) {
        return randomSource.nextInt(start, range);
    }


}
