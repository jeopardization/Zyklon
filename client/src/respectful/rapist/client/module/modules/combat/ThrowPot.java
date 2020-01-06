package respectful.rapist.client.module.modules.combat;

import respectful.rapist.client.mapping.Mappings;
import respectful.rapist.client.module.Module;
import respectful.rapist.client.util.Timer;

import java.security.SecureRandom;
import java.util.ArrayList;

public class ThrowPot extends Module {
    public int minThrowDelay = 75, maxThrowDelay = 100, slot = -1;
    private Timer timer = new Timer();
    private boolean held;

    public ThrowPot() {
        super(33, "ThrowPot", "000000");
    }

    @Override
    public void onTick() {
        if (Mappings.EntityLivingBase.getHealth(Mappings.Minecraft.getThePlayer()) <= 13.5F) {
            if (slot == -1 && !held) {
                java.util.ArrayList<Integer> pots = new ArrayList<>();
                for (int i = 0; i < 9; i++) {
                    Object itemStack = Mappings.InventoryPlayer.getMainInventory(Mappings.EntityPlayer.getInventory(Mappings.Minecraft.getThePlayer()))[i];
                    if (itemStack != null && Mappings.Item.getItemStackDisplayName(Mappings.ItemStack.getItem(itemStack), itemStack).equals("Splash Potion of Healing")) {
                        pots.add(i);
                    }
                }
                if (!pots.isEmpty()) {
                    slot = pots.get(new SecureRandom().nextInt(pots.size()));
                }
            } else if (slot > -1 && !held) {
                Mappings.InventoryPlayer.setCurrentItem(Mappings.EntityPlayer.getInventory(Mappings.Minecraft.getThePlayer()), slot);
                if (timer.elapsed(respectful.rapist.client.util.Random.nextInt(minThrowDelay, maxThrowDelay))) {
                    Mappings.KeyBinding.setKeyBindState(-99, true);
                    Mappings.KeyBinding.onTick(-99);
                    held = true;
                    timer = new Timer();
                }
            } else if (timer.elapsed(respectful.rapist.client.util.Random.nextInt(15, 30)) && slot > -1 && held) {
                Mappings.KeyBinding.setKeyBindState(-99, false);
                held = false;
                slot = -1;
                timer = new Timer();
                disable();
            }
        } else {
            disable();
        }
    }

    @Override
    public void enable() {
        timer = new Timer();
        super.enable();
    }
}
