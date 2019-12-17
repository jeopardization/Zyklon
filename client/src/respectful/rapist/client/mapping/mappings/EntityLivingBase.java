package respectful.rapist.client.mapping.mappings;

import respectful.rapist.client.mapping.MappedClass;

public class EntityLivingBase extends MappedClass {
    public EntityLivingBase() {
        super("net.minecraft.entity.EntityLivingBase");
    }

    public boolean isEntityAlive(Object entity) {
        try {
            return (boolean) clazz.getDeclaredMethod("func_70089_S").invoke(clazz.cast(entity));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public float getHealth(Object entity) {
        try {
            return (float) clazz.getDeclaredMethod("func_110143_aJ").invoke(clazz.cast(entity));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0.0F;
    }
}
