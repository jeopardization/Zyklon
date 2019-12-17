package respectful.rapist.loader.transformer.transformers;

import org.objectweb.asm.tree.*;
import respectful.rapist.loader.Main;
import respectful.rapist.loader.transformer.Transformer;

public class Minecraft extends Transformer {
    @Override
    public void transform(ClassNode classNode) {
        Main.origMinecraft = orig;
        for (MethodNode method : classNode.methods) {
            switch (method.name) {
                case "func_71407_l":
                    for (AbstractInsnNode instruction : method.instructions.toArray()) {
                        if ((instruction.getOpcode() == INVOKESTATIC) && (instruction.getNext().getOpcode() == BIPUSH) && (instruction.getNext().getNext().getOpcode() == IF_ICMPNE) && (instruction.getNext().getNext().getNext().getOpcode() == BIPUSH) && (instruction.getNext().getNext().getNext().getNext().getOpcode() == INVOKESTATIC) && (instruction.getNext().getNext().getNext().getNext().getNext().getOpcode() == IFEQ)) {
                            InsnList insns = new InsnList();
                            insns.add(new MethodInsnNode(INVOKESTATIC, "org/lwjgl/input/Keyboard", "getEventKey", "()I", false));
                            insns.add(new MethodInsnNode(INVOKESTATIC, "respectful/rapist/loader/Main", "onKey", "(I)V", false));
                            method.instructions.insert(instruction.getNext().getNext().getNext().getNext().getNext().getNext().getNext().getNext().getNext().getNext().getNext(), insns);
                        }
                    }
                case "func_71411_J":
                    method.instructions.insert(new MethodInsnNode(INVOKESTATIC, "respectful/rapist/loader/Main", "onTick", "()V", false));
            }
        }
    }
}
