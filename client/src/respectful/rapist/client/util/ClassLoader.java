package respectful.rapist.client.util;

public class ClassLoader {
    private static Object classLoader;

    static {
        try {
            classLoader = java.lang.ClassLoader.getSystemClassLoader().loadClass("net.minecraft.launchwrapper.Launch").getDeclaredField("classLoader").get(null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Class findClass(String path) {
        try {
            return (Class) classLoader.getClass().getDeclaredMethod("findClass", String.class).invoke(classLoader, path);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
