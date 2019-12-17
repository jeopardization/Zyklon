package respectful.rapist.client.mapping.mappings;

import respectful.rapist.client.mapping.MappedClass;

public class KeyBinding extends MappedClass {
    public KeyBinding() {
        super("net.minecraft.client.settings.KeyBinding");
    }

    public void setKeyBindState(int keyCode, boolean active) {
        try {
            clazz.getDeclaredMethod("func_74510_a", int.class, boolean.class).invoke(null, keyCode, active);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void onTick(int keyCode) {
        try {
            clazz.getDeclaredMethod("func_74507_a", int.class).invoke(null, keyCode);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
