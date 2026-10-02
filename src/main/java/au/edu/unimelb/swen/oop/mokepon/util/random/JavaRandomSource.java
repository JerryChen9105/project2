package au.edu.unimelb.swen.oop.mokepon.util.random;


import java.util.concurrent.ThreadLocalRandom;

public class JavaRandomSource implements RandomSource {

    @Override
    public int nextInt(int start, int range) {
        return ThreadLocalRandom.current().nextInt(start, start + range);
    }

    @Override
    public double nextDouble(double start, double range) {
        return ThreadLocalRandom.current().nextDouble(start, start + range);
    }
}
