package respectful.rapist.client.mapping.mappings;

import respectful.rapist.client.mapping.MappedClass;
import respectful.rapist.client.mapping.Mappings;

import java.lang.reflect.Method;

public class EntityLivingBase extends MappedClass implements Mappings {
    private Method isEntityAlive, getHealth, knockBack;

    public EntityLivingBase() {
        super("net.minecraft.entity.EntityLivingBase");
        try {
            isEntityAlive = clazz.getDeclaredMethod("func_70089_S");
            getHealth = clazz.getDeclaredMethod("func_110143_aJ");
            knockBack = clazz.getDeclaredMethod("func_70653_a", Entity.clazz, float.class, double.class, double.class);
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
