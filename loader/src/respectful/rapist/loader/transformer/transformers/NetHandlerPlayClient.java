package respectful.rapist.loader.transformer.transformers;

import org.objectweb.asm.tree.*;
import respectful.rapist.loader.Main;
import respectful.rapist.loader.transformer.Transformer;

public class NetHandlerPlayClient extends Transformer {
    @Override
    public void transform(ClassNode classNode) {
        Main.origNetHandlerPlayClient = orig;
        for (MethodNode method : classNode.methods) {
            InsnList insns;
            switch (method.name) {
                case "func_147272_a":
                    insns = new InsnList();
                    insns.add(new FieldInsnNode(GETSTATIC, "respectful/rapist/loader/mapping/Mappings", "FakeLag", "Lrespectful/rapist/loader/mapping/mappings/FakeLag;"));
                    insns.add(new MethodInsnNode(INVOKEVIRTUAL, "respectful/rapist/loader/mapping/mappings/FakeLag", "sleep", "()V", false));
                    method.instructions.insert(method.instructions.getFirst(), insns);
                case "func_147244_a":
                    insns = new InsnList();
                    insns.add(new FieldInsnNode(GETSTATIC, "respectful/rapist/loader/mapping/Mappings", "Velocity", "Lrespectful/rapist/loader/mapping/mappings/Velocity;"));
                    insns.add(new VarInsnNode(ALOAD, 1));
                    insns.add(new MethodInsnNode(INVOKEVIRTUAL, "respectful/rapist/loader/mapping/mappings/Velocity", "intercept", "(Ljava/lang/Object;)V", false));
                    method.instructions.insertBefore(method.instructions.getFirst(), insns);
            }
        }
    }
}
