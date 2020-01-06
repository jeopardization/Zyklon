package respectful.rapist.client.mapping.mappings;

import respectful.rapist.client.mapping.MappedClass;

public class RenderManager extends MappedClass {
    public RenderManager() {
        super("net.minecraft.client.renderer.entity.RenderManager");
    }

    public Object getInstance() {
        try {
            return clazz.getDeclaredField("field_78727_a").get(null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public float getPlayerViewY(Object obj) {
        try {
            return clazz.getDeclaredField("field_78735_i").getFloat(clazz.cast(obj));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0.0F;
    }

    public float getPlayerViewX(Object obj) {
        try {
            return clazz.getDeclaredField("field_78732_j").getFloat(clazz.cast(obj));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0.0F;
    }
}
