package respectful.rapist.client.mapping.mappings;

import respectful.rapist.client.mapping.MappedClass;

import java.lang.reflect.Method;

public class EntityLivingBase extends MappedClass {
    private Method isEntityAlive, getHealth;

    public EntityLivingBase() {
        super("net.minecraft.entity.EntityLivingBase");
        try {
            isEntityAlive = clazz.getDeclaredMethod("func_70089_S");
            getHealth = clazz.getDeclaredMethod("func_110143_aJ");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public boolean isEntityAlive(Object entity) {
        try {
            return (boolean) isEntityAlive.invoke(entity);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public float getHealth(Object entity) {
        try {
            return (float) getHealth.invoke(entity);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0.0F;
    }
}
