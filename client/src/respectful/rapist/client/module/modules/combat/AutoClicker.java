package respectful.rapist.client.module.modules.combat;

import respectful.rapist.client.mapping.Mappings;
import respectful.rapist.client.module.Module;
import respectful.rapist.client.util.Config;
import respectful.rapist.client.util.Random;
import respectful.rapist.client.util.Timer;

public class AutoClicker extends Module implements Mappings {
    public float minCPS = 8.0F, maxCPS = 13.0F;
    public int[] itemWhitelist = {267, 276, 272, 283, 268};
    public boolean reqItem, held;
    private long holdDelay = (long) Random.nextFloat(1000.0F / maxCPS, 1000.0F / minCPS), releaseDelay = holdDelay / 3L;
    private Timer timer = new Timer();

    public AutoClicker() {
        super(19, "AutoClicker", "FF0000");
    }

    @Override
    public void onTick() {
        if (Config.safe(reqItem, itemWhitelist, true)) {
            if (timer.elapsed(holdDelay) && !held) {
                KeyBinding.setKeyBindState(-100, true);
                KeyBinding.onTick(-100);
                CPSMod.addClick();
                held = true;
                releaseDelay = holdDelay / 3L;
                timer = new Timer();
            } else if (timer.elapsed(releaseDelay) && held) {
                KeyBinding.setKeyBindState(-100, false);
                held = false;
                holdDelay = (long) Random.nextFloat((1000.0F / maxCPS) - releaseDelay, (1000.0F / minCPS) - releaseDelay);
                timer = new Timer();
            }
        }
    }
}
