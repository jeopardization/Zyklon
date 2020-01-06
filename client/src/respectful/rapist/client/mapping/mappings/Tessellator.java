package respectful.rapist.client.mapping.mappings;

import respectful.rapist.client.mapping.MappedClass;

public class Tessellator extends MappedClass {
    public Tessellator() {
        super("net.minecraft.client.renderer.Tessellator");
    }

    public Object getInstance() {
        try {
            return clazz.getDeclaredField("field_78398_a").get(null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void startDrawingQuads(Object obj) {
        try {
            clazz.getDeclaredMethod("func_78382_b").invoke(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setColorRGBA_F(Object obj, float r, float g, float b, float a) {
        try {
            clazz.getDeclaredMethod("func_78369_a", float.class, float.class, float.class, float.class).invoke(obj, r, g, b, a);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void addVertex(Object obj, double x, double y, double z) {
        try {
            clazz.getDeclaredMethod("func_78377_a", double.class, double.class, double.class).invoke(obj, x, y, z);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void draw(Object obj) {
        try {
            clazz.getDeclaredMethod("func_78381_a").invoke(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
