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

    public static void onKey(int keyCode) {
        for (Module module : moduleManager.modules) {
            if (keyCode == module.bind) {
                module.setEnabled(!module.enabled);
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
            if (timer.elapsed(5000L)) {
                try {
                    String[] values = new Scanner(new URL("http://localhost:1337/config").openStream()).next().split(";");
                    /*
                    0: AutoClicker Bind
                    1: Aimbot Bind
                    2: Brightness Bind
                    3: Reach Bind
                    4: HitBoxes Bind
                    5: WTap Bind
                    6: Self Destruct Bind
                    7: HUD Bind
                    8: ThrowPot Bind
                    9: Refill Bind
                    10: AutoClicker State
                    11: Aimbot State
                    12: Brightness State
                    13: Reach State
                    14: HitBoxes State
                    15: WTap State
                    16: Self Destruct State
                    17: HUD State
                    18: AutoClicker Require Item
                    19: Aimbot Require Item
                    20: Reach Require Item
                    21: HitBoxes Require Item
                    22: WTap Require Item
                    23: AutoClicker Item Whitelist
                    24: Aimbot Item Whitelist
                    25: Reach Item Whitelist
                    26: HitBoxes Item Whitelist
                    27: WTap Item Whitelist
                    28: AutoClicker Minimum CPS
                    29: AutoClicker Maximum CPS
                    30: Aimbot Distance
                    31: Aimbot FOV
                    32: Aimbot Require Mouse
                    33: Aimbot Minimum Yaw Smoothing
                    34: Aimbot Maximum Yaw Smoothing
                    35: Aimbot Minimum Pitch Smoothing
                    36: Aimbot Maximum Pitch Smoothing
                    37: Aimbot Minimum Randomize Frequency
                    38: Aimbot Maximum Randomize Frequency
                    39: Reach Minimum Expansion
                    40: Reach Maximum Expansion
                    41: HitBoxes Expansion
                    42: WTap Distance
                    43: WTap Minimum Tap Delay
                    44: WTap Maximum Tap Delay
                    45: ThrowPot Minimum Throw Delay
                    46: ThrowPot Maximum Throw Delay
                    47: Refill Minimum Fill Delay
                    48: Refill Maximum Fill Delay
                    49: Refill Minimum Exit Delay
                    50: Refill Maximum Exit Delay
                    */
                    for (int i = 0; i < 10; i++) {
                        moduleManager.modules.get(i).bind = Integer.parseInt(values[i]);
                    }
                    for (int i = 10; i < 18; i++) {
                        int index = i - 10;
                        Module module = moduleManager.modules.get(index);
                        if (Integer.parseInt(values[i]) == 1) {
                            module.enable();
                        } else {
                            module.disable();
                        }
                    }
                    moduleManager.autoClicker.reqItem = Integer.parseInt(values[18]) == 1;
                    moduleManager.aimbot.reqItem = Integer.parseInt(values[19]) == 1;
                    moduleManager.reach.reqItem = Integer.parseInt(values[20]) == 1;
                    moduleManager.hitBoxes.reqItem = Integer.parseInt(values[21]) == 1;
                    moduleManager.wTap.reqItem = Integer.parseInt(values[22]) == 1;
                    moduleManager.autoClicker.itemWhitelist = Config.stringToIntArr(values[23]);
                    moduleManager.aimbot.itemWhitelist = Config.stringToIntArr(values[24]);
                    moduleManager.reach.itemWhitelist = Config.stringToIntArr(values[25]);
                    moduleManager.hitBoxes.itemWhitelist = Config.stringToIntArr(values[26]);
                    moduleManager.wTap.itemWhitelist = Config.stringToIntArr(values[27]);
                    moduleManager.autoClicker.minCPS = Float.parseFloat(values[28]);
                    moduleManager.autoClicker.maxCPS = Float.parseFloat(values[29]);
                    moduleManager.aimbot.dist = Float.parseFloat(values[30]);
                    moduleManager.aimbot.FOV = Float.parseFloat(values[31]);
                    moduleManager.aimbot.reqMouse = Integer.parseInt(values[32]) == 1;
                    moduleManager.aimbot.minYawSmooth = Float.parseFloat(values[33]);
                    moduleManager.aimbot.maxYawSmooth = Float.parseFloat(values[34]);
                    moduleManager.aimbot.minPitchSmooth = Float.parseFloat(values[35]);
                    moduleManager.aimbot.maxPitchSmooth = Float.parseFloat(values[36]);
                    moduleManager.aimbot.minRand = Integer.parseInt(values[37]);
                    moduleManager.aimbot.maxRand = Integer.parseInt(values[38]);
                    moduleManager.reach.minExpansion = Double.parseDouble(values[39]);
                    moduleManager.reach.maxExpansion = Double.parseDouble(values[40]);
                    moduleManager.hitBoxes.expansion = Float.parseFloat(values[41]);
                    moduleManager.wTap.dist = Float.parseFloat(values[42]);
                    moduleManager.wTap.minTapDelay = Integer.parseInt(values[43]);
                    moduleManager.wTap.maxTapDelay = Integer.parseInt(values[44]);
                    moduleManager.throwPot.minThrowDelay = Integer.parseInt(values[45]);
                    moduleManager.throwPot.maxThrowDelay = Integer.parseInt(values[46]);
                    moduleManager.refill.minFillDelay = Integer.parseInt(values[47]);
                    moduleManager.refill.maxFillDelay = Integer.parseInt(values[48]);
                    moduleManager.refill.minExitDelay = Integer.parseInt(values[49]);
                    moduleManager.refill.maxExitDelay = Integer.parseInt(values[50]);
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
