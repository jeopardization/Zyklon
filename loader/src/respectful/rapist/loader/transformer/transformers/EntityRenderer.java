package respectful.rapist.loader.transformer.transformers;

import org.objectweb.asm.tree.*;
import respectful.rapist.loader.Main;
import respectful.rapist.loader.transformer.Transformer;

public class EntityRenderer extends Transformer {
    @Override
    public void transform(ClassNode classNode) {
        Main.origEntityRenderer = orig;
        for (MethodNode method : classNode.methods) {
            switch (method.name) {
                case "func_78473_a":
                    for (AbstractInsnNode instruction : method.instructions.toArray()) {
                        if (instruction.getOpcode() == IFLE) {
                            InsnList insns = new InsnList();
                            insns.add(new FieldInsnNode(GETSTATIC, "respectful/rapist/loader/mapping/Mappings", "Reach", "Lrespectful/rapist/loader/mapping/mappings/Reach;"));
                            insns.add(new MethodInsnNode(INVOKEVIRTUAL, "respectful/rapist/loader/mapping/mappings/Reach", "getAdd", "()D", false));
                            insns.add(new InsnNode(DADD));
                            method.instructions.insert(instruction.getNext().getNext().getNext().getNext().getNext().getNext().getNext().getNext(), insns);
                            break;
                        }
                    }
                case "func_78471_a":
                    for (AbstractInsnNode instruction : method.instructions.toArray()) {
                        if (instruction.getOpcode() == ALOAD && instruction.getNext().getOpcode() == GETFIELD && instruction.getNext().getNext().getOpcode() == IFNE) {
                            InsnList insns = new InsnList();
                            insns.add(new FieldInsnNode(GETSTATIC, "respectful/rapist/loader/mapping/Mappings", "EventManager", "Lrespectful/rapist/loader/mapping/mappings/EventManager;"));
                            insns.add(new MethodInsnNode(INVOKEVIRTUAL, "respectful/rapist/loader/mapping/mappings/EventManager", "onRender", "()V", false));
                            method.instructions.insertBefore(instruction, insns);
                            break;
                        }
                    }
            }
        }
    }
}