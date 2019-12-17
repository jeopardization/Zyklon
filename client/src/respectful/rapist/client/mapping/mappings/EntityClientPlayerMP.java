package respectful.rapist.client.mapping.mappings;

import respectful.rapist.client.mapping.MappedClass;

public class EntityClientPlayerMP extends MappedClass {
    public EntityClientPlayerMP() {
        super("net.minecraft.client.entity.EntityClientPlayerMP");
    }

    public void sendChatMessage(Object obj, String text) {
        try {
            clazz.getDeclaredMethod("func_71165_d", String.class).invoke(obj, text);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
