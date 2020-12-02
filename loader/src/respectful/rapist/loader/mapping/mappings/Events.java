package respectful.rapist.loader.mapping.mappings;

import respectful.rapist.loader.mapping.MappedClass;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Events extends MappedClass {
    private Field modules;
    private Method onKey, onRenderGUI, onRender, onTick;

    public Events() {
        super("respectful.rapist.client.Events");
        try {
            modules = clazz.getDeclaredField("modules");
            onKey = clazz.getDeclaredMethod("onKey", int.class);
            onRender = clazz.getDeclaredMethod("onRender");
            onRenderGUI = clazz.getDeclaredMethod("onRenderGUI");
            onTick = clazz.getDeclaredMethod("onTick");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void onKey(int keyCode) {
        try {
            onKey.invoke(null, keyCode);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void onRenderGUI() {
        try {
            onRenderGUI.invoke(null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void onRender() {
        try {
            onRender.invoke(null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void onTick() {
        try {
            onTick.invoke(null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Object getModules() {
        try {
            return modules.get(null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
