package respectful.rapist.loader.mapping.mappings;

import respectful.rapist.loader.mapping.MappedClass;

import java.lang.reflect.Field;

public class HitBoxes extends MappedClass {
    private Field add;

    public HitBoxes() {
        super("respectful.rapist.client.module.modules.combat.HitBoxes");
        try {
            add = clazz.getDeclaredField("add");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public float getAdd() {
        try {
            return add.getFloat(null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0.0F;
    }
}
