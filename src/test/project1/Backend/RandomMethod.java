package project1.Backend;

import java.util.concurrent.ThreadLocalRandom;

public class RandomMethod {

    public static int randomInt(int start, int range) {
        int num = ThreadLocalRandom.current().nextInt(start, start + range);
        return num;
    }

    public static double randomDouble(double start, double range) {
        double num = ThreadLocalRandom.current().nextDouble(start, start + range);
        return num;
    }

    public static Object randomObject(Object[] objectSet)
    {
        return objectSet[randomInt(0, objectSet.length)];
    }

    private RandomMethod()
    {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

}
