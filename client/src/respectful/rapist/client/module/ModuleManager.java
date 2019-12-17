package respectful.rapist.client.module;

import respectful.rapist.client.module.modules.*;

import java.util.ArrayList;

public class ModuleManager {
    public ArrayList<Module> modules = new ArrayList<>();
    public AutoClicker autoClicker = new AutoClicker();
    public Aimbot aimbot = new Aimbot();
    public Brightness brightness = new Brightness();
    public HitBoxes hitBoxes = new HitBoxes();
    public WTap wTap = new WTap();
    public SelfDestruct selfDestruct = new SelfDestruct();
    public HUD HUD = new HUD();
    public ThrowPot throwPot = new ThrowPot();
    public Refill refill = new Refill();

    public ModuleManager() {
        modules.add(autoClicker);
        modules.add(aimbot);
        modules.add(brightness);
        modules.add(hitBoxes);
        modules.add(wTap);
        modules.add(selfDestruct);
        modules.add(HUD);
        modules.add(throwPot);
        modules.add(refill);
    }
}
