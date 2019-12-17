package respectful.rapist.loader;

import sun.misc.Resource;
import sun.misc.URLClassPath;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class Loader extends URLClassLoader {
    Loader(URL[] urls) {
        super(urls);
    }

    @Override
    public Class<?> findClass(String name) {
        try {
            String path = name.replace('.', '/').concat(".class");
            Field field = URLClassLoader.class.getDeclaredField("ucp");
            field.setAccessible(true);
            URLClassPath ucp = (URLClassPath) field.get(this);
            Resource res = ucp.getResource(path, false);
            if (res != null) {
                Method method = URLClassLoader.class.getDeclaredMethod("defineClass", String.class, Resource.class);
                method.setAccessible(true);
                return (Class<?>) method.invoke(this, name, res);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}