package respectful.rapist.client.module.modules.combat;

import org.lwjgl.input.Keyboard;
import respectful.rapist.client.EventManager;
import respectful.rapist.client.mapping.Mappings;
import respectful.rapist.client.module.Module;
import respectful.rapist.client.util.Config;
import respectful.rapist.client.util.Random;
import respectful.rapist.client.util.Timer;

public class WTap extends Module {
    public float dist = 2.5F;
    public int minTapDelay = 75, maxTapDelay = 100;
    public boolean reqItem, released;
    public int[] itemWhitelist = {267, 276, 272, 283, 268};
    private Timer timer = new Timer();

    public WTap() {
        super(35, "WTap", "9E0000");
    }

    @Override
    public void onTick() {
        if (Config.safe(reqItem, itemWhitelist, true) && Keyboard.isKeyDown(17)) {
            Object entity = Mappings.MovingObjectPosition.getEntityHit(Mappings.Minecraft.getObjectMouseOver());
            if (Mappings.EntityPlayer.clazz.isInstance(entity) && Mappings.Entity.getDistanceToEntity(Mappings.Minecraft.getThePlayer(), entity) <= dist && timer.elapsed(Random.nextInt(minTapDelay, maxTapDelay)) && !EventManager.playerManager.isFriend(Mappings.EntityPlayer.getCommandSenderName(entity)) && !released) {
                Mappings.KeyBinding.setKeyBindState(17, false);
                timer = new Timer();
                released = true;
            } else if (timer.elapsed(Random.nextInt(minTapDelay, maxTapDelay)) && released) {
                Mappings.KeyBinding.setKeyBindState(17, true);
                Mappings.KeyBinding.onTick(17);
                timer = new Timer();
                released = false;
                if (Keyboard.isKeyDown(17)) {
                    Mappings.KeyBinding.setKeyBindState(17, true);
                    Mappings.KeyBinding.onTick(17);
                } else {
                    Mappings.KeyBinding.setKeyBindState(17, false);
                }
            }
        }
    }
}
