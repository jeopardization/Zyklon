package respectful.rapist.client.util;

import respectful.rapist.client.mapping.Mappings;

public class Angle implements Mappings {
    public static float[] findNeededRotations(double x, double y, double z, double radius) {
        float yaw = (float) Math.toDegrees(Math.atan(y / x)), pitch = (float) (-1 * Math.toDegrees(Math.asin(z / radius)));
        yaw += (x < 0.0D) ? 90.0F : -90.0F;
        yaw = findNormalized(yaw);
        float neededRotationYaw = yaw - findNormalized(Mappings.Entity.getRotationYaw(Minecraft.getThePlayer())), neededRotationPitch = pitch - Entity.getRotationPitch(Minecraft.getThePlayer()), coterminal = findCoterminal(neededRotationYaw);
        if (Math.abs(coterminal) < Math.abs(neededRotationYaw)) {
            neededRotationYaw = coterminal;
        }
        return new float[]{neededRotationYaw, neededRotationPitch};
    }

    public static boolean isWithinFOV(float[] neededRotations, int FOV) {
        return Math.abs(neededRotations[0]) <= FOV && Math.abs(neededRotations[1]) <= FOV;
    }

    private static float findCoterminal(float angle) {
        return (angle > 0.0F) ? (angle - 360.0F) : (angle + 360.0F);
    }

    private static float findNormalized(float angle) {
        return (Math.abs(angle) > 360.0F) ? angle - (int) (angle / 360.0F) * 360.0F : angle;
    }
}
