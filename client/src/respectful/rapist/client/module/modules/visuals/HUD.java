package respectful.rapist.client.module.modules.visuals;

import respectful.rapist.client.Events;
import respectful.rapist.client.mapping.Mappings;
import respectful.rapist.client.module.Module;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HUD extends Module implements Mappings {
    public HUD() {
        super(200, "HUD", 0xFF0000);
    }

    @Override
    public void onRenderGUI() {
        FontRenderer.drawStringWithShadow(Minecraft.getFontRenderer(), "Zyklon", 3, 3, 0x8DC63F);
        FontRenderer.drawStringWithShadow(Minecraft.getFontRenderer(), new SimpleDateFormat("h:mm a").format(Calendar.getInstance().getTime()), FontRenderer.getStringWidth(Minecraft.getFontRenderer(), "Zyklon") + 6, 3, 0xFFFFFF);
        int mult = 0;
        for (Module module : Events.modules.modules) {
            if (module.enabled && !module.equals(this)) {
                FontRenderer.drawStringWithShadow(Minecraft.getFontRenderer(), module.name, ScaledResolution.getScaledWidth(RealmsSharedConstants.getVersion().equals("1.8.9") ? ScaledResolution.newInstance(Minecraft.getMinecraft()) : ScaledResolution.newInstance(Minecraft.getMinecraft(), Minecraft.getDisplayWidth(), Minecraft.getDisplayHeight())) - FontRenderer.getStringWidth(Minecraft.getFontRenderer(), module.name) - 3, 3 + (mult * 10), module.color);
                mult++;
            }
        }
    }
}
