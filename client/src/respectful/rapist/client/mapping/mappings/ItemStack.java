package respectful.rapist.client.mapping.mappings;

import respectful.rapist.client.mapping.MappedClass;

public class ItemStack extends MappedClass {
    public ItemStack() {
        super("net.minecraft.item.ItemStack");
    }

    public Object getItem(Object obj) {
        try {
            return clazz.getDeclaredMethod("func_77973_b").invoke(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
