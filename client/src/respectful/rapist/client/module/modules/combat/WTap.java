package respectful.rapist.client.module.modules.combat;

import org.lwjgl.input.Keyboard;
import respectful.rapist.client.mapping.Mappings;
import respectful.rapist.client.module.Module;
import respectful.rapist.client.util.Config;
import respectful.rapist.client.util.Random;
import respectful.rapist.client.util.Timer;

public class WTap extends Module implements Mappings {
    public float dist = 3.0F;
    public int FOV = 25, minTapDelay = 100, maxTapDelay = 400, delay = Random.nextInt(minTapDelay, maxTapDelay);
    public boolean reqItem, released;
    public int[] itemWhitelist = {267, 276, 272, 283, 268};
    public Object target;
    private Timer timer = new Timer();

    public WTap() {
        super(35, "WTap", "9E0000");
    }

    @Override
    public void onTick() {
        if (Keyboard.isKeyDown(17)) {
            if (Config.safe(reqItem, itemWhitelist, true, false, true)) {
                Config.Target target = Config.Target.check(this.target, dist, FOV);
                this.target = (target != null) ? target.target : null;
                if (this.target == null) {
                    for (Object entityPlayer : World.getPlayerEntities(Minecraft.getTheWorld())) {
                        target = Config.Target.check(entityPlayer, dist, FOV);
                        if (target != null) {
                            this.target = target.target;
                        }
                    }
                }
                if (this.target != null) {
                    if (timer.elapsed(delay) && !released) {
                        KeyBinding.setKeyBindState(17, false);
                        released = true;
                        delay /= 3L;
                        timer = new Timer();
                    } else if (timer.elapsed(delay) && released) {
                        KeyBinding.setKeyBindState(17, true);
                        KeyBinding.onTick(17);
                        released = false;
                        delay = Random.nextInt(minTapDelay, maxTapDelay);
                        timer = new Timer();
                    }
                } else {
                    if (released) {
                        KeyBinding.setKeyBindState(17, true);
                        KeyBinding.onTick(17);
                    }
                }
            } else {
                target = null;
                if (released && Minecraft.getCurrentScreen() == null) {
                    KeyBinding.setKeyBindState(17, true);
                    KeyBinding.onTick(17);
                }
            }
        } else {
            target = null;
        }
    }

    @Override
    public void disable() {
        target = null;
        super.disable();
    }
}
