package respectful.rapist.client.module.modules.combat;

import respectful.rapist.client.mapping.Mappings;
import respectful.rapist.client.module.Module;
import respectful.rapist.client.util.Random;
import respectful.rapist.client.util.Timer;

import java.security.SecureRandom;
import java.util.ArrayList;

public class Refill extends Module implements Mappings {
    public int minFillDelay = 65, maxFillDelay = 110, minExitDelay = 110, maxExitDelay = 200;
    private long fillDelay = Random.nextInt(minFillDelay, maxFillDelay), exitDelay = Random.nextInt(minExitDelay, maxExitDelay), releaseDelay = fillDelay / 3L;
    private Timer timer = new Timer(), heldTimer = new Timer();
    private boolean held;

    public Refill() {
        super(34, "Refill", "000000");
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
            if (!held && Minecraft.getCurrentScreen() == null) {
                KeyBinding.setKeyBindState(18, true);
                KeyBinding.onTick(18);
                heldTimer = new Timer();
                held = true;
            }
            if (held && heldTimer.elapsed(releaseDelay)) {
                KeyBinding.setKeyBindState(18, false);
                held = false;
            }
            if (GuiInventory.clazz.isInstance(Minecraft.getCurrentScreen()) && timer.elapsed(fillDelay)) {
                PlayerControllerMP.windowClick(Minecraft.getPlayerController(), 0, pots.get(new SecureRandom().nextInt(pots.size())), 0, 1, Minecraft.getThePlayer());
                fillDelay = Random.nextInt(minFillDelay, maxFillDelay);
                timer = new Timer();
            }
        } else {
            if (timer.elapsed(exitDelay)) {
                Minecraft.displayGuiScreen(null);
                exitDelay = Random.nextInt(minExitDelay, maxExitDelay);
                disable();
            }
        }
    }

    @Override
    public void enable() {
        fillDelay = Random.nextInt(minFillDelay, maxFillDelay);
        exitDelay = Random.nextInt(minExitDelay, maxExitDelay);
        releaseDelay = fillDelay / 3L;
        timer = new Timer();
        super.enable();
    }
}
