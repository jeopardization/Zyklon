package respectful.rapist.client.mapping.mappings;

import respectful.rapist.client.mapping.MappedClass;

public class MovingObjectPosition extends MappedClass {
    public MovingObjectPosition() {
        super("net.minecraft.util.MovingObjectPosition");
    }

    public Object getEntityHit(Object obj) {
        try {
            return clazz.getDeclaredField("field_72308_g").get(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
