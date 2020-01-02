package respectful.rapist.client.mapping;

public abstract class MappedClass {
    public Class clazz;

    public MappedClass(String path) {
        try {
            Object classLoader = ClassLoader.getSystemClassLoader().loadClass("net.minecraft.launchwrapper.Launch").getDeclaredField("classLoader").get(null);
            clazz = (Class) classLoader.getClass().getDeclaredMethod("findClass", String.class).invoke(classLoader, path);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
