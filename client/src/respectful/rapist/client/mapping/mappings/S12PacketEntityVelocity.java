package respectful.rapist.client.mapping.mappings;

import respectful.rapist.client.mapping.MappedClass;

import java.lang.reflect.Field;

public class S12PacketEntityVelocity extends MappedClass {
    private Field ID, x, y, z;

    public S12PacketEntityVelocity() {
        super("net.minecraft.network.play.server.S12PacketEntityVelocity");
        try {
            ID = clazz.getDeclaredField("field_149417_a");
            ID.setAccessible(true);
            x = clazz.getDeclaredField("field_149415_b");
            x.setAccessible(true);
            y = clazz.getDeclaredField("field_149414_d");
            y.setAccessible(true);
            z = clazz.getDeclaredField("field_149416_c");
            z.setAccessible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public int getID(Object obj) {
        try {
            return ID.getInt(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public int getX(Object obj) {
        try {
            return x.getInt(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public void setX(Object obj, int value) {
        try {
            this.x.setInt(obj, value);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public int getY(Object obj) {
        try {
            return y.getInt(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public void setY(Object obj, int value) {
        try {
            this.y.setInt(obj, value);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public int getZ(Object obj) {
        try {
            return z.getInt(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public void setZ(Object obj, int value) {
        try {
            this.z.setInt(obj, value);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
