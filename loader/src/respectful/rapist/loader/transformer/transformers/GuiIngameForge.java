package respectful.rapist.loader.transformer.transformers;

import org.objectweb.asm.tree.*;
import respectful.rapist.loader.transformer.Transformer;

public class GuiIngameForge extends Transformer {
    @Override
    public void transform(ClassNode classNode) {
        for (MethodNode method : classNode.methods) {
            if (method.name.equals("func_73830_a") || method.name.equals("func_175180_a")) {
                InsnList insns = new InsnList();
                insns.add(new FieldInsnNode(GETSTATIC, "respectful/rapist/loader/mapping/Mappings", "Events", "Lrespectful/rapist/loader/mapping/mappings/Events;"));
                insns.add(new MethodInsnNode(INVOKEVIRTUAL, "respectful/rapist/loader/mapping/mappings/Events", "onRenderGUI", "()V", false));
                method.instructions.insertBefore(method.instructions.getLast().getPrevious(), insns);
                break;
            }
        }
    }
}