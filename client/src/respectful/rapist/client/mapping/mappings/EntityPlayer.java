package respectful.rapist.client.mapping.mappings;

import respectful.rapist.client.mapping.MappedClass;

public class EntityPlayer extends MappedClass {
    public EntityPlayer() {
        super("net.minecraft.entity.player.EntityPlayer");
    }

    public Object getInventory(Object obj) {
        try {
            return clazz.getDeclaredField("field_71071_by").get(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
