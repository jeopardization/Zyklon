package respectful.rapist.client.module.modules.combat;

import respectful.rapist.client.mapping.Mappings;
import respectful.rapist.client.module.Module;
import respectful.rapist.client.util.Angle;
import respectful.rapist.client.util.Config;
import respectful.rapist.client.util.Random;
import respectful.rapist.client.util.Timer;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;

public class Aimbot extends Module implements Mappings {
    public float maxYawSmooth = 8.0F, minYawSmooth = 6.0F, maxPitchSmooth = 20.0F, minPitchSmooth = 16.0F, dist = 4.0F;
    public int FOV = 90, minRand = 150, maxRand = 350, delay = Random.nextInt(minRand, maxRand), targetSelection = 0;
    public boolean reqItem, reqMouse;
    public int[] itemWhitelist = {267, 276, 272, 283, 268};
    public Object target;
    private double randX, randY, randZ;
    private Timer timer = new Timer();

    public Aimbot() {
        super(21, "Aimbot", "D63031");
    }

    @Override
    public void onTick() {
        if (Config.safe(reqItem, itemWhitelist, reqMouse, false, true)) {
            Config.Target target = Config.Target.check(this.target, dist, FOV);
            this.target = (target != null) ? target.target : null;
            if (this.target == null) {
                ArrayList<Config.Target> targets = new ArrayList<>();
                for (Object entityPlayer : World.getPlayerEntities(Minecraft.getTheWorld())) {
                    target = Config.Target.check(entityPlayer, dist, FOV);
                    if (target != null) {
                        targets.add(target);
                    }
                }
                if (!targets.isEmpty()) {
                    switch (targetSelection) {
                        case 0 /*FOV*/:
                            Collections.sort(targets);
                            this.target = targets.get(0).target;
                            break;
                        case 1 /*Distance*/:
                            targets.sort(Config.Target.distComparator);
                            this.target = targets.get(0).target;
                            break;
                        case 2 /*Lowest Health*/:
                            targets.sort(Config.Target.healthComparator);
                            this.target = targets.get(0).target;
                            break;
                        case 3 /*Random*/:
                            this.target = targets.get(new SecureRandom().nextInt(targets.size())).target;
                            break;
                    }
                }
            }
            if (this.target != null) {
                if (timer.elapsed(delay)) {
                    randX = Random.nextDouble(-0.15D, 0.15D);
                    randY = Random.nextDouble(-0.15D, 0.15D);
                    randZ = Random.nextDouble(-0.15D, 0.15D);
                    delay = Random.nextInt(minRand, maxRand);
                    timer = new Timer();
                }
                double x = Entity.getPosX(this.target) - Entity.getPosX(Minecraft.getThePlayer()) + randX, y = Entity.getPosZ(this.target) - Entity.getPosZ(Minecraft.getThePlayer()) + randY, z = Entity.getPosY(this.target) - Entity.getPosY(Minecraft.getThePlayer()) + randZ;
                if (RealmsSharedConstants.getVersion().equals("1.8.9")) {
                    z -= 1.62D;
                }
                ArrayList<AimPoint> aimPoints = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    aimPoints.add(new AimPoint(z + ((i + 1) * (0.3))));
                }
                Collections.sort(aimPoints);
                z = aimPoints.get(0).point;
                float[] neededRotations = Angle.findNeededRotations(x, y, z, Entity.getDistanceToEntity(Minecraft.getThePlayer(), EntityPlayer.clazz.cast(this.target)));
                Entity.setRotationYaw(Minecraft.getThePlayer(), Entity.getRotationYaw(Minecraft.getThePlayer()) + (neededRotations[0] / (Random.nextFloat(minYawSmooth, maxYawSmooth) * 50)));
                Entity.setRotationPitch(Minecraft.getThePlayer(), Entity.getRotationPitch(Minecraft.getThePlayer()) + (neededRotations[1] / (Random.nextFloat(minPitchSmooth, maxPitchSmooth) * 50)));
            }
        } else {
            target = null;
        }
    }

    @Override
    public void disable() {
        target = null;
        super.disable();
    }

    private static class AimPoint implements Comparable<AimPoint> {
        double dist, point;

        public AimPoint(double point) {
            dist = 1.62 - Math.abs(point);
            this.point = point;
        }

        @Override
        public int compareTo(AimPoint aimPoint) {
            return Double.toString(aimPoint.dist).compareTo(Double.toString(this.dist));
        }
    }
}
