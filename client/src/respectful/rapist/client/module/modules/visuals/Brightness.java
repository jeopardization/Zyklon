package respectful.rapist.client.module.modules.visuals;

import respectful.rapist.client.mapping.Mappings;
import respectful.rapist.client.module.Module;

public class Brightness extends Module implements Mappings {
    private float origGamma;

    public Brightness() {
        super(48, "Brightness", 0xFFEAA7);
    }

    @Override
    public void enable() {
        origGamma = GameSettings.getGammaSetting(Minecraft.getGameSettings());
        GameSettings.setGammaSetting(Minecraft.getGameSettings(), 100.0F);
        super.enable();
    }

    @Override
    public void disable() {
        GameSettings.setGammaSetting(Minecraft.getGameSettings(), origGamma);
        super.disable();
    }
}
