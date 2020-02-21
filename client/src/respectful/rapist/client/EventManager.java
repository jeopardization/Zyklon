package respectful.rapist.client;

import respectful.rapist.client.mapping.Mappings;
import respectful.rapist.client.module.Module;
import respectful.rapist.client.module.ModuleManager;
import respectful.rapist.client.util.Config;
import respectful.rapist.client.util.Timer;

import java.net.URL;
import java.util.Scanner;

public class EventManager implements Mappings {
    public static ModuleManager moduleManager = new ModuleManager();
    public static PlayerManager playerManager = new PlayerManager();
    private static Timer timer = new Timer();
    private static int refreshRate = 5000;

    public static void onKey(int keyCode) {
        for (Module module : moduleManager.modules) {
            if (keyCode == module.bind) {
                module.toggle();
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
        if (Minecraft.getTheWorld() != null) {
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
                    6: Fake Lag Bind
                    7: WTap Bind
                    8: Self Destruct Bind
                    9: HUD Bind
                    10: ThrowPot Bind
                    11: Refill Bind
                    12: AutoClicker State
                    13: Aimbot State
                    14: NameTags State
                    15: Brightness State
                    16: Reach State
                    17: HitBoxes State
                    18: Fake Lag State
                    19: WTap State
                    20: Self Destruct State
                    21: HUD State
                    22: AutoClicker Require Item
                    23: Aimbot Require Item
                    24: Reach Require Item
                    25: HitBoxes Require Item
                    26: WTap Require Item
                    27: AutoClicker Item Whitelist
                    28: Aimbot Item Whitelist
                    29: Reach Item Whitelist
                    30: HitBoxes Item Whitelist
                    31: WTap Item Whitelist
                    32: AutoClicker Minimum CPS
                    33: AutoClicker Maximum CPS
                    34: Aimbot Distance
                    35: Aimbot FOV
                    36: Aimbot Require Mouse
                    37: Aimbot Target Selection
                    38: Aimbot Minimum Yaw Smoothing
                    39: Aimbot Maximum Yaw Smoothing
                    40: Aimbot Minimum Pitch Smoothing
                    41: Aimbot Maximum Pitch Smoothing
                    42: Aimbot Minimum Randomize Frequency
                    43: Aimbot Maximum Randomize Frequency
                    44: Reach Minimum Expansion
                    45: Reach Maximum Expansion
                    46: HitBoxes Expansion
                    47: Fake Lag Distance
                    48: Fake Lag FOV
                    49: Fake Lag Minimum Delay
                    50: Fake Lag Maximum Delay
                    51: WTap Distance
                    52: WTap FOV
                    53: WTap Minimum Tap Delay
                    54: WTap Maximum Tap Delay
                    55: ThrowPot Minimum Throw Delay
                    56: ThrowPot Maximum Throw Delay
                    57: Refill Minimum Fill Delay
                    58: Refill Maximum Fill Delay
                    59: Refill Minimum Exit Delay
                    60: Refill Maximum Exit Delay
                    61: Refresh Rate
                    */
                    for (int i = 0; i < 12; i++) {
                        moduleManager.modules.get(i).bind = Integer.parseInt(config[i]);
                    }
                    for (int i = 12; i < 22; i++) {
                        moduleManager.modules.get(i - 12).setEnabled(Integer.parseInt(config[i]) == 1);
                    }
                    moduleManager.autoClicker.reqItem = Integer.parseInt(config[22]) == 1;
                    moduleManager.aimbot.reqItem = Integer.parseInt(config[23]) == 1;
                    moduleManager.reach.reqItem = Integer.parseInt(config[24]) == 1;
                    moduleManager.hitBoxes.reqItem = Integer.parseInt(config[25]) == 1;
                    moduleManager.wTap.reqItem = Integer.parseInt(config[26]) == 1;
                    moduleManager.autoClicker.itemWhitelist = Config.stringToIntArr(config[27]);
                    moduleManager.aimbot.itemWhitelist = Config.stringToIntArr(config[28]);
                    moduleManager.reach.itemWhitelist = Config.stringToIntArr(config[29]);
                    moduleManager.hitBoxes.itemWhitelist = Config.stringToIntArr(config[30]);
                    moduleManager.wTap.itemWhitelist = Config.stringToIntArr(config[31]);
                    moduleManager.autoClicker.minCPS = Float.parseFloat(config[32]);
                    moduleManager.autoClicker.maxCPS = Float.parseFloat(config[33]);
                    moduleManager.aimbot.dist = Float.parseFloat(config[34]);
                    moduleManager.aimbot.FOV = Integer.parseInt(config[35]);
                    moduleManager.aimbot.reqMouse = Integer.parseInt(config[36]) == 1;
                    moduleManager.aimbot.targetSelection = Integer.parseInt(config[37]);
                    moduleManager.aimbot.minYawSmooth = Float.parseFloat(config[38]);
                    moduleManager.aimbot.maxYawSmooth = Float.parseFloat(config[39]);
                    moduleManager.aimbot.minPitchSmooth = Float.parseFloat(config[40]);
                    moduleManager.aimbot.maxPitchSmooth = Float.parseFloat(config[41]);
                    moduleManager.aimbot.minRand = Integer.parseInt(config[42]);
                    moduleManager.aimbot.maxRand = Integer.parseInt(config[43]);
                    moduleManager.reach.minExpansion = Double.parseDouble(config[44]);
                    moduleManager.reach.maxExpansion = Double.parseDouble(config[45]);
                    moduleManager.hitBoxes.expansion = Float.parseFloat(config[46]);
                    moduleManager.fakeLag.dist = Float.parseFloat(config[47]);
                    moduleManager.fakeLag.FOV = Integer.parseInt(config[48]);
                    moduleManager.fakeLag.minDelay = Integer.parseInt(config[49]);
                    moduleManager.fakeLag.maxDelay = Integer.parseInt(config[50]);
                    moduleManager.wTap.dist = Float.parseFloat(config[51]);
                    moduleManager.wTap.FOV = Integer.parseInt(config[52]);
                    moduleManager.wTap.minTapDelay = Integer.parseInt(config[53]);
                    moduleManager.wTap.maxTapDelay = Integer.parseInt(config[54]);
                    moduleManager.throwPot.minThrowDelay = Integer.parseInt(config[55]);
                    moduleManager.throwPot.maxThrowDelay = Integer.parseInt(config[56]);
                    moduleManager.refill.minFillDelay = Integer.parseInt(config[57]);
                    moduleManager.refill.maxFillDelay = Integer.parseInt(config[58]);
                    moduleManager.refill.minExitDelay = Integer.parseInt(config[59]);
                    moduleManager.refill.maxExitDelay = Integer.parseInt(config[60]);
                    refreshRate = Integer.parseInt(config[61]) * 1000;
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
