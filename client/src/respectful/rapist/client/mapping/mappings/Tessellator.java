package respectful.rapist.client.mapping.mappings;

import respectful.rapist.client.mapping.MappedClass;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Tessellator extends MappedClass {
    private Field instance;
    private Method startDrawingQuads, setColorRGBA_F, addVertex, draw;

    public Tessellator() {
        super("net.minecraft.client.renderer.Tessellator");
        try {
            instance = clazz.getDeclaredField("field_78398_a");
            startDrawingQuads = clazz.getDeclaredMethod("func_78382_b");
            setColorRGBA_F = clazz.getDeclaredMethod("func_78369_a", float.class, float.class, float.class, float.class);
            addVertex = clazz.getDeclaredMethod("func_78377_a", double.class, double.class, double.class);
            draw = clazz.getDeclaredMethod("func_78381_a");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Object getInstance() {
        try {
            return instance.get(null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void startDrawingQuads(Object obj) {
        try {
            startDrawingQuads.invoke(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setColorRGBA_F(Object obj, float r, float g, float b, float a) {
        try {
            setColorRGBA_F.invoke(obj, r, g, b, a);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void addVertex(Object obj, double x, double y, double z) {
        try {
            addVertex.invoke(obj, x, y, z);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void draw(Object obj) {
        try {
            draw.invoke(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
