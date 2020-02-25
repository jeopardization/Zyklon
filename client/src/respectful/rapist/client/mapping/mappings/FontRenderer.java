package respectful.rapist.client.mapping.mappings;

import respectful.rapist.client.mapping.MappedClass;

import java.lang.reflect.Method;

public class FontRenderer extends MappedClass {
    private Method drawStringWithShadow, getStringWidth;

    public FontRenderer() {
        super("net.minecraft.client.gui.FontRenderer");
        try {
            drawStringWithShadow = clazz.getDeclaredMethod("func_78261_a", String.class, int.class, int.class, int.class);
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
