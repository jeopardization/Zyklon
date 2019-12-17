package respectful.rapist.client.mapping.mappings;

import respectful.rapist.client.mapping.MappedClass;

public class Mouse extends MappedClass {
    public Mouse() {
        super("org.lwjgl.input.Mouse");
    }

    public boolean isButtonDown(int keyCode) {
        try {
            return (boolean) clazz.getDeclaredMethod("isButtonDown", int.class).invoke(null, keyCode);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
