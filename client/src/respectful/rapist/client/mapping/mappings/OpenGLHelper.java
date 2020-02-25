package respectful.rapist.client.mapping.mappings;

import respectful.rapist.client.mapping.MappedClass;

import java.lang.reflect.Method;

public class OpenGLHelper extends MappedClass {
    private Method glBlendFunc;

    public OpenGLHelper() {
        super("net.minecraft.client.renderer.OpenGlHelper");
        try {
            glBlendFunc = clazz.getDeclaredMethod("func_148821_a", int.class, int.class, int.class, int.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void glBlendFunc(int p_148821_0_, int p_148821_1_, int p_148821_2_, int p_148821_3_) {
        try {
            glBlendFunc.invoke(null, p_148821_0_, p_148821_1_, p_148821_2_, p_148821_3_);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
