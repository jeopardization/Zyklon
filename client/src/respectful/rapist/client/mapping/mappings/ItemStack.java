package respectful.rapist.client.mapping.mappings;

import respectful.rapist.client.mapping.MappedClass;

import java.lang.reflect.Method;

public class ItemStack extends MappedClass {
    private Method getItem;

    public ItemStack() {
        super("net.minecraft.item.ItemStack");
        try {
            getItem = clazz.getDeclaredMethod("func_77973_b");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Object getItem(Object obj) {
        try {
            return getItem.invoke(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
