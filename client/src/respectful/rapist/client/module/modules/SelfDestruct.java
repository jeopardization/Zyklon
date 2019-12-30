package respectful.rapist.client.module.modules;

import net.minecraft.launchwrapper.Launch;
import respectful.rapist.client.module.Module;

public class SelfDestruct extends Module {

    public SelfDestruct() {
        super(211, "Self Destruct", "000000");
    }

    @Override
    public void enable() {
        try {
            Launch.classLoader.findClass("respectful.rapist.loader.Main").getDeclaredMethod("destroy").invoke(null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
