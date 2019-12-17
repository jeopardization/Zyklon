package respectful.rapist.client.module.modules;

import respectful.rapist.client.mapping.Mappings;
import respectful.rapist.client.module.Module;
import respectful.rapist.client.util.Random;
import respectful.rapist.client.util.Timer;

import java.util.ArrayList;

public class Refill extends Module {
    public int minFillDelay = 75, maxFillDelay = 100, minExitDelay = 100, maxExitDelay = 200, loop, filled;
    private Timer timer = new Timer();

    public Refill() {
        super(34, "Refill", "000000", true);
    }

    @Override
    public void onTick() {
        boolean slotsAvail = false;
        for (int i = 0; i < 9; i++) {
            Object itemStack = Mappings.InventoryPlayer.getMainInventory(Mappings.EntityPlayer.getInventory(Mappings.Minecraft.getThePlayer()))[i];
            if ((itemStack == null)) {
                slotsAvail = true;
                break;
            }
        }
        ArrayList<Integer> pots = new ArrayList<>();
        for (int i = 9; i < 36; i++) {
            Object itemStack = Mappings.InventoryPlayer.getMainInventory(Mappings.EntityPlayer.getInventory(Mappings.Minecraft.getThePlayer()))[i];
            if (itemStack != null && Mappings.Item.getItemStackDisplayName(Mappings.ItemStack.getItem(itemStack), itemStack).equals("Splash Potion of Healing")) {
                pots.add(i);
            }
        }
        if (slotsAvail && !pots.isEmpty()) {
            Mappings.Minecraft.displayGuiScreen(Mappings.GuiInventory.newInstance(Mappings.Minecraft.getThePlayer()));
            if (timer.elapsed(Random.nextInt(minFillDelay, maxFillDelay))) {
                Mappings.PlayerControllerMP.windowClick(Mappings.Minecraft.getPlayerController(), 0, pots.get(Random.nextInt(0, pots.size())), 0, 1, Mappings.Minecraft.getThePlayer());
                timer = new Timer();
            }
        } else {
            if (timer.elapsed(Random.nextInt(minExitDelay, maxExitDelay))) {
                Mappings.Minecraft.displayGuiScreen(null);
                timer = new Timer();
                disable();
            }
        }
    }

    @Override
    public void enable() {
        timer = new Timer();
        this.enabled = true;
    }
}
