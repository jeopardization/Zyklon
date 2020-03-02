package respectful.rapist.loader.mapping.mappings;

import respectful.rapist.loader.mapping.MappedClass;

import java.lang.reflect.Field;

public class Module extends MappedClass {
    private Field enabled;

    public Module() {
        super("respectful.rapist.client.module.Module");
        try {
            enabled = clazz.getDeclaredField("enabled");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public boolean getEnabled(Object obj) {
        try {
            return enabled.getBoolean(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
