package respectful.rapist.client.mapping.mappings;

import respectful.rapist.client.mapping.MappedClass;

import java.lang.reflect.Method;

public class KeyBinding extends MappedClass {
    private Method setKeyBindState, onTick;

    public KeyBinding() {
        super("net.minecraft.client.settings.KeyBinding");
        try {
            setKeyBindState = clazz.getDeclaredMethod("func_74510_a", int.class, boolean.class);
            onTick = clazz.getDeclaredMethod("func_74507_a", int.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setKeyBindState(int keyCode, boolean active) {
        try {
            setKeyBindState.invoke(null, keyCode, active);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void onTick(int keyCode) {
        try {
            onTick.invoke(null, keyCode);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
