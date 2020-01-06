package respectful.rapist.loader.transformer.transformers;

import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import respectful.rapist.loader.Main;
import respectful.rapist.loader.transformer.Transformer;

public class GuiIngame extends Transformer {
    @Override
    public void transform(ClassNode classNode) {
        Main.origGuiIngame = orig;
        for (MethodNode method : classNode.methods) {
            if (method.name.equals("func_73830_a")) {
                method.instructions.insertBefore(method.instructions.getLast().getPrevious(), new MethodInsnNode(INVOKESTATIC, "respectful/rapist/loader/Main", "onRenderGUI", "()V", false));
                break;
            }
        }
    }
}