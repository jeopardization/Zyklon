package respectful.rapist.client.mapping.mappings;

import respectful.rapist.client.mapping.MappedClass;
import respectful.rapist.client.mapping.Mappings;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Minecraft extends MappedClass implements Mappings {
    private Field thePlayer, theWorld, currentScreen, gameSettings, playerController, fontRenderer, displayWidth, displayHeight;
    private Method getMinecraft, displayGuiScreen;

    public Minecraft() {
        super("net.minecraft.client.Minecraft");
        try {
            thePlayer = clazz.getDeclaredField("field_71439_g");
            theWorld = clazz.getDeclaredField("field_71441_e");
            currentScreen = clazz.getDeclaredField("field_71462_r");
            gameSettings = clazz.getDeclaredField("field_71474_y");
            playerController = clazz.getDeclaredField("field_71442_b");
            fontRenderer = clazz.getDeclaredField("field_71466_p");
            displayWidth = clazz.getDeclaredField("field_71443_c");
            displayHeight = clazz.getDeclaredField("field_71440_d");
            getMinecraft = clazz.getDeclaredMethod("func_71410_x");
            displayGuiScreen = clazz.getDeclaredMethod("func_147108_a", GuiScreen.clazz);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Object getThePlayer() {
        try {
            return thePlayer.get(getMinecraft());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Object getTheWorld() {
        try {
            return theWorld.get(getMinecraft());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Object getCurrentScreen() {
        try {
            return currentScreen.get(getMinecraft());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Object getGameSettings() {
        try {
            return gameSettings.get(getMinecraft());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Object getPlayerController() {
        try {
            return playerController.get(getMinecraft());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Object getFontRenderer() {
        try {
            return fontRenderer.get(getMinecraft());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Object getMinecraft() {
        try {
            return getMinecraft.invoke(null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void displayGuiScreen(Object guiScreen) {
        try {
            displayGuiScreen.invoke(getMinecraft(), guiScreen == null ? null : GuiScreen.clazz.cast(guiScreen));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public int getDisplayWidth() {
        try {
            return (int) displayWidth.get(getMinecraft());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public int getDisplayHeight() {
        try {
            return (int) displayHeight.get(getMinecraft());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }
}
