package respectful.rapist.client.mapping.mappings;

import respectful.rapist.client.mapping.MappedClass;
import respectful.rapist.client.mapping.Mappings;

import java.lang.reflect.Field;

public class RenderManager extends MappedClass implements Mappings {
    private Field instance, playerViewY, playerViewX;

    public RenderManager() {
        super("net.minecraft.client.renderer.entity.RenderManager");
        try {
            if (RealmsSharedConstants.getVersion().equals("1.7.10")) {
                instance = clazz.getDeclaredField("field_78727_a");
            }
            playerViewY = clazz.getDeclaredField("field_78735_i");
            playerViewX = clazz.getDeclaredField("field_78732_j");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Object getInstance() {
        try {
            if (instance != null) {
                return instance.get(null);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public float getPlayerViewY(Object obj) {
        try {
            return playerViewY.getFloat(clazz.cast(obj));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0.0F;
    }

    public float getPlayerViewX(Object obj) {
        try {
            return playerViewX.getFloat(clazz.cast(obj));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0.0F;
    }
}
