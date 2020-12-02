package respectful.rapist.loader.transformer.transformers;

import org.objectweb.asm.tree.*;
import respectful.rapist.loader.transformer.Transformer;

public class Minecraft extends Transformer {

    @Override
    public void transform(ClassNode classNode) {
        for (MethodNode method : classNode.methods) {
            switch (method.name) {
                case "func_71407_l":
                    for (AbstractInsnNode instruction : method.instructions.toArray()) {
                        if ((instruction.getOpcode() == INVOKESTATIC || instruction.getOpcode() == ILOAD) && instruction.getNext().getOpcode() == BIPUSH && instruction.getNext().getNext().getOpcode() == IF_ICMPNE && instruction.getNext().getNext().getNext().getOpcode() == BIPUSH && instruction.getNext().getNext().getNext().getNext().getOpcode() == INVOKESTATIC && instruction.getNext().getNext().getNext().getNext().getNext().getOpcode() == IFEQ) {
                            InsnList insns = new InsnList();
                            insns.add(new FieldInsnNode(GETSTATIC, "respectful/rapist/loader/mapping/Mappings", "Events", "Lrespectful/rapist/loader/mapping/mappings/Events;"));
                            insns.add(new MethodInsnNode(INVOKESTATIC, "org/lwjgl/input/Keyboard", "getEventKey", "()I", false));
                            insns.add(new MethodInsnNode(INVOKEVIRTUAL, "respectful/rapist/loader/mapping/mappings/Events", "onKey", "(I)V", false));
                            method.instructions.insertBefore(instruction.getPrevious().getPrevious(), insns);
                            break;
                        }
                    }
                    break;
                case "func_71411_J":
                    InsnList insns = new InsnList();
                    insns.add(new FieldInsnNode(GETSTATIC, "respectful/rapist/loader/mapping/Mappings", "Events", "Lrespectful/rapist/loader/mapping/mappings/Events;"));
                    insns.add(new MethodInsnNode(INVOKEVIRTUAL, "respectful/rapist/loader/mapping/mappings/Events", "onTick", "()V", false));
                    method.instructions.insert(insns);
                    break;
            }
        }
    }
}