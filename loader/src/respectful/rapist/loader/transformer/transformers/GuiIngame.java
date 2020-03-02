package respectful.rapist.loader.transformer.transformers;

import org.objectweb.asm.tree.*;
import respectful.rapist.loader.Main;
import respectful.rapist.loader.transformer.Transformer;

public class GuiIngame extends Transformer {
    @Override
    public void transform(ClassNode classNode) {
        Main.origGuiIngame = orig;
        for (MethodNode method : classNode.methods) {
            if (method.name.equals("func_73830_a")) {
                InsnList insns = new InsnList();
                insns.add(new FieldInsnNode(GETSTATIC, "respectful/rapist/loader/mapping/Mappings", "EventManager", "Lrespectful/rapist/loader/mapping/mappings/EventManager;"));
                insns.add(new MethodInsnNode(INVOKEVIRTUAL, "respectful/rapist/loader/mapping/mappings/EventManager", "onRenderGUI", "()V", false));
                method.instructions.insertBefore(method.instructions.getLast().getPrevious(), insns);
                break;
            }
        }
    }
}