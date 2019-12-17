package respectful.rapist.client.mapping.mappings;

import respectful.rapist.client.mapping.MappedClass;
import respectful.rapist.client.mapping.Mappings;

public class Entity extends MappedClass {
    public Entity() {
        super("net.minecraft.entity.Entity");
    }

    public float getDistanceToEntity(Object obj, Object entity) {
        try {
            return (float) clazz.getDeclaredMethod("func_70032_d", clazz).invoke(obj, clazz.cast(entity));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0.0F;
    }

    public double getPosX(Object obj) {
        try {
            return clazz.getDeclaredField("field_70165_t").getDouble(Mappings.Entity.clazz.cast(obj));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0.0D;
    }

    public double getPosY(Object obj) {
        try {
            return clazz.getDeclaredField("field_70163_u").getDouble(Mappings.Entity.clazz.cast(obj));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0.0D;
    }

    public double getPosZ(Object obj) {
        try {
            return clazz.getDeclaredField("field_70161_v").getDouble(Mappings.Entity.clazz.cast(obj));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0.0D;
    }

    public float getRotationYaw(Object obj) {
        try {
            return clazz.getDeclaredField("field_70177_z").getFloat(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0.0F;
    }

    public void setRotationYaw(Object obj, float value) {
        try {
            clazz.getDeclaredField("field_70177_z").setFloat(obj, value);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public float getRotationPitch(Object obj) {
        try {
            return clazz.getDeclaredField("field_70125_A").getFloat(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0.0F;
    }

    public void setRotationPitch(Object obj, float value) {
        try {
            clazz.getDeclaredField("field_70125_A").setFloat(obj, value);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
