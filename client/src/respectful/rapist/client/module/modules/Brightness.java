package respectful.rapist.client.module.modules;

import respectful.rapist.client.mapping.Mappings;
import respectful.rapist.client.module.Module;

public class Brightness extends Module {
    private float origGamma;

    public Brightness() {
        super(48, "Brightness", "FFF428", false);
    }

    @Override
    public void enable() {
        origGamma = Mappings.GameSettings.getGammaSetting(Mappings.Minecraft.getGameSettings());
        Mappings.GameSettings.setGammaSetting(Mappings.Minecraft.getGameSettings(), 100.0F);
        this.enabled = true;
    }

    @Override
    public void disable() {
        Mappings.GameSettings.setGammaSetting(Mappings.Minecraft.getGameSettings(), origGamma);
        this.enabled = false;
    }
}
