package respectful.rapist.client;

import net.openhft.compiler.CompilerUtils;

public class Plugins {
    public boolean enabled;
    private Class plugins;

    public Plugins(String code) {
        try {
            plugins = CompilerUtils.CACHED_COMPILER.loadFromJava("respectful.rapist.client.Plugin", "package respectful.rapist.client;" + code);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void onTick() {
        try {
            if (enabled) {
                plugins.getDeclaredMethod("onTick").invoke(null);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void onRenderGUI() {
        try {
            if (enabled) {
                plugins.getDeclaredMethod("onRenderGUI").invoke(null);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void onRender() {
        try {
            if (enabled) {
                plugins.getDeclaredMethod("onRender").invoke(null);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void onKey(int keyCode) {
        try {
            if (enabled) {
                plugins.getDeclaredMethod("onKey", int.class).invoke(null, keyCode);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
