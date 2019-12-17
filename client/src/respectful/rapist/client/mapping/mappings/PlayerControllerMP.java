package respectful.rapist.client.mapping.mappings;

import respectful.rapist.client.mapping.MappedClass;
import respectful.rapist.client.mapping.Mappings;

public class PlayerControllerMP extends MappedClass {
    public PlayerControllerMP() {
        super("net.minecraft.client.multiplayer.PlayerControllerMP");
    }

    public Object windowClick(Object obj, int windowID, int slotID, int p_78753_3_, int p_78753_4_, Object entityPlayer) {
        try {
            return clazz.getDeclaredMethod("func_78753_a", int.class, int.class, int.class, int.class, Mappings.EntityPlayer.clazz).invoke(obj, windowID, slotID, p_78753_3_, p_78753_4_, entityPlayer);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
