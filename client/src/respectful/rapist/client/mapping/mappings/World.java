package respectful.rapist.client.mapping.mappings;

import respectful.rapist.client.mapping.MappedClass;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class World extends MappedClass {
    private Field playerEntities;

    public World() {
        super("net.minecraft.world.World");
        try {
            playerEntities = clazz.getDeclaredField("field_73010_i");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList getPlayerEntities(Object obj) {
        try {
            return (ArrayList) playerEntities.get(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
