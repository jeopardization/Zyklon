package respectful.rapist.loader.mapping.mappings;

import respectful.rapist.loader.mapping.MappedClass;
import respectful.rapist.loader.mapping.Mappings;

public class NameTags extends MappedClass {
    public NameTags() {
        super("respectful.rapist.client.module.modules.visuals.NameTags");
    }

    public boolean getEnabled() {
        try {
            return Mappings.Module.getEnabled(Mappings.ModuleManager.getNameTags(Mappings.EventManager.getModuleManager()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
