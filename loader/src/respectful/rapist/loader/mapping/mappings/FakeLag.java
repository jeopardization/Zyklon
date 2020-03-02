package respectful.rapist.loader.mapping.mappings;

import respectful.rapist.loader.mapping.MappedClass;

import java.lang.reflect.Method;

public class FakeLag extends MappedClass {
    private Method sleep;

    public FakeLag() {
        super("respectful.rapist.client.module.modules.misc.FakeLag");
        try {
            sleep = clazz.getDeclaredMethod("sleep");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void sleep() {
        try {
            sleep.invoke(null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
