package respectful.rapist.loader.transformer.transformers;

import org.objectweb.asm.tree.*;
import respectful.rapist.loader.Main;
import respectful.rapist.loader.transformer.Transformer;

public class Render extends Transformer {
    @Override
    public void transform(ClassNode classNode) {
        Main.origRender = orig;
        for (MethodNode method : classNode.methods) {
            if (method.name.equals("func_147906_a")) {
                for (AbstractInsnNode instruction : method.instructions.toArray()) {
                    if (instruction.getOpcode() == IFGT) {
                        InsnList insns = new InsnList();
                        LabelNode labelNode = new LabelNode();
                        insns.add(new FieldInsnNode(GETSTATIC, "respectful/rapist/loader/mapping/Mappings", "NameTags", "Lrespectful/rapist/loader/mapping/mappings/NameTags;"));
                        insns.add(new MethodInsnNode(INVOKEVIRTUAL, "respectful/rapist/loader/mapping/mappings/NameTags", "getEnabled", "()Z", false));
                        insns.add(new JumpInsnNode(IFEQ, labelNode));
                        insns.add(new InsnNode(RETURN));
                        insns.add(labelNode);
                        method.instructions.insert(instruction, insns);
                        break;
                    }
                }
                break;
            }
        }
    }
}
