package respectful.rapist.client.module.modules.misc;

import org.lwjgl.input.Mouse;
import respectful.rapist.client.Events;
import respectful.rapist.client.mapping.Mappings;
import respectful.rapist.client.module.Module;
import respectful.rapist.client.util.Config;
import respectful.rapist.client.util.Random;

public class FakeLag extends Module implements Mappings {
    public int FOV = 100, minDelay = 150, maxDelay = 250;
    public float dist = 3.0F;
    public boolean attacking;

    public FakeLag() {
        super(25, "FakeLag", 0xDFE6E9);
    }

    public static void sleep() {
        if (Events.modules.fakeLag.enabled && !Events.modules.fakeLag.attacking) {
            try {
                Thread.sleep(Random.nextInt(Events.modules.fakeLag.minDelay, Events.modules.fakeLag.maxDelay));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void onTick() {
        if (Mouse.isButtonDown(0)) {
            for (Object entityPlayer : World.getPlayerEntities(Minecraft.getTheWorld())) {
                if (Config.Target.check(entityPlayer, dist, FOV) != null) {
                    attacking = true;
                    break;
                } else {
                    attacking = false;
                }
            }
        } else {
            attacking = false;
        }
    }
}
