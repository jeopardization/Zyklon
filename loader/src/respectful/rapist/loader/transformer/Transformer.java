package respectful.rapist.loader.transformer;

import jdk.internal.org.objectweb.asm.Opcodes;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public abstract class Transformer implements ClassFileTransformer, Opcodes {
    protected byte[] orig;

    public abstract void transform(ClassNode classNode);

    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        orig = classfileBuffer;
        ClassNode classNode = new ClassNode();
        ClassReader reader = new ClassReader(classfileBuffer);
        reader.accept(classNode, 0);
        transform(classNode);
        ClassWriter classWriter = new ClassWriter(reader, 1);
        classNode.accept(classWriter);
        return classWriter.toByteArray();
    }
}
