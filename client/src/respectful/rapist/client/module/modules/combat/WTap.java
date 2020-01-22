package respectful.rapist.client.module.modules.combat;

import org.lwjgl.input.Keyboard;
import respectful.rapist.client.EventManager;
import respectful.rapist.client.mapping.Mappings;
import respectful.rapist.client.module.Module;
import respectful.rapist.client.util.Config;
import respectful.rapist.client.util.Random;
import respectful.rapist.client.util.Timer;

public class WTap extends Module implements Mappings {
    public float dist = 2.5F;
    public int minTapDelay = 100, maxTapDelay = 350;
    public boolean reqItem, released;
    public int[] itemWhitelist = {267, 276, 272, 283, 268};
    private long tapDelay = Random.nextInt(minTapDelay, maxTapDelay);
    private Timer timer = new Timer();

    public WTap() {
        super(35, "WTap", "9E0000");
    }

    @Override
    public void onTick() {
        if (Config.safe(reqItem, itemWhitelist, true) && Keyboard.isKeyDown(17)) {
            Object entity = MovingObjectPosition.getEntityHit(Minecraft.getObjectMouseOver());
            if (EntityPlayer.clazz.isInstance(entity) && Entity.getDistanceToEntity(Minecraft.getThePlayer(), entity) <= dist && timer.elapsed(tapDelay) && !EventManager.playerManager.isFriend(EntityPlayer.getCommandSenderName(entity)) && !released) {
                KeyBinding.setKeyBindState(17, false);
                tapDelay = Random.nextInt(minTapDelay, maxTapDelay);
                released = true;
                timer = new Timer();
            } else if (timer.elapsed(tapDelay) && released) {
                KeyBinding.setKeyBindState(17, true);
                KeyBinding.onTick(17);
                tapDelay = Random.nextInt(minTapDelay, maxTapDelay);
                released = false;
                timer = new Timer();
                if (Keyboard.isKeyDown(17)) {
                    KeyBinding.setKeyBindState(17, true);
                    KeyBinding.onTick(17);
                } else {
                    KeyBinding.setKeyBindState(17, false);
                }
            }
        }
    }
}
