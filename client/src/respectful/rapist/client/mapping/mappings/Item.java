package respectful.rapist.client.mapping.mappings;

import respectful.rapist.client.mapping.MappedClass;
import respectful.rapist.client.mapping.Mappings;

import java.lang.reflect.Method;

public class Item extends MappedClass implements Mappings {
    private Method getIdFromItem, getItemStackDisplayName;

    public Item() {
        super("net.minecraft.item.Item");
        try {
            getIdFromItem = clazz.getDeclaredMethod("func_150891_b", clazz);
            getItemStackDisplayName = clazz.getDeclaredMethod("func_77653_i", ItemStack.clazz);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public int getIdFromItem(Object item) {
        try {
            return (int) getIdFromItem.invoke(null, clazz.cast(item));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public String getItemStackDisplayName(Object obj, Object itemStack) {
        try {
            return (String) getItemStackDisplayName.invoke(obj, ItemStack.clazz.cast(itemStack));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
