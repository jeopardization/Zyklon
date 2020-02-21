package respectful.rapist.loader.transformer.transformers;

import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import respectful.rapist.loader.Main;
import respectful.rapist.loader.transformer.Transformer;

public class NetHandlerPlayClient extends Transformer {
    @Override
    public void transform(ClassNode classNode) {
        Main.origNetHandlerPlayClient = orig;
        for (MethodNode method : classNode.methods) {
            if (method.name.equals("func_147272_a")) {
                method.instructions.insert(method.instructions.getFirst(), new MethodInsnNode(INVOKESTATIC, "respectful/rapist/loader/Main", "sleep", "()V", false));
                break;
            }
        }
    }
}
