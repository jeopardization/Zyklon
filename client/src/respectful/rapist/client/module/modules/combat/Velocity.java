package respectful.rapist.client.module.modules.combat;

import respectful.rapist.client.Events;
import respectful.rapist.client.mapping.Mappings;
import respectful.rapist.client.module.Module;
import respectful.rapist.client.util.Config;
import respectful.rapist.client.util.Random;

public class Velocity extends Module implements Mappings {
    public int chance = 80;
    public boolean reqSprint;
    public double x = 0.9D, y = 0.9D, z = 1.0D;

    public Velocity() {
        super(51, "Velocity", 0xE84393);
    }

    public static void intercept(Object packet) {
        if (Events.modules.velocity.enabled && S12PacketEntityVelocity.clazz.isInstance(packet) && Entity.getEntityID(Minecraft.getThePlayer()) == S12PacketEntityVelocity.getID(packet) && Random.nextInt(1, 100) <= Events.modules.velocity.chance && Config.safe(false, null, false, Events.modules.velocity.reqSprint, false)) {
            S12PacketEntityVelocity.setX(packet, (int) (S12PacketEntityVelocity.getX(packet) * Events.modules.velocity.x));
            S12PacketEntityVelocity.setY(packet, (int) (S12PacketEntityVelocity.getY(packet) * Events.modules.velocity.y));
            S12PacketEntityVelocity.setZ(packet, (int) (S12PacketEntityVelocity.getZ(packet) * Events.modules.velocity.z));
        }
    }
}