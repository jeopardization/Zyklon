package respectful.rapist.loader.transformer.transformers;

import org.objectweb.asm.tree.*;
import respectful.rapist.loader.transformer.Transformer;

public class Entity extends Transformer {
    @Override
    public void transform(ClassNode classNode) {
        for (MethodNode method : classNode.methods) {
            if (method.name.equals("func_70111_Y")) {
                for (AbstractInsnNode instruction : method.instructions.toArray()) {
                    if (instruction.getOpcode() == LDC) {
                        InsnList insns = new InsnList();
                        insns.add(new FieldInsnNode(GETSTATIC, "respectful/rapist/loader/mapping/Mappings", "HitBoxes", "Lrespectful/rapist/loader/mapping/mappings/HitBoxes;"));
                        insns.add(new MethodInsnNode(INVOKEVIRTUAL, "respectful/rapist/loader/mapping/mappings/HitBoxes", "getAdd", "()F", false));
                        insns.add(new InsnNode(FADD));
                        method.instructions.insertBefore(instruction.getNext(), insns);
                        break;
                    }
                }
                break;
            }
        }
    }
}