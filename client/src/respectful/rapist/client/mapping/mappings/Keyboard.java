package respectful.rapist.client.mapping.mappings;

import respectful.rapist.client.mapping.MappedClass;

public class Keyboard extends MappedClass {
    public Keyboard() {
        super("org.lwjgl.input.Keyboard");
    }

    public boolean isKeyDown(int keyCode) {
        try {
            return (boolean) clazz.getDeclaredMethod("isKeyDown", int.class).invoke(null, keyCode);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
