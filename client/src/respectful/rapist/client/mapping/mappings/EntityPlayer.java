package respectful.rapist.client.mapping.mappings;

import respectful.rapist.client.mapping.MappedClass;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class EntityPlayer extends MappedClass {
    private Method getCommandSenderName;
    private Field inventory;

    public EntityPlayer() {
        super("net.minecraft.entity.player.EntityPlayer");
        try {
            getCommandSenderName = clazz.getDeclaredMethod("func_70005_c_");
            inventory = clazz.getDeclaredField("field_71071_by");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String getCommandSenderName(Object obj) {
        try {
            return (String) getCommandSenderName.invoke(clazz.cast(obj));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Object getInventory(Object obj) {
        try {
            return inventory.get(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
