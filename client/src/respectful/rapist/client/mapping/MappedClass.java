package respectful.rapist.client.mapping;

import net.minecraft.launchwrapper.Launch;

public abstract class MappedClass {
    public Class clazz;

    public MappedClass(String path) {
        try {
            clazz = Launch.classLoader.findClass(path);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
