package respectful.rapist.client.mapping.mappings;

import respectful.rapist.client.mapping.MappedClass;
import respectful.rapist.client.mapping.Mappings;

import java.lang.reflect.Method;

public class WorldRenderer extends MappedClass implements Mappings {
    private Method begin, pos, color, endVertex;

    public WorldRenderer() {
        super("net.minecraft.client.renderer.WorldRenderer");
        try {
            if (RealmsSharedConstants.getVersion().equals("1.8.9")) {
                begin = clazz.getDeclaredMethod("func_181668_a", int.class, VertexFormat.clazz);
                pos = clazz.getDeclaredMethod("func_181662_b", double.class, double.class, double.class);
                color = clazz.getDeclaredMethod("func_181666_a", float.class, float.class, float.class, float.class);
                endVertex = clazz.getDeclaredMethod("func_181675_d");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void begin(Object obj, int glMode, Object format) {
        try {
            begin.invoke(obj, glMode, format);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Object pos(Object obj, double x, double y, double z) {
        try {
            return pos.invoke(obj, x, y, z);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Object color(Object obj, float red, float green, float blue, float alpha) {
        try {
            return color.invoke(obj, red, green, blue, alpha);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void endVertex(Object obj) {
        try {
            endVertex.invoke(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
