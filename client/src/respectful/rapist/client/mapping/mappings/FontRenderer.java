package respectful.rapist.client.mapping.mappings;

import respectful.rapist.client.mapping.MappedClass;
import respectful.rapist.client.mapping.Mappings;

import java.lang.reflect.Method;

public class FontRenderer extends MappedClass implements Mappings {
    private Method drawStringWithShadow, getStringWidth;

    public FontRenderer() {
        super("net.minecraft.client.gui.FontRenderer");
        try {
            drawStringWithShadow = RealmsSharedConstants.getVersion().equals("1.8.9") ? clazz.getDeclaredMethod("func_175063_a", String.class, float.class, float.class, int.class) : clazz.getDeclaredMethod("func_78261_a", String.class, int.class, int.class, int.class);
            getStringWidth = clazz.getDeclaredMethod("func_78256_a", String.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public int drawStringWithShadow(Object obj, String text, int x, int y, String color) {
        try {
            return (int) drawStringWithShadow.invoke(obj, text, x, y, Integer.parseInt(color, 16));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public int getStringWidth(Object obj, String text) {
        try {
            return (int) getStringWidth.invoke(obj, text);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }
}
