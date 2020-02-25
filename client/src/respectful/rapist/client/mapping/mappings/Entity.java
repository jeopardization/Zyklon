package respectful.rapist.client.mapping.mappings;

import respectful.rapist.client.mapping.MappedClass;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Entity extends MappedClass {
    private Method isInvisible, isSneaking, getDistanceToEntity;
    private Field posX, posY, posZ, rotationYaw, rotationPitch;

    public Entity() {
        super("net.minecraft.entity.Entity");
        try {
            isInvisible = clazz.getDeclaredMethod("func_82150_aj");
            isSneaking = clazz.getDeclaredMethod("func_70093_af");
            getDistanceToEntity = clazz.getDeclaredMethod("func_70032_d", clazz);
            posX = clazz.getDeclaredField("field_70165_t");
            posY = clazz.getDeclaredField("field_70163_u");
            posZ = clazz.getDeclaredField("field_70161_v");
            rotationYaw = clazz.getDeclaredField("field_70177_z");
            rotationPitch = clazz.getDeclaredField("field_70125_A");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public boolean isInvisible(Object obj) {
        try {
            return (boolean) isInvisible.invoke(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean isSneaking(Object obj) {
        try {
            return (boolean) isSneaking.invoke(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public float getDistanceToEntity(Object obj, Object entity) {
        try {
            return (float) getDistanceToEntity.invoke(obj, entity);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0.0F;
    }

    public double getPosX(Object obj) {
        try {
            return posX.getDouble(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0.0D;
    }

    public double getPosY(Object obj) {
        try {
            return posY.getDouble(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0.0D;
    }

    public double getPosZ(Object obj) {
        try {
            return posZ.getDouble(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0.0D;
    }

    public float getRotationYaw(Object obj) {
        try {
            return rotationYaw.getFloat(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0.0F;
    }

    public void setRotationYaw(Object obj, float value) {
        if (!Float.isNaN(value)) {
            try {
                rotationYaw.setFloat(obj, value);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public float getRotationPitch(Object obj) {
        try {
            return rotationPitch.getFloat(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0.0F;
    }

    public void setRotationPitch(Object obj, float value) {
        if (!Float.isNaN(value)) {
            try {
                rotationPitch.setFloat(obj, value);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
