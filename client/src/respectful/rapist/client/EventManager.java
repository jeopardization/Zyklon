package respectful.rapist.client;

import respectful.rapist.client.mapping.Mappings;
import respectful.rapist.client.module.Module;
import respectful.rapist.client.module.ModuleManager;
import respectful.rapist.client.util.Config;
import respectful.rapist.client.util.Timer;

import java.net.URL;
import java.util.Scanner;

public class EventManager {
    public static ModuleManager moduleManager = new ModuleManager();
    private static Timer timer = new Timer();
    private static long refreshRate = 5L;

    public static void onKey(int keyCode) {
        for (Module module : moduleManager.modules) {
            if (keyCode == module.bind) {
                module.setEnabled(!module.enabled);
            }
        }
    }

    public static void onRenderGUI() {
        for (Module module : moduleManager.modules) {
            if (module.enabled) {
                module.onRenderGUI();
            }
        }
    }

    public static void onRender() {
        for (Module module : moduleManager.modules) {
            if (module.enabled) {
                module.onRender();
            }
        }
    }

    public static void onTick() {
        if (Mappings.Minecraft.getTheWorld() != null) {
            if (timer.elapsed(refreshRate * 1000L)) {
                try {
                    String[] values = new Scanner(new URL("http://localhost:1337/config").openStream()).next().split(";");
                    /*
                    0: AutoClicker Bind
                    1: Aimbot Bind
                    2: NameTags Bind
                    3: Brightness Bind
                    4: Reach Bind
                    5: HitBoxes Bind
                    6: WTap Bind
                    7: Self Destruct Bind
                    8: HUD Bind
                    9: ThrowPot Bind
                    10: Refill Bind
                    11: AutoClicker State
                    12: Aimbot State
                    13: NameTags State
                    14: Brightness State
                    15: Reach State
                    16: HitBoxes State
                    17: WTap State
                    18: Self Destruct State
                    19: HUD State
                    20: AutoClicker Require Item
                    21: Aimbot Require Item
                    22: Reach Require Item
                    23: HitBoxes Require Item
                    24: WTap Require Item
                    25: AutoClicker Item Whitelist
                    26: Aimbot Item Whitelist
                    27: Reach Item Whitelist
                    28: HitBoxes Item Whitelist
                    29: WTap Item Whitelist
                    30: AutoClicker Minimum CPS
                    31: AutoClicker Maximum CPS
                    32: Aimbot Distance
                    33: Aimbot FOV
                    34: Aimbot Require Mouse
                    35: Aimbot Minimum Yaw Smoothing
                    36: Aimbot Maximum Yaw Smoothing
                    37: Aimbot Minimum Pitch Smoothing
                    38: Aimbot Maximum Pitch Smoothing
                    39: Aimbot Minimum Randomize Frequency
                    40: Aimbot Maximum Randomize Frequency
                    41: Reach Minimum Expansion
                    42: Reach Maximum Expansion
                    43: HitBoxes Expansion
                    44: WTap Distance
                    45: WTap Minimum Tap Delay
                    46: WTap Maximum Tap Delay
                    47: ThrowPot Minimum Throw Delay
                    48: ThrowPot Maximum Throw Delay
                    49: Refill Minimum Fill Delay
                    50: Refill Maximum Fill Delay
                    51: Refill Minimum Exit Delay
                    52: Refill Maximum Exit Delay
                    53: Refresh Rate
                    */
                    for (int i = 0; i < 11; i++) {
                        moduleManager.modules.get(i).bind = Integer.parseInt(values[i]);
                    }
                    for (int i = 11; i < 20; i++) {
                        int index = i - 11;
                        Module module = moduleManager.modules.get(index);
                        if (Integer.parseInt(values[i]) == 1) {
                            module.enable();
                        } else {
                            module.disable();
                        }
                    }
                    moduleManager.autoClicker.reqItem = Integer.parseInt(values[20]) == 1;
                    moduleManager.aimbot.reqItem = Integer.parseInt(values[21]) == 1;
                    moduleManager.reach.reqItem = Integer.parseInt(values[22]) == 1;
                    moduleManager.hitBoxes.reqItem = Integer.parseInt(values[23]) == 1;
                    moduleManager.wTap.reqItem = Integer.parseInt(values[24]) == 1;
                    moduleManager.autoClicker.itemWhitelist = Config.stringToIntArr(values[25]);
                    moduleManager.aimbot.itemWhitelist = Config.stringToIntArr(values[26]);
                    moduleManager.reach.itemWhitelist = Config.stringToIntArr(values[27]);
                    moduleManager.hitBoxes.itemWhitelist = Config.stringToIntArr(values[28]);
                    moduleManager.wTap.itemWhitelist = Config.stringToIntArr(values[29]);
                    moduleManager.autoClicker.minCPS = Float.parseFloat(values[30]);
                    moduleManager.autoClicker.maxCPS = Float.parseFloat(values[31]);
                    moduleManager.aimbot.dist = Float.parseFloat(values[32]);
                    moduleManager.aimbot.FOV = Float.parseFloat(values[33]);
                    moduleManager.aimbot.reqMouse = Integer.parseInt(values[34]) == 1;
                    moduleManager.aimbot.minYawSmooth = Float.parseFloat(values[35]);
                    moduleManager.aimbot.maxYawSmooth = Float.parseFloat(values[36]);
                    moduleManager.aimbot.minPitchSmooth = Float.parseFloat(values[37]);
                    moduleManager.aimbot.maxPitchSmooth = Float.parseFloat(values[38]);
                    moduleManager.aimbot.minRand = Integer.parseInt(values[39]);
                    moduleManager.aimbot.maxRand = Integer.parseInt(values[40]);
                    moduleManager.reach.minExpansion = Double.parseDouble(values[41]);
                    moduleManager.reach.maxExpansion = Double.parseDouble(values[42]);
                    moduleManager.hitBoxes.expansion = Float.parseFloat(values[43]);
                    moduleManager.wTap.dist = Float.parseFloat(values[44]);
                    moduleManager.wTap.minTapDelay = Integer.parseInt(values[45]);
                    moduleManager.wTap.maxTapDelay = Integer.parseInt(values[46]);
                    moduleManager.throwPot.minThrowDelay = Integer.parseInt(values[47]);
                    moduleManager.throwPot.maxThrowDelay = Integer.parseInt(values[48]);
                    moduleManager.refill.minFillDelay = Integer.parseInt(values[49]);
                    moduleManager.refill.maxFillDelay = Integer.parseInt(values[50]);
                    moduleManager.refill.minExitDelay = Integer.parseInt(values[51]);
                    moduleManager.refill.maxExitDelay = Integer.parseInt(values[52]);
                    refreshRate = Integer.parseInt(values[53]);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                timer = new Timer();
            }
            for (Module module : moduleManager.modules) {
                if (module.enabled) {
                    module.onTick();
                }
            }
        }
    }
}
