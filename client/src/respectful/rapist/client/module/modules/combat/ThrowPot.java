package respectful.rapist.client.module.modules.combat;

import respectful.rapist.client.mapping.Mappings;
import respectful.rapist.client.module.Module;
import respectful.rapist.client.util.Random;
import respectful.rapist.client.util.Timer;

import java.security.SecureRandom;
import java.util.ArrayList;

public class ThrowPot extends Module implements Mappings {
    public int minThrowDelay = 65, maxThrowDelay = 110, slot = -1;
    private int delay = Random.nextInt(minThrowDelay, maxThrowDelay);
    private Timer timer = new Timer();
    private boolean held;

    public ThrowPot() {
        super(33, "ThrowPot", "000000");
    }

    @Override
    public void onTick() {
        if (EntityLivingBase.getHealth(Minecraft.getThePlayer()) <= 13.5F) {
            if (slot == -1 && !held) {
                java.util.ArrayList<Integer> pots = new ArrayList<>();
                for (int i = 0; i < 9; i++) {
                    Object itemStack = InventoryPlayer.getMainInventory(EntityPlayer.getInventory(Minecraft.getThePlayer()))[i];
                    if (itemStack != null && Item.getItemStackDisplayName(ItemStack.getItem(itemStack), itemStack).equals("Splash Potion of Healing")) {
                        pots.add(i);
                    }
                }
                if (pots.isEmpty()) {
                    disable();
                } else {
                    slot = pots.get(new SecureRandom().nextInt(pots.size()));
                }
            }
            if (slot > -1 && !held) {
                InventoryPlayer.setCurrentItem(EntityPlayer.getInventory(Minecraft.getThePlayer()), slot);
                if (timer.elapsed(delay)) {
                    KeyBinding.setKeyBindState(-99, true);
                    KeyBinding.onTick(-99);
                    held = true;
                    delay /= 3;
                    timer = new Timer();
                }
            } else if (timer.elapsed(delay) && slot > -1 && held) {
                KeyBinding.setKeyBindState(-99, false);
                disable();
            }
        } else {
            disable();
        }
    }

    @Override
    public void enable() {
        held = false;
        slot = -1;
        delay = Random.nextInt(minThrowDelay, maxThrowDelay);
        timer = new Timer();
        super.enable();
    }
}
