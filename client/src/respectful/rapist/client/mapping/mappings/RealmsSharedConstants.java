package respectful.rapist.client.mapping.mappings;

import respectful.rapist.client.mapping.MappedClass;

import java.lang.reflect.Field;

public class RealmsSharedConstants extends MappedClass {
    private Field version;

    public RealmsSharedConstants() {
        super("net.minecraft.realms.RealmsSharedConstants");
        try {
            version = clazz.getDeclaredField("VERSION_STRING");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String getVersion() {
        try {
            return (String) version.get(null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
