package respectful.rapist.client.module.modules.combat;

import respectful.rapist.client.EventManager;
import respectful.rapist.client.mapping.Mappings;
import respectful.rapist.client.module.Module;
import respectful.rapist.client.util.Config;
import respectful.rapist.client.util.Random;

public class Velocity extends Module implements Mappings {
    public int chance = 80;
    public boolean reqSprint;
    public double x = 0.8D, y = 0.8D, z = 1.0D;

    public Velocity() {
        super(51, "Velocity", 0xE84393);
    }

    public static void intercept(Object packet) {
        if (EventManager.moduleManager.velocity.enabled && S12PacketEntityVelocity.clazz.isInstance(packet) && Entity.getEntityID(Minecraft.getThePlayer()) == S12PacketEntityVelocity.getID(packet) && Random.nextInt(1, 100) <= EventManager.moduleManager.velocity.chance && Config.safe(false, null, false, EventManager.moduleManager.velocity.reqSprint, false)) {
            S12PacketEntityVelocity.setX(packet, (int) (S12PacketEntityVelocity.getX(packet) * EventManager.moduleManager.velocity.x));
            S12PacketEntityVelocity.setY(packet, (int) (S12PacketEntityVelocity.getY(packet) * EventManager.moduleManager.velocity.y));
            S12PacketEntityVelocity.setZ(packet, (int) (S12PacketEntityVelocity.getZ(packet) * EventManager.moduleManager.velocity.z));
        }
    }
}