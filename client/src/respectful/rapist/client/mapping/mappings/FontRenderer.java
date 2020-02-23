package respectful.rapist.client.mapping.mappings;

import respectful.rapist.client.mapping.MappedClass;

public class FontRenderer extends MappedClass {
    public FontRenderer() {
        super("net.minecraft.client.gui.FontRenderer");
    }

    public int drawStringWithShadow(Object obj, String text, int x, int y, String color) {
        try {
            return (int) clazz.getDeclaredMethod("func_78261_a", String.class, int.class, int.class, int.class).invoke(obj, text, x, y, Integer.parseInt(color, 16));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public int drawStringWithShadow(Object obj, String text, int x, int y, int color) {
        try {
            return (int) clazz.getDeclaredMethod("func_78261_a", String.class, int.class, int.class, int.class).invoke(obj, text, x, y, color);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public int getStringWidth(Object obj, String text) {
        try {
            return (int) clazz.getDeclaredMethod("func_78256_a", String.class).invoke(obj, text);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }
}
