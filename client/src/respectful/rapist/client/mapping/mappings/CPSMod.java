package respectful.rapist.client.mapping.mappings;

import respectful.rapist.client.mapping.MappedClass;

public class CPSMod extends MappedClass {
    public CPSMod() {
        super("pw.cinque.cpsmod.CPSMod");
    }

    public void addClick() {
        try {
            if (clazz != null) {
                clazz.getDeclaredMethod("addClick").invoke(null);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
