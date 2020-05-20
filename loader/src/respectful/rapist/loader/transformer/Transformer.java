package respectful.rapist.loader.transformer;

import jdk.internal.org.objectweb.asm.Opcodes;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public abstract class Transformer implements ClassFileTransformer, Opcodes {
    protected byte[] orig = null;

    public abstract void transform(ClassNode classNode);

    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        if (className.equals("net/minecraft/client/Minecraft") || className.equals("net/minecraft/entity/Entity") || className.equals("net/minecraft/client/renderer/RenderGlobal") || className.equals("net/minecraft/client/renderer/entity/Render") || className.equals("net/minecraft/client/renderer/EntityRenderer") || className.equals("net/minecraft/client/renderer/entity/RendererLivingEntity") || className.equals("net/minecraftforge/client/GuiIngameForge") || className.equals("net/minecraft/client/network/NetHandlerPlayClient")) {
            if (orig == null) {
                orig = classfileBuffer;
                ClassNode classNode = new ClassNode();
                ClassReader reader = new ClassReader(classfileBuffer);
                reader.accept(classNode, 0);
                transform(classNode);
                ClassWriter classWriter = new ClassWriter(reader, 1);
                classNode.accept(classWriter);
                return classWriter.toByteArray();
            }
            return orig;
        }
        return classfileBuffer;
    }
}
