package respectful.rapist.client.mapping.mappings;

import respectful.rapist.client.mapping.MappedClass;

public class GameSettings extends MappedClass {
    public GameSettings() {
        super("net.minecraft.client.settings.GameSettings");
    }

    public float getGammaSetting(Object obj) {
        try {
            return clazz.getDeclaredField("field_74333_Y").getFloat(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0.0F;
    }

    public void setGammaSetting(Object obj, float value) {
        try {
            clazz.getDeclaredField("field_74333_Y").setFloat(obj, value);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
