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
    public static PlayerManager playerManager = new PlayerManager();
    private static Timer timer = new Timer();
    private static long refreshRate = 5000L;

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
            if (timer.elapsed(refreshRate)) {
                try {
                    String[] players = new Scanner(new URL("http://localhost:1337/players").openStream()).next().split(";");
                    /*
                    0: Friends
                    1: Enemies
                     */
                    if ((players.length >= 1)) {
                        playerManager.friends = players[0].split(",");
                    } else {
                        playerManager.friends = null;
                    }
                    if ((players.length >= 2)) {
                        playerManager.enemies = players[1].split(",");
                    } else {
                        playerManager.enemies = null;
                    }
                    String[] config = new Scanner(new URL("http://localhost:1337/config").openStream()).next().split(";");
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
                    35: Aimbot Target Selection
                    36: Aimbot Minimum Yaw Smoothing
                    37: Aimbot Maximum Yaw Smoothing
                    38: Aimbot Minimum Pitch Smoothing
                    39: Aimbot Maximum Pitch Smoothing
                    40: Aimbot Minimum Randomize Frequency
                    41: Aimbot Maximum Randomize Frequency
                    42: Reach Minimum Expansion
                    43: Reach Maximum Expansion
                    44: HitBoxes Expansion
                    45: WTap Distance
                    46: WTap Minimum Tap Delay
                    47: WTap Maximum Tap Delay
                    48: ThrowPot Minimum Throw Delay
                    49: ThrowPot Maximum Throw Delay
                    50: Refill Minimum Fill Delay
                    51: Refill Maximum Fill Delay
                    52: Refill Minimum Exit Delay
                    53: Refill Maximum Exit Delay
                    54: Refresh Rate
                    */
                    for (int i = 0; i < 11; i++) {
                        moduleManager.modules.get(i).bind = Integer.parseInt(config[i]);
                    }
                    for (int i = 11; i < 20; i++) {
                        int index = i - 11;
                        Module module = moduleManager.modules.get(index);
                        if (Integer.parseInt(config[i]) == 1) {
                            module.enable();
                        } else {
                            module.disable();
                        }
                    }
                    moduleManager.autoClicker.reqItem = Integer.parseInt(config[20]) == 1;
                    moduleManager.aimbot.reqItem = Integer.parseInt(config[21]) == 1;
                    moduleManager.reach.reqItem = Integer.parseInt(config[22]) == 1;
                    moduleManager.hitBoxes.reqItem = Integer.parseInt(config[23]) == 1;
                    moduleManager.wTap.reqItem = Integer.parseInt(config[24]) == 1;
                    moduleManager.autoClicker.itemWhitelist = Config.stringToIntArr(config[25]);
                    moduleManager.aimbot.itemWhitelist = Config.stringToIntArr(config[26]);
                    moduleManager.reach.itemWhitelist = Config.stringToIntArr(config[27]);
                    moduleManager.hitBoxes.itemWhitelist = Config.stringToIntArr(config[28]);
                    moduleManager.wTap.itemWhitelist = Config.stringToIntArr(config[29]);
                    moduleManager.autoClicker.minCPS = Float.parseFloat(config[30]);
                    moduleManager.autoClicker.maxCPS = Float.parseFloat(config[31]);
                    moduleManager.aimbot.dist = Float.parseFloat(config[32]);
                    moduleManager.aimbot.FOV = Float.parseFloat(config[33]);
                    moduleManager.aimbot.reqMouse = Integer.parseInt(config[34]) == 1;
                    moduleManager.aimbot.targetSelection = Integer.parseInt(config[35]);
                    moduleManager.aimbot.minYawSmooth = Float.parseFloat(config[36]);
                    moduleManager.aimbot.maxYawSmooth = Float.parseFloat(config[37]);
                    moduleManager.aimbot.minPitchSmooth = Float.parseFloat(config[38]);
                    moduleManager.aimbot.maxPitchSmooth = Float.parseFloat(config[39]);
                    moduleManager.aimbot.minRand = Integer.parseInt(config[40]);
                    moduleManager.aimbot.maxRand = Integer.parseInt(config[41]);
                    moduleManager.reach.minExpansion = Double.parseDouble(config[42]);
                    moduleManager.reach.maxExpansion = Double.parseDouble(config[43]);
                    moduleManager.hitBoxes.expansion = Float.parseFloat(config[44]);
                    moduleManager.wTap.dist = Float.parseFloat(config[45]);
                    moduleManager.wTap.minTapDelay = Integer.parseInt(config[46]);
                    moduleManager.wTap.maxTapDelay = Integer.parseInt(config[47]);
                    moduleManager.throwPot.minThrowDelay = Integer.parseInt(config[48]);
                    moduleManager.throwPot.maxThrowDelay = Integer.parseInt(config[49]);
                    moduleManager.refill.minFillDelay = Integer.parseInt(config[50]);
                    moduleManager.refill.maxFillDelay = Integer.parseInt(config[51]);
                    moduleManager.refill.minExitDelay = Integer.parseInt(config[52]);
                    moduleManager.refill.maxExitDelay = Integer.parseInt(config[53]);
                    refreshRate = Integer.parseInt(config[54]) * 1000L;
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
