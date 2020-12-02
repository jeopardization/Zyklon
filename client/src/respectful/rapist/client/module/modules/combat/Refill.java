package respectful.rapist.client.module.modules.combat;

import respectful.rapist.client.mapping.Mappings;
import respectful.rapist.client.module.Module;
import respectful.rapist.client.util.Random;
import respectful.rapist.client.util.Timer;

import java.security.SecureRandom;
import java.util.ArrayList;

public class Refill extends Module implements Mappings {
    public int minFillDelay = 75, maxFillDelay = 125, minExitDelay = 125, maxExitDelay = 225;
    private int fillDelay = Random.nextInt(minFillDelay, maxFillDelay), exitDelay = Random.nextInt(minExitDelay, maxExitDelay), releaseDelay = fillDelay / 3;
    private Timer timer = new Timer(), heldTimer;

    public Refill() {
        super(34, "Refill", 0x000000);
    }

    @Override
    public void onTick() {
        boolean slotsAvail = false;
        for (int i = 0; i < 9; i++) {
            Object itemStack = InventoryPlayer.getMainInventory(EntityPlayer.getInventory(Minecraft.getThePlayer()))[i];
            if (itemStack == null) {
                slotsAvail = true;
                break;
            }
        }
        ArrayList<Integer> pots = new ArrayList<>();
        for (int i = 9; i < 36; i++) {
            Object itemStack = InventoryPlayer.getMainInventory(EntityPlayer.getInventory(Minecraft.getThePlayer()))[i];
            if (itemStack != null && Item.getItemStackDisplayName(ItemStack.getItem(itemStack), itemStack).equals("Splash Potion of Healing")) {
                pots.add(i);
            }
        }
        if (slotsAvail && !pots.isEmpty()) {
            if (heldTimer == null && Minecraft.getCurrentScreen() == null) {
                KeyBinding.setKeyBindState(18, true);
                KeyBinding.onTick(18);
                heldTimer = new Timer();
            }
            if (heldTimer != null && heldTimer.elapsed(releaseDelay)) {
                KeyBinding.setKeyBindState(18, false);
            }
            if (GuiInventory.clazz.isInstance(Minecraft.getCurrentScreen()) && timer.elapsed(fillDelay)) {
                PlayerControllerMP.windowClick(Minecraft.getPlayerController(), 0, pots.get(new SecureRandom().nextInt(pots.size())), 0, 1, Minecraft.getThePlayer());
                fillDelay = Random.nextInt(minFillDelay, maxFillDelay);
                timer = new Timer();
            }
        } else {
            if (timer.elapsed(exitDelay)) {
                Minecraft.displayGuiScreen(null);
                disable();
            }
        }
    }

    @Override
    public void enable() {
        fillDelay = Random.nextInt(minFillDelay, maxFillDelay);
        exitDelay = Random.nextInt(minExitDelay, maxExitDelay);
        releaseDelay = fillDelay / 3;
        heldTimer = null;
        timer = new Timer();
        super.enable();
    }
}
