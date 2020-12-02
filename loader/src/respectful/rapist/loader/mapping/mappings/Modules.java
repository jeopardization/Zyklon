package respectful.rapist.loader.mapping.mappings;

import respectful.rapist.loader.mapping.MappedClass;

import java.lang.reflect.Field;

public class Modules extends MappedClass {
    private Field nameTags;

    public Modules() {
        super("respectful.rapist.client.module.Modules");
        try {
            nameTags = clazz.getDeclaredField("nameTags");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Object getNameTags(Object obj) {
        try {
            return nameTags.get(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
