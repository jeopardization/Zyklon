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
                    3: HitBoxes Bind
                    4: WTap Bind
                    5: Self Destruct Bind
                    6: HUD Bind
                    7: ThrowPot Bind
                    8: Refill Bind
                    9: AutoClicker State
                    10: Aimbot State
                    11: Brightness State
                    12: HitBoxes State
                    13: WTap State
                    14: Self Destruct State
                    15: HUD State
                    16: AutoClicker Require Item
                    17: Aimbot Require Item
                    18: HitBoxes Require Item
                    19: WTap Require Item
                    20: AutoClicker Item Whitelist
                    21: Aimbot Item Whitelist
                    22: HitBoxes Item Whitelist
                    23: WTap Item Whitelist
                    24: AutoClicker Minimum CPS
                    25: AutoClicker Maximum CPS
                    26: Aimbot Distance
                    27: Aimbot FOV
                    28: Aimbot Require Mouse
                    29: Aimbot Minimum Yaw Smoothing
                    30: Aimbot Maximum Yaw Smoothing
                    31: Aimbot Minimum Pitch Smoothing
                    32: Aimbot Maximum Pitch Smoothing
                    33: Aimbot Minimum Randomize Frequency
                    34: Aimbot Maximum Randomize Frequency
                    35: HitBoxes Expansion
                    36: WTap Distance
                    37: WTap Minimum Tap Delay
                    38: WTap Maximum Tap Delay
                    39: ThrowPot Minimum Throw Delay
                    40: ThrowPot Maximum Throw Delay
                    41: Refill Minimum Fill Delay
                    42: Refill Maximum Fill Delay
                    43: Refill Minimum Exit Delay
                    44: Refill Maximum Exit Delay
                    */
                    for (int i = 0; i < 9; i++) {
                        moduleManager.modules.get(i).bind = Integer.parseInt(values[i]);
                    }
                    for (int i = 9; i < 16; i++) {
                        int index = i - 9;
                        Module module = moduleManager.modules.get(index);
                        if (Integer.parseInt(values[i]) == 1) {
                            module.enable();
                        } else {
                            module.disable();
                        }
                    }
                    moduleManager.autoClicker.reqItem = Integer.parseInt(values[16]) == 1;
                    moduleManager.aimbot.reqItem = Integer.parseInt(values[17]) == 1;
                    moduleManager.hitBoxes.reqItem = Integer.parseInt(values[18]) == 1;
                    moduleManager.wTap.reqItem = Integer.parseInt(values[19]) == 1;
                    moduleManager.autoClicker.itemWhitelist = Config.stringToIntArr(values[20]);
                    moduleManager.aimbot.itemWhitelist = Config.stringToIntArr(values[21]);
                    moduleManager.hitBoxes.itemWhitelist = Config.stringToIntArr(values[22]);
                    moduleManager.wTap.itemWhitelist = Config.stringToIntArr(values[23]);
                    moduleManager.autoClicker.minCPS = Float.parseFloat(values[24]);
                    moduleManager.autoClicker.maxCPS = Float.parseFloat(values[25]);
                    moduleManager.aimbot.dist = Float.parseFloat(values[26]);
                    moduleManager.aimbot.FOV = Float.parseFloat(values[27]);
                    moduleManager.aimbot.reqMouse = Integer.parseInt(values[28]) == 1;
                    moduleManager.aimbot.minYawSmooth = Float.parseFloat(values[29]);
                    moduleManager.aimbot.maxYawSmooth = Float.parseFloat(values[30]);
                    moduleManager.aimbot.minPitchSmooth = Float.parseFloat(values[31]);
                    moduleManager.aimbot.maxPitchSmooth = Float.parseFloat(values[32]);
                    moduleManager.aimbot.minRand = Integer.parseInt(values[33]);
                    moduleManager.aimbot.maxRand = Integer.parseInt(values[34]);
                    moduleManager.hitBoxes.expansion = Float.parseFloat(values[35]);
                    moduleManager.wTap.dist = Float.parseFloat(values[36]);
                    moduleManager.wTap.minTapDelay = Integer.parseInt(values[37]);
                    moduleManager.wTap.maxTapDelay = Integer.parseInt(values[38]);
                    moduleManager.throwPot.minThrowDelay = Integer.parseInt(values[39]);
                    moduleManager.throwPot.maxThrowDelay = Integer.parseInt(values[40]);
                    moduleManager.refill.minFillDelay = Integer.parseInt(values[41]);
                    moduleManager.refill.maxFillDelay = Integer.parseInt(values[42]);
                    moduleManager.refill.minExitDelay = Integer.parseInt(values[43]);
                    moduleManager.refill.maxExitDelay = Integer.parseInt(values[44]);
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
