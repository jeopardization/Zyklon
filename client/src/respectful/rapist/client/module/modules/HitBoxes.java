package respectful.rapist.client.module.modules;

import respectful.rapist.client.module.Module;
import respectful.rapist.client.util.Config;

public class HitBoxes extends Module {
    public static float add = 0.05F;
    public float expansion = 0.05F;
    public boolean reqItem;
    public int[] itemWhitelist = {267, 276, 272, 283, 268};

    public HitBoxes() {
        super(47, "HitBoxes", "4DECF2", false);
    }

    @Override
    public void onTick() {
        if (Config.safe(reqItem, itemWhitelist, false)) {
            add = expansion;
        } else {
            add = 0.0F;
        }
    }

    @Override
    public void disable() {
        add = 0.0F;
        this.enabled = false;
    }
}
