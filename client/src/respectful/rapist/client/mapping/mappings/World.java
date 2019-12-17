package respectful.rapist.client.mapping.mappings;

import respectful.rapist.client.mapping.MappedClass;

import java.util.ArrayList;

public class World extends MappedClass {
    public World() {
        super("net.minecraft.world.World");
    }

    public ArrayList getPlayerEntities(Object obj) {
        try {
            return (ArrayList) clazz.getDeclaredField("field_73010_i").get(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
