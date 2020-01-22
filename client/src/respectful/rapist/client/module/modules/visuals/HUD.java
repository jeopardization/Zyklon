package respectful.rapist.client.module.modules.visuals;

import respectful.rapist.client.EventManager;
import respectful.rapist.client.mapping.Mappings;
import respectful.rapist.client.module.Module;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HUD extends Module implements Mappings {
    public HUD() {
        super(200, "HUD", "FF0000");
    }

    @Override
    public void onRenderGUI() {
        FontRenderer.drawStringWithShadow(Minecraft.getFontRenderer(), "Zyklon", 3, 3, "8DC63F");
        FontRenderer.drawStringWithShadow(Minecraft.getFontRenderer(), new SimpleDateFormat("h:mm a").format(Calendar.getInstance().getTime()), FontRenderer.getStringWidth(Minecraft.getFontRenderer(), "Zyklon") + 6, 3, "FFFFFF");
        int mult = 0;
        for (Module module : EventManager.moduleManager.modules) {
            if (module.enabled && !module.equals(this)) {
                FontRenderer.drawStringWithShadow(Minecraft.getFontRenderer(), module.name, ScaledResolution.getScaledWidth(ScaledResolution.newInstance(Minecraft.getMinecraft(), Minecraft.getDisplayWidth(), Minecraft.getDisplayHeight())) - FontRenderer.getStringWidth(Minecraft.getFontRenderer(), module.name) - 3, 3 + (mult * 10), module.color);
                mult++;
            }
        }
    }
}
