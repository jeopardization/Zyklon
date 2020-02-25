package respectful.rapist.client.mapping.mappings;

import respectful.rapist.client.mapping.MappedClass;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class InventoryPlayer extends MappedClass {
    private Method getCurrentItem;
    private Field currentItem, mainInventory;

    public InventoryPlayer() {
        super("net.minecraft.entity.player.InventoryPlayer");
        try {
            getCurrentItem = clazz.getDeclaredMethod("func_70448_g");
            currentItem = clazz.getDeclaredField("field_70461_c");
            mainInventory = clazz.getDeclaredField("field_70462_a");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Object getCurrentItem(Object obj) {
        try {
            return getCurrentItem.invoke(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void setCurrentItem(Object obj, int slot) {
        try {
            currentItem.setInt(obj, slot);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Object[] getMainInventory(Object obj) {
        try {
            Object[] mainInventory = new Object[36];
            for (int i = 0; i < mainInventory.length; i++) {
                mainInventory[i] = Array.get(this.mainInventory.get(obj), i);
            }
            return mainInventory;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
