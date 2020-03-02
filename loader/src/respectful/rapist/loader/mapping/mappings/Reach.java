package respectful.rapist.loader.mapping.mappings;

import respectful.rapist.loader.mapping.MappedClass;

import java.lang.reflect.Field;

public class Reach extends MappedClass {
    private Field add;

    public Reach() {
        super("respectful.rapist.client.module.modules.combat.Reach");
        try {
            add = clazz.getDeclaredField("add");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public double getAdd() {
        try {
            return add.getDouble(null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0.0F;
    }
}
