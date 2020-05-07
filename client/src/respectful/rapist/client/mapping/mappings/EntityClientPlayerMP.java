package respectful.rapist.client.mapping.mappings;

import respectful.rapist.client.mapping.MappedClass;
import respectful.rapist.client.mapping.Mappings;

import java.lang.reflect.Method;

public class EntityClientPlayerMP extends MappedClass implements Mappings {
    private Method sendChatMessage;

    public EntityClientPlayerMP() {
        super(RealmsSharedConstants.getVersion().equals("1.8.9") ? "net.minecraft.client.entity.EntityPlayerSP" : "net.minecraft.client.entity.EntityClientPlayerMP");
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
