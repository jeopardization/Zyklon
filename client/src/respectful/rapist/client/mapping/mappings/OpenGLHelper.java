package respectful.rapist.client.mapping.mappings;

import respectful.rapist.client.mapping.MappedClass;

public class OpenGLHelper extends MappedClass {
    public OpenGLHelper() {
        super("net.minecraft.client.renderer.OpenGlHelper");
    }

    public void glBlendFunc(int p_148821_0_, int p_148821_1_, int p_148821_2_, int p_148821_3_) {
        try {
            clazz.getDeclaredMethod("func_148821_a", int.class, int.class, int.class, int.class).invoke(null, p_148821_0_, p_148821_1_, p_148821_2_, p_148821_3_);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
