package respectful.rapist.client.module.modules;

import respectful.rapist.client.module.Module;
import respectful.rapist.client.util.Config;
import respectful.rapist.client.util.Random;

public class Reach extends Module {
    public static double add;
    public double minExpansion = 0.1D, maxExpansion = 0.3D;
    public boolean reqItem;
    public int[] itemWhitelist = {267, 276, 272, 283, 268};

    public Reach() {
        super(45, "Reach", "00E6B8");
    }

    @Override
    public void onTick() {
        if (Config.safe(reqItem, itemWhitelist, false)) {
            add = Random.nextDouble(minExpansion, maxExpansion);
        } else {
            add = 0.0D;
        }
    }

    @Override
    public void disable() {
        add = 0.0D;
        super.disable();
    }
}
