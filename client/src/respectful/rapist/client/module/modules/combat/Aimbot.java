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
    public float maxYawSmooth = 8.0F, minYawSmooth = 6.0F, maxPitchSmooth = 20.0F, minPitchSmooth = 16.0F, dist = 4.0F, scale = 0.75F;
    public int FOV = 90, minDelay = 75, maxDelay = 150, delay = Random.nextInt(minDelay, maxDelay), minRand = 150, maxRand = 350, randDelay = Random.nextInt(minRand, maxRand), targetSelection = 0;
    public boolean reqItem, reqMouse;
    public int[] itemWhitelist = {267, 276, 272, 283, 268};
    public Object target;
    private double x, y, z, randX, randY, randZ;
    private Timer delayTimer = new Timer(), randDelayTimer = new Timer();

    public Aimbot() {
        super(21, "Aimbot", 0xD63031);
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
                if (randDelayTimer.elapsed(randDelay)) {
                    randX = Random.nextDouble(-0.12D, 0.12D);
                    randY = Random.nextDouble(-0.12D, 0.12D);
                    randZ = Random.nextDouble(-0.12D, 0.12D);
                    randDelay = Random.nextInt(minRand, maxRand);
                    randDelayTimer = new Timer();
                }
                if (delayTimer.elapsed(delay)) {
                    x = Entity.getPosX(this.target) - Entity.getPosX(Minecraft.getThePlayer()) + randX;
                    y = Entity.getPosZ(this.target) - Entity.getPosZ(Minecraft.getThePlayer()) + randY;
                    double z = Entity.getPosY(this.target) - Entity.getPosY(Minecraft.getThePlayer()) + randZ;
                    ArrayList<AimPoint> aimPoints = new ArrayList<>(Collections.singletonList(new AimPoint(z + 0.9D)));
                    for (int i = 1; i < 3; i++) {
                        aimPoints.add(new AimPoint(z + 0.9D + i * scale * 0.36D));
                        aimPoints.add(new AimPoint(z + 0.9D - i * scale * 0.36D));
                    }
                    Collections.sort(aimPoints);
                    this.z = aimPoints.get(0).point;
                    delay = Random.nextInt(minDelay, maxDelay);
                    delayTimer = new Timer();
                }
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
        private final double dist, point;

        public AimPoint(double point) {
            if (RealmsSharedConstants.getVersion().equals("1.8.9")) {
                point -= 1.62D;
            }
            dist = 1.62 - Math.abs(point);
            this.point = point;
        }

        @Override
        public int compareTo(AimPoint aimPoint) {
            return Double.toString(aimPoint.dist).compareTo(Double.toString(this.dist));
        }
    }
}
