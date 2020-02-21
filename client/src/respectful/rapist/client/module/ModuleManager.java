package respectful.rapist.client.module;

import respectful.rapist.client.module.modules.combat.*;
import respectful.rapist.client.module.modules.misc.FakeLag;
import respectful.rapist.client.module.modules.visuals.Brightness;
import respectful.rapist.client.module.modules.visuals.HUD;
import respectful.rapist.client.module.modules.visuals.NameTags;

import java.util.ArrayList;

public class ModuleManager {
    public ArrayList<Module> modules = new ArrayList<>();
    public AutoClicker autoClicker = new AutoClicker();
    public Aimbot aimbot = new Aimbot();
    public NameTags nameTags = new NameTags();
    public Brightness brightness = new Brightness();
    public Reach reach = new Reach();
    public HitBoxes hitBoxes = new HitBoxes();
    public FakeLag fakeLag = new FakeLag();
    public WTap wTap = new WTap();
    public SelfDestruct selfDestruct = new SelfDestruct();
    public HUD HUD = new HUD();
    public ThrowPot throwPot = new ThrowPot();
    public Refill refill = new Refill();

    public ModuleManager() {
        modules.add(autoClicker);
        modules.add(aimbot);
        modules.add(nameTags);
        modules.add(brightness);
        modules.add(reach);
        modules.add(hitBoxes);
        modules.add(fakeLag);
        modules.add(wTap);
        modules.add(selfDestruct);
        modules.add(HUD);
        modules.add(throwPot);
        modules.add(refill);
    }
}
