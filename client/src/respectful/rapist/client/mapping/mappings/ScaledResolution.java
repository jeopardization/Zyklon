package respectful.rapist.client.mapping.mappings;

import respectful.rapist.client.mapping.MappedClass;
import respectful.rapist.client.mapping.Mappings;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class ScaledResolution extends MappedClass implements Mappings {
    private Constructor scaledResolution;
    private Method getScaledWidth;

    public ScaledResolution() {
        super("net.minecraft.client.gui.ScaledResolution");
        try {
            scaledResolution = clazz.getDeclaredConstructor(Minecraft.clazz, int.class, int.class);
            getScaledWidth = clazz.getDeclaredMethod("func_78326_a");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Object newInstance(Object minecraft, int width, int height) {
        try {
            return scaledResolution.newInstance(minecraft, width, height);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public int getScaledWidth(Object obj) {
        try {
            return (int) getScaledWidth.invoke(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }
}
