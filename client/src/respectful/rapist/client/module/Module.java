package respectful.rapist.client.module;

import respectful.rapist.client.util.Config;

public abstract class Module {
    public String name, color;
    public int bind;
    public boolean enabled = false;

    public Module(int bind, String name, String color) {
        this.bind = bind;
        this.name = name;
        this.color = color;
    }

    public void setEnabled(boolean enabled) {
        if (enabled != this.enabled) {
            if (enabled) {
                enable();
            } else {
                disable();
            }
            if (!color.equals("000000")) {
                Config.setEnabledCloud(this, this.enabled);
            }
        }
    }

    public void onTick() {
    }

    public void onRenderGUI() {
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
