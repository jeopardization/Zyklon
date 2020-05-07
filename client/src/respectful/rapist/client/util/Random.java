package respectful.rapist.client.util;

import java.security.SecureRandom;

public class Random {
    private static final SecureRandom secureRandom = new SecureRandom();

    public static int nextInt(int min, int max) {
        return (int) nextDouble(min, max);
    }

    public static float nextFloat(float min, float max) {
        return (float) nextDouble(min, max);
    }

    public static double nextDouble(double min, double max) {
        return min + (max - min) * secureRandom.nextDouble();
    }
}
