package respectful.rapist.client.mapping.mappings;

import respectful.rapist.client.mapping.MappedClass;

import java.lang.reflect.Method;

public class CPSMod extends MappedClass {
    private Method addClick;

    public CPSMod() {
        super("pw.cinque.cpsmod.CPSMod");
        try {
            if (clazz != null) {
                addClick = clazz.getDeclaredMethod("addClick");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void addClick() {
        try {
            if (addClick != null) {
                addClick.invoke(null);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
