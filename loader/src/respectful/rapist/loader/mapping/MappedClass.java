package respectful.rapist.loader.mapping;

import respectful.rapist.loader.Main;

public abstract class MappedClass {
    public Class clazz;

    public MappedClass(String path) {
        try {
            clazz = Main.loader.loadClass(path);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}