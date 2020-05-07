package respectful.rapist.client.mapping.mappings;

import respectful.rapist.client.mapping.MappedClass;
import respectful.rapist.client.mapping.Mappings;

import java.lang.reflect.Field;

public class DefaultVertexFormats extends MappedClass implements Mappings {
    private Field POSITION_COLOR;

    public DefaultVertexFormats() {
        super("net.minecraft.client.renderer.vertex.DefaultVertexFormats");
        try {
            if (RealmsSharedConstants.getVersion().equals("1.8.9")) {
                POSITION_COLOR = clazz.getDeclaredField("field_181706_f");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Object getPOSITION_COLOR() {
        try {
            return POSITION_COLOR.get(null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
