package respectful.rapist.client.module.modules.combat;

import respectful.rapist.client.EventManager;
import respectful.rapist.client.mapping.Mappings;
import respectful.rapist.client.module.Module;
import respectful.rapist.client.util.Config;
import respectful.rapist.client.util.Random;
import respectful.rapist.client.util.Timer;

import java.util.ArrayList;
import java.util.Collections;

public class Aimbot extends Module {
    public float maxYawSmooth = 11.0F, minYawSmooth = 10.0F, maxPitchSmooth = 13.0F, minPitchSmooth = 12.0F, dist = 4.0F, FOV = 45.0F, randX, randY, randZ;
    public int minRand = 200, maxRand = 250, targetSelection = 0;
    public boolean reqItem, reqMouse;
    public int[] itemWhitelist = {267, 276, 272, 283, 268};
    public Object target;
    private Timer timer = new Timer();

    public Aimbot() {
        super(21, "Aimbot", "FF0000");
    }

    @Override
    public void onTick() {
        if (Config.safe(reqItem, itemWhitelist, reqMouse)) {
            if (!canTarget(target)) {
                target = null;
            }
            if (target == null) {
                ArrayList<Object> targets = new ArrayList<>();
                for (Object entityPlayer : Mappings.World.getPlayerEntities(Mappings.Minecraft.getTheWorld())) {
                    if (canTarget(entityPlayer)) {
                        targets.add(entityPlayer);
                    }
                }
                if (!targets.isEmpty()) {
                    ArrayList<Float> values = new ArrayList<>();
                    switch (targetSelection) {
                        case 0 /*Distance*/:
                            for (Object distTarget : targets) {
                                values.add(Mappings.Entity.getDistanceToEntity(Mappings.Minecraft.getThePlayer(), distTarget));
                            }
                            Collections.sort(values);
                            for (Object distTarget : targets) {
                                if (Mappings.Entity.getDistanceToEntity(Mappings.Minecraft.getThePlayer(), distTarget) == values.get(0)) {
                                    target = distTarget;
                                }
                            }
                            break;
                        case 1 /*Lowest Health*/:
                            for (Object healthTarget : targets) {
                                values.add(Mappings.EntityLivingBase.getHealth(healthTarget));
                            }
                            Collections.sort(values);
                            for (Object healthTarget : targets) {
                                if (Mappings.EntityLivingBase.getHealth(healthTarget) == values.get(0)) {
                                    target = healthTarget;
                                }
                            }
                            break;
                        case 2 /*Random*/:
                            target = targets.get(Random.nextInt(0, targets.size()));
                            break;
                    }
                }
            }
            if (target != null) {
                double x = Mappings.Entity.getPosX(target) - Mappings.Entity.getPosX(Mappings.Minecraft.getThePlayer()), y = Mappings.Entity.getPosZ(target) - Mappings.Entity.getPosZ(Mappings.Minecraft.getThePlayer()), z = Mappings.Entity.getPosY(target) - Mappings.Entity.getPosY(Mappings.Minecraft.getThePlayer()) + 1;
                if (timer.elapsed(Random.nextInt(minRand, maxRand))) {
                    randX = (float) (Random.nextDouble(x - 0.15D, x + 0.15D));
                    randY = (float) (Random.nextDouble(y - 0.15D, y + 0.15D));
                    randZ = (float) (Random.nextDouble(z - 0.15D, z + 0.15D));
                    timer = new Timer();
                }
                float yaw = (float) ((Math.atan(randY / randX)) * (180.0F / Math.PI)), pitch = (float) (-1 * ((Math.asin(randZ / Mappings.Entity.getDistanceToEntity(Mappings.Minecraft.getThePlayer(), Mappings.EntityPlayer.clazz.cast(target))) * (180.0F / Math.PI))));
                yaw += (randX < 0.0D) ? 90.0F : -90.0F;
                yaw = findNormalizedAngle(yaw);
                float neededRotationYaw = yaw - findNormalizedAngle(Mappings.Entity.getRotationYaw(Mappings.Minecraft.getThePlayer())), neededRotationPitch = pitch - Mappings.Entity.getRotationPitch(Mappings.Minecraft.getThePlayer()), coterminal = findCoterminalAngle(neededRotationYaw);
                if (Math.abs(coterminal) < Math.abs(neededRotationYaw)) {
                    neededRotationYaw = coterminal;
                }
                if (Math.abs(neededRotationYaw) <= FOV && Math.abs(neededRotationPitch) <= FOV) {
                    Mappings.Entity.setRotationYaw(Mappings.Minecraft.getThePlayer(), Mappings.Entity.getRotationYaw(Mappings.Minecraft.getThePlayer()) + (neededRotationYaw / (Random.nextFloat(minYawSmooth, maxYawSmooth) * 50)));
                    Mappings.Entity.setRotationPitch(Mappings.Minecraft.getThePlayer(), Mappings.Entity.getRotationPitch(Mappings.Minecraft.getThePlayer()) + (neededRotationPitch / (Random.nextFloat(minPitchSmooth, maxPitchSmooth) * 50)));
                }
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

    private float findCoterminalAngle(float angle) {
        return (angle > 0.0F) ? (angle - 360.0F) : (angle + 360.0F);
    }

    private float findNormalizedAngle(float angle) {
        return (Math.abs(angle) > 360.0F) ? angle - (int) (angle / 360.0F) * 360.0F : angle;
    }

    private boolean canTarget(Object entityPlayer) {
        return entityPlayer != null && (!Mappings.EntityPlayer.clazz.cast(entityPlayer).equals(Mappings.Minecraft.getThePlayer()) && Mappings.Entity.getDistanceToEntity(Mappings.Minecraft.getThePlayer(), entityPlayer) <= dist && Mappings.EntityLivingBase.isEntityAlive(entityPlayer) && !Mappings.Entity.isInvisible(entityPlayer) && !EventManager.playerManager.isFriend(Mappings.EntityPlayer.getCommandSenderName(entityPlayer)));
    }
}
