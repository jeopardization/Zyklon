package respectful.rapist.client.mapping.mappings;

import respectful.rapist.client.mapping.MappedClass;

import java.lang.reflect.Field;

public class GameSettings extends MappedClass {
    private Field gammaSetting;

    public GameSettings() {
        super("net.minecraft.client.settings.GameSettings");
        try {
            gammaSetting = clazz.getDeclaredField("field_74333_Y");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public float getGammaSetting(Object obj) {
        try {
            return gammaSetting.getFloat(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0.0F;
    }

    public void setGammaSetting(Object obj, float value) {
        try {
            gammaSetting.setFloat(obj, value);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
