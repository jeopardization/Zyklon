package respectful.rapist.loader.mapping.mappings;

import respectful.rapist.loader.mapping.MappedClass;

import java.lang.reflect.Method;

public class Velocity extends MappedClass {
    private Method intercept;

    public Velocity() {
        super("respectful.rapist.client.module.modules.combat.Velocity");
        try {
            intercept = clazz.getDeclaredMethod("intercept", Object.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void intercept(Object packet) {
        try {
            intercept.invoke(null, packet);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
