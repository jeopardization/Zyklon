package respectful.rapist.loader.transformer.transformers;

import org.objectweb.asm.tree.*;
import respectful.rapist.loader.Main;
import respectful.rapist.loader.transformer.Transformer;

public class NetHandlerPlayClient extends Transformer {
    @Override
    public void transform(ClassNode classNode) {
        Main.origNetHandlerPlayClient = orig;
        for (MethodNode method : classNode.methods) {
            if (method.name.equals("func_147272_a")) {
                InsnList insns = new InsnList();
                insns.add(new FieldInsnNode(GETSTATIC, "respectful/rapist/loader/mapping/Mappings", "FakeLag", "Lrespectful/rapist/loader/mapping/mappings/FakeLag;"));
                insns.add(new MethodInsnNode(INVOKEVIRTUAL, "respectful/rapist/loader/mapping/mappings/FakeLag", "sleep", "()V", false));
                method.instructions.insert(method.instructions.getFirst(), insns);
                break;
            }
        }
    }
}
