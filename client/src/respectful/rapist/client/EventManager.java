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
                    6: Velocity Bind
                    7: Fake Lag Bind
                    8: WTap Bind
                    9: Self Destruct Bind
                    10: HUD Bind
                    11: ThrowPot Bind
                    12: Refill Bind
                    13: AutoClicker State
                    14: Aimbot State
                    15: NameTags State
                    16: Brightness State
                    17: Reach State
                    18: HitBoxes State
                    19: Velocity State
                    20: Fake Lag State
                    21: WTap State
                    22: Self Destruct State
                    23: HUD State
                    24: AutoClicker Require Item
                    25: Aimbot Require Item
                    26: Reach Require Item
                    27: HitBoxes Require Item
                    28: WTap Require Item
                    29: AutoClicker Item Whitelist
                    30: Aimbot Item Whitelist
                    31: Reach Item Whitelist
                    32: HitBoxes Item Whitelist
                    33: WTap Item Whitelist
                    34: AutoClicker Minimum CPS
                    35: AutoClicker Maximum CPS
                    36: Aimbot Distance
                    37: Aimbot FOV
                    38: Aimbot Require Mouse
                    39: Aimbot Target Selection
                    40: Aimbot Minimum Yaw Smoothing
                    41: Aimbot Maximum Yaw Smoothing
                    42: Aimbot Minimum Pitch Smoothing
                    43: Aimbot Maximum Pitch Smoothing
                    44: Aimbot Minimum Randomize Frequency
                    45: Aimbot Maximum Randomize Frequency
                    46: Reach Minimum Expansion
                    47: Reach Maximum Expansion
                    48: HitBoxes Expansion
                    49: Velocity Chance
                    50: Velocity X
                    51: Velocity Y
                    52: Velocity Z
                    53: Fake Lag Distance
                    54: Fake Lag FOV
                    55: Fake Lag Minimum Delay
                    56: Fake Lag Maximum Delay
                    57: WTap Distance
                    58: WTap FOV
                    59: WTap Minimum Tap Delay
                    60: WTap Maximum Tap Delay
                    61: ThrowPot Minimum Throw Delay
                    62: ThrowPot Maximum Throw Delay
                    63: Refill Minimum Fill Delay
                    64: Refill Maximum Fill Delay
                    65: Refill Minimum Exit Delay
                    66: Refill Maximum Exit Delay
                    67: Refresh Rate
                    */
                    for (int i = 0; i < 13; i++) {
                        moduleManager.modules.get(i).bind = Integer.parseInt(config[i]);
                    }
                    for (int i = 13; i < 24; i++) {
                        moduleManager.modules.get(i - 13).setEnabled(Integer.parseInt(config[i]) == 1);
                    }
                    moduleManager.autoClicker.reqItem = Integer.parseInt(config[24]) == 1;
                    moduleManager.aimbot.reqItem = Integer.parseInt(config[25]) == 1;
                    moduleManager.reach.reqItem = Integer.parseInt(config[26]) == 1;
                    moduleManager.hitBoxes.reqItem = Integer.parseInt(config[27]) == 1;
                    moduleManager.wTap.reqItem = Integer.parseInt(config[28]) == 1;
                    moduleManager.autoClicker.itemWhitelist = Config.stringToIntArr(config[29]);
                    moduleManager.aimbot.itemWhitelist = Config.stringToIntArr(config[30]);
                    moduleManager.reach.itemWhitelist = Config.stringToIntArr(config[31]);
                    moduleManager.hitBoxes.itemWhitelist = Config.stringToIntArr(config[32]);
                    moduleManager.wTap.itemWhitelist = Config.stringToIntArr(config[33]);
                    moduleManager.autoClicker.minCPS = Float.parseFloat(config[34]);
                    moduleManager.autoClicker.maxCPS = Float.parseFloat(config[35]);
                    moduleManager.aimbot.dist = Float.parseFloat(config[36]);
                    moduleManager.aimbot.FOV = Integer.parseInt(config[37]);
                    moduleManager.aimbot.reqMouse = Integer.parseInt(config[38]) == 1;
                    moduleManager.aimbot.targetSelection = Integer.parseInt(config[39]);
                    moduleManager.aimbot.minYawSmooth = Float.parseFloat(config[40]);
                    moduleManager.aimbot.maxYawSmooth = Float.parseFloat(config[41]);
                    moduleManager.aimbot.minPitchSmooth = Float.parseFloat(config[42]);
                    moduleManager.aimbot.maxPitchSmooth = Float.parseFloat(config[43]);
                    moduleManager.aimbot.minRand = Integer.parseInt(config[44]);
                    moduleManager.aimbot.maxRand = Integer.parseInt(config[45]);
                    moduleManager.reach.minExpansion = Double.parseDouble(config[46]);
                    moduleManager.reach.maxExpansion = Double.parseDouble(config[47]);
                    moduleManager.hitBoxes.expansion = Float.parseFloat(config[48]);
                    moduleManager.velocity.chance = Integer.parseInt(config[49]);
                    moduleManager.velocity.x = Double.parseDouble(config[50]);
                    moduleManager.velocity.y = Double.parseDouble(config[51]);
                    moduleManager.velocity.z = Double.parseDouble(config[52]);
                    moduleManager.fakeLag.dist = Float.parseFloat(config[53]);
                    moduleManager.fakeLag.FOV = Integer.parseInt(config[54]);
                    moduleManager.fakeLag.minDelay = Integer.parseInt(config[55]);
                    moduleManager.fakeLag.maxDelay = Integer.parseInt(config[56]);
                    moduleManager.wTap.dist = Float.parseFloat(config[57]);
                    moduleManager.wTap.FOV = Integer.parseInt(config[58]);
                    moduleManager.wTap.minTapDelay = Integer.parseInt(config[59]);
                    moduleManager.wTap.maxTapDelay = Integer.parseInt(config[60]);
                    moduleManager.throwPot.minThrowDelay = Integer.parseInt(config[61]);
                    moduleManager.throwPot.maxThrowDelay = Integer.parseInt(config[62]);
                    moduleManager.refill.minFillDelay = Integer.parseInt(config[63]);
                    moduleManager.refill.maxFillDelay = Integer.parseInt(config[64]);
                    moduleManager.refill.minExitDelay = Integer.parseInt(config[65]);
                    moduleManager.refill.maxExitDelay = Integer.parseInt(config[66]);
                    refreshRate = Integer.parseInt(config[67]) * 1000;
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
