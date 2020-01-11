package respectful.rapist.loader.transformer.transformers;

import org.objectweb.asm.tree.*;
import respectful.rapist.loader.Main;
import respectful.rapist.loader.transformer.Transformer;

public class RendererLivingEntity extends Transformer {
    @Override
    public void transform(ClassNode classNode) {
        Main.origRendererLivingEntity = orig;
        for (MethodNode method : classNode.methods) {
            if (method.name.equals("func_77033_b")) {
                for (AbstractInsnNode instruction : method.instructions.toArray()) {
                    if (instruction.getOpcode() == INVOKEINTERFACE) {
                        InsnList insns = new InsnList();
                        LabelNode labelNode = new LabelNode();
                        insns.add(new MethodInsnNode(INVOKESTATIC, "respectful/rapist/loader/Main", "getNametagsEnabled", "()Z", false));
                        insns.add(new JumpInsnNode(IFEQ, labelNode));
                        insns.add(new InsnNode(RETURN));
                        insns.add(labelNode);
                        method.instructions.insert(instruction.getNext().getNext().getNext().getNext().getNext().getNext(), insns);
                        break;
                    }
                }
                break;
            }
        }
    }
}
