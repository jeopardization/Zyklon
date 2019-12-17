package respectful.rapist.client.util;

public class Random {
    private static java.util.Random random = new java.util.Random();

    public static int nextInt(int min, int max) {
        return (int) nextDouble(min, max);
    }

    public static float nextFloat(float min, float max) {
        return (float) nextDouble(min, max);
    }

    public static double nextDouble(double min, double max) {
        return min + (max - min) * random.nextDouble();
    }
}
