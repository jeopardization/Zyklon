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
                InsnList insns = new InsnList();
                insns.add(new FieldInsnNode(GETSTATIC, "respectful/rapist/loader/mapping/Mappings", "Reach", "Lrespectful/rapist/loader/mapping/mappings/Reach;"));
                insns.add(new MethodInsnNode(INVOKEVIRTUAL, "respectful/rapist/loader/mapping/mappings/Reach", "getAdd", "()D", false));
                insns.add(new InsnNode(DADD));
                for (AbstractInsnNode instruction : method.instructions.toArray()) {
                    if (instruction.getOpcode() == IFLE && instruction.getNext().getNext().getNext().getOpcode() == LDC) {
                        method.instructions.insert(instruction.getNext().getNext().getNext().getNext().getNext().getNext().getNext().getNext(), insns);
                        break;
                    } else if (instruction.getOpcode() == INVOKEVIRTUAL && instruction.getNext().getOpcode() == LDC) {
                        method.instructions.insert(instruction.getNext(), insns);
                        break;
                    }
                }
                break;
            }
        }
    }
}