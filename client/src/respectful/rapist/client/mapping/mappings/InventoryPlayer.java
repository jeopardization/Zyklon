package respectful.rapist.client.mapping.mappings;

import respectful.rapist.client.mapping.MappedClass;

import java.lang.reflect.Array;

public class InventoryPlayer extends MappedClass {
    public InventoryPlayer() {
        super("net.minecraft.entity.player.InventoryPlayer");
    }

    public Object getCurrentItem(Object obj) {
        try {
            return clazz.getDeclaredMethod("func_70448_g").invoke(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void setCurrentItem(Object obj, int slot) {
        try {
            clazz.getDeclaredField("field_70461_c").setInt(obj, slot);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Object[] getMainInventory(Object obj) {
        try {
            Object[] mainInventory = new Object[36];
            for (int i = 0; i < mainInventory.length; i++) {
                mainInventory[i] = Array.get(clazz.getDeclaredField("field_70462_a").get(obj), i);
            }
            return mainInventory;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
