package respectful.rapist.client.mapping.mappings;

import respectful.rapist.client.mapping.MappedClass;
import respectful.rapist.client.mapping.Mappings;

public class GuiInventory extends MappedClass implements Mappings {
    public GuiInventory() {
        super("net.minecraft.client.gui.inventory.GuiInventory");
    }

    public Object newInstance(Object entityPlayer) {
        try {
            return clazz.getDeclaredConstructor(EntityPlayer.clazz).newInstance(entityPlayer);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
