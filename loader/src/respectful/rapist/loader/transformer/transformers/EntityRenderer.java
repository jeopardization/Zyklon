package respectful.rapist.loader.transformer.transformers;

import org.objectweb.asm.tree.*;
import respectful.rapist.loader.Main;
import respectful.rapist.loader.transformer.Transformer;

public class EntityRenderer extends Transformer {
    @Override
    public void transform(ClassNode classNode) {
        Main.origEntityRenderer = orig;
        for (MethodNode method : classNode.methods) {
            if (method.name.equals("func_78473_a")) {
                for (AbstractInsnNode instruction : method.instructions.toArray()) {
                    if (instruction.getOpcode() == IFLE) {
                        InsnList insns = new InsnList();
                        insns.add(new MethodInsnNode(INVOKESTATIC, "respectful/rapist/loader/Main", "getReachAdd", "()D", false));
                        insns.add(new InsnNode(DADD));
                        method.instructions.insert(instruction.getNext().getNext().getNext().getNext().getNext().getNext().getNext().getNext(), insns);
                        break;
                    }
                }
            }
        }
    }
}
