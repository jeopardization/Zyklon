package respectful.rapist.loader.mapping.mappings;

import respectful.rapist.loader.mapping.MappedClass;

import java.lang.reflect.Field;

public class ModuleManager extends MappedClass {
    private Field nameTags;

    public ModuleManager() {
        super("respectful.rapist.client.module.ModuleManager");
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
