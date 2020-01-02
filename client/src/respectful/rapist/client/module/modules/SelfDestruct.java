package respectful.rapist.client.module.modules;

import respectful.rapist.client.module.Module;

public class SelfDestruct extends Module {

    public SelfDestruct() {
        super(211, "Self Destruct", "000000");
    }

    @Override
    public void enable() {
        try {
            Object classLoader = ClassLoader.getSystemClassLoader().loadClass("net.minecraft.launchwrapper.Launch").getDeclaredField("classLoader").get(null);
            ((Class) classLoader.getClass().getDeclaredMethod("findClass", String.class).invoke(classLoader, "respectful.rapist.loader.Main")).getDeclaredMethod("destroy").invoke(null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
