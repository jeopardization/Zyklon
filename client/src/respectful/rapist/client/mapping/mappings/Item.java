package respectful.rapist.client.mapping.mappings;

import respectful.rapist.client.mapping.MappedClass;
import respectful.rapist.client.mapping.Mappings;

public class Item extends MappedClass implements Mappings {
    public Item() {
        super("net.minecraft.item.Item");
    }

    public int getIdFromItem(Object item) {
        try {
            return (int) clazz.getDeclaredMethod("func_150891_b", clazz).invoke(null, clazz.cast(item));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public String getItemStackDisplayName(Object obj, Object itemStack) {
        try {
            return (String) clazz.getDeclaredMethod("func_77653_i", ItemStack.clazz).invoke(obj, ItemStack.clazz.cast(itemStack));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
