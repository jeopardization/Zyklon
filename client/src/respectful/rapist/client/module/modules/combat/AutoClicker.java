package respectful.rapist.client.module.modules.combat;

import respectful.rapist.client.mapping.Mappings;
import respectful.rapist.client.module.Module;
import respectful.rapist.client.util.Config;
import respectful.rapist.client.util.Random;
import respectful.rapist.client.util.Timer;

public class AutoClicker extends Module implements Mappings {
    public float minCPS = 8.0F, maxCPS = 14.0F;
    public int[] itemWhitelist = {267, 276, 272, 283, 268};
    public boolean reqItem, held;
    private int delay = (int) Random.nextFloat(1000.0F / maxCPS, 1000.0F / minCPS), releaseDelay = delay / 3;
    private Timer timer = new Timer();

    public AutoClicker() {
        super(19, "AutoClicker", "FF0000");
    }

    @Override
    public void onTick() {
        if (Config.safe(reqItem, itemWhitelist, true)) {
            if (timer.elapsed(delay - releaseDelay) && !held) {
                KeyBinding.setKeyBindState(-100, true);
                KeyBinding.onTick(-100);
                CPSMod.addClick();
                held = true;
                timer = new Timer();
            } else if (timer.elapsed(releaseDelay) && held) {
                KeyBinding.setKeyBindState(-100, false);
                held = false;
                delay = (int) Random.nextFloat(1000.0F / maxCPS, 1000.0F / minCPS);
                releaseDelay = delay / 3;
                timer = new Timer();
            }
        }
    }
}
