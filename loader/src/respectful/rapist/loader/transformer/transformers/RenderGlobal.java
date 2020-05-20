package respectful.rapist.loader.transformer.transformers;

import org.objectweb.asm.tree.*;
import respectful.rapist.loader.transformer.Transformer;

public class RenderGlobal extends Transformer {
    @Override
    public void transform(ClassNode classNode) {
        for (MethodNode method : classNode.methods) {
            if (method.name.equals("func_147589_a") || method.name.equals("func_180446_a")) {
                InsnList insns = new InsnList();
                insns.add(new FieldInsnNode(GETSTATIC, "respectful/rapist/loader/mapping/Mappings", "EventManager", "Lrespectful/rapist/loader/mapping/mappings/EventManager;"));
                insns.add(new MethodInsnNode(INVOKEVIRTUAL, "respectful/rapist/loader/mapping/mappings/EventManager", "onRender", "()V", false));
                method.instructions.insertBefore(method.instructions.getLast().getPrevious(), insns);
                break;
            }
        }
    }
}
