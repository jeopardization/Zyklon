package respectful.rapist.client.module;

import respectful.rapist.client.util.Config;

public abstract class Module {
    public String name, color;
    public int bind;
    public boolean enabled = false, singleUse;

    public Module(int bind, String name, String color, boolean singleUse) {
        this.bind = bind;
        this.name = name;
        this.color = color;
        this.singleUse = singleUse;
    }

    public void setEnabled(boolean enabled) {
        if (enabled != this.enabled) {
            if (enabled) {
                enable();
            } else {
                disable();
            }
            if (!singleUse) {
                Config.setEnabledCloud(this, this.enabled);
            }
        }
    }

    public void onTick() {
    }

    public void onRender() {
    }

    public void enable() {
        this.enabled = true;
    }

    public void disable() {
        this.enabled = false;
    }
}
