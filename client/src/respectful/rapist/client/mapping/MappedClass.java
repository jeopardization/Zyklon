package respectful.rapist.client.mapping;

import respectful.rapist.client.util.ClassLoader;

public abstract class MappedClass {
    public Class clazz;

    public MappedClass(String path) {
        clazz = ClassLoader.findClass(path);
    }
}
