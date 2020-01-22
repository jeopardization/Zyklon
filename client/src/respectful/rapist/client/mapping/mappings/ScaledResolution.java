package respectful.rapist.client.mapping.mappings;

import respectful.rapist.client.mapping.MappedClass;
import respectful.rapist.client.mapping.Mappings;

public class ScaledResolution extends MappedClass implements Mappings {
    public ScaledResolution() {
        super("net.minecraft.client.gui.ScaledResolution");
    }

    public Object newInstance(Object minecraft, int width, int height) {
        try {
            return clazz.getDeclaredConstructor(Minecraft.clazz, int.class, int.class).newInstance(minecraft, width, height);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public int getScaledWidth(Object obj) {
        try {
            return (int) clazz.getDeclaredMethod("func_78326_a").invoke(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }
}
