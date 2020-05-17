package respectful.rapist.client.util;

import org.lwjgl.input.Mouse;
import respectful.rapist.client.EventManager;
import respectful.rapist.client.mapping.Mappings;
import respectful.rapist.client.module.Module;

import java.io.InputStream;
import java.net.URL;
import java.util.Comparator;

public class Config implements Mappings {
    public static int[] stringToIntArr(String str) {
        String[] arr = str.split(",");
        int[] intArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            intArr[i] = Integer.parseInt(arr[i]);
        }
        return intArr;
    }

    public static boolean safe(boolean reqItem, int[] itemWhitelist, boolean reqMouse, boolean reqSprint, boolean reqFocus) {
        boolean itemCheck = true, mouseCheck = true, sprintCheck = true, focusCheck = true;
        if (reqItem) {
            if (InventoryPlayer.getCurrentItem(EntityPlayer.getInventory(Minecraft.getThePlayer())) == null) {
                itemCheck = false;
            } else {
                for (int ID : itemWhitelist) {
                    if (ID == Item.getIdFromItem(ItemStack.getItem(InventoryPlayer.getCurrentItem(EntityPlayer.getInventory(Minecraft.getThePlayer()))))) {
                        itemCheck = true;
                        break;
                    } else {
                        itemCheck = false;
                    }
                }
            }
        }
        if (reqMouse) {
            mouseCheck = Mouse.isButtonDown(0);
        }
        if (reqSprint) {
            sprintCheck = Entity.isSprinting(Minecraft.getThePlayer());
        }
        if (reqFocus) {
            focusCheck = Minecraft.getCurrentScreen() == null;
        }
        return itemCheck && mouseCheck && sprintCheck && focusCheck;
    }

    public static void setEnabledCloud(Module module, boolean enabled) {
        try {
            URL URL = new URL("http://localhost:1337/setenabled/" + module.name + "/" + (enabled ? 1 : 0));
            InputStream inputStream = URL.openStream();
            inputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static class Target implements Comparable<Target> {
        public static Comparator<Target> distComparator = Comparator.comparing(target -> Float.toString(target.dist)), healthComparator = Comparator.comparing(target -> Float.toString(target.health));
        private final float FOV;
        private final float dist;
        private final float health;
        public Object target;

        public Target(Object target, float FOV, float dist) {
            this.target = target;
            this.FOV = FOV;
            this.dist = dist;
            this.health = EntityLivingBase.getHealth(target);
        }

        public static Target check(Object entityPlayer, float maxDist, int maxFOV) {
            if (entityPlayer != null) {
                float[] rotations = Angle.findNeededRotations(Entity.getPosX(entityPlayer) - Entity.getPosX(Minecraft.getThePlayer()), Entity.getPosZ(entityPlayer) - Entity.getPosZ(Minecraft.getThePlayer()), Entity.getPosY(entityPlayer) - Entity.getPosY(Minecraft.getThePlayer()) + 1.0D, Entity.getDistanceToEntity(Minecraft.getThePlayer(), EntityPlayer.clazz.cast(entityPlayer)));
                float dist = Entity.getDistanceToEntity(Minecraft.getThePlayer(), entityPlayer);
                return ((!EntityPlayer.clazz.cast(entityPlayer).equals(Minecraft.getThePlayer()) && dist <= maxDist && Angle.isWithinFOV(rotations, maxFOV) && EntityLivingBase.isEntityAlive(entityPlayer) && !Entity.isInvisible(entityPlayer) && !EventManager.playerManager.isFriend(EntityPlayer.getCommandSenderName(entityPlayer)))) ? new Target(entityPlayer, Math.abs(rotations[0]) + Math.abs(rotations[1]), dist) : null;
            }
            return null;
        }

        @Override
        public int compareTo(Target target) {
            return Float.toString(this.FOV).compareTo(Float.toString(target.FOV));
        }
    }
}
