package respectful.rapist.client.mapping.mappings;

import respectful.rapist.client.mapping.MappedClass;

import java.lang.reflect.Method;

public class EntityClientPlayerMP extends MappedClass {
    private Method sendChatMessage;

    public EntityClientPlayerMP() {
        super("net.minecraft.client.entity.EntityClientPlayerMP");
        try {
            sendChatMessage = clazz.getDeclaredMethod("func_71165_d", String.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void sendChatMessage(Object obj, String text) {
        try {
            sendChatMessage.invoke(obj, text);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
