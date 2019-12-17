package respectful.rapist.client.mapping.mappings;

import respectful.rapist.client.mapping.MappedClass;
import respectful.rapist.client.mapping.Mappings;

public class Minecraft extends MappedClass {
    public Minecraft() {
        super("net.minecraft.client.Minecraft");
    }

    public Object getThePlayer() {
        try {
            return clazz.getDeclaredField("field_71439_g").get(getMinecraft());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Object getTheWorld() {
        try {
            return clazz.getDeclaredField("field_71441_e").get(getMinecraft());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Object getCurrentScreen() {
        try {
            clazz.getDeclaredField("field_71462_r").get(getMinecraft());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Object getGameSettings() {
        try {
            return clazz.getDeclaredField("field_71474_y").get(getMinecraft());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Object getPlayerController() {
        try {
            return clazz.getDeclaredField("field_71442_b").get(getMinecraft());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Object getFontRenderer() {
        try {
            return clazz.getDeclaredField("field_71466_p").get(getMinecraft());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Object getObjectMouseOver() {
        try {
            return clazz.getDeclaredField("field_71476_x").get(getMinecraft());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Object getMinecraft() {
        try {
            return clazz.getDeclaredMethod("func_71410_x").invoke(null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void displayGuiScreen(Object guiScreen) {
        try {
            clazz.getDeclaredMethod("func_147108_a", Mappings.GuiScreen.clazz).invoke(getMinecraft(), guiScreen == null ? null : Mappings.GuiScreen.clazz.cast(guiScreen));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public int getDisplayWidth() {
        try {
            return (int) clazz.getDeclaredField("field_71443_c").get(getMinecraft());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public int getDisplayHeight() {
        try {
            return (int) clazz.getDeclaredField("field_71440_d").get(getMinecraft());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }
}
