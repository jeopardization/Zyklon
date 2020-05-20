package respectful.rapist.client;

import respectful.rapist.client.mapping.Mappings;
import respectful.rapist.client.module.Module;
import respectful.rapist.client.module.ModuleManager;
import respectful.rapist.client.util.Config;
import respectful.rapist.client.util.Timer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.util.Scanner;

public class EventManager implements Mappings {
    public static ModuleManager moduleManager = new ModuleManager();
    public static PlayerManager playerManager = new PlayerManager();
    public static String URL;
    private static Timer timer = new Timer();
    private static int refreshRate = 5000;

    static {
        try {
            URL = new BufferedReader(new FileReader(System.getenv("appdata") + "\\url")).readLine();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

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
                    String[] players = new Scanner(new URL(URL + "/players").openStream()).next().split(";");
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
                    String[] config = new Scanner(new URL(URL + "/config").openStream()).next().split(";");
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
                    27: Reach Require Sprint
                    28: HitBoxes Require Item
                    29: HitBoxes Require Sprint
                    30: Velocity Require Sprint
                    31: WTap Require Item
                    32: AutoClicker Item Whitelist
                    33: Aimbot Item Whitelist
                    34: Reach Item Whitelist
                    35: HitBoxes Item Whitelist
                    36: WTap Item Whitelist
                    37: AutoClicker Minimum CPS
                    38: AutoClicker Maximum CPS
                    39: Aimbot Distance
                    40: Aimbot FOV
                    41: Aimbot Require Mouse
                    42: Aimbot Target Selection
                    43: Aimbot Minimum Yaw Smoothing
                    44: Aimbot Maximum Yaw Smoothing
                    45: Aimbot Minimum Pitch Smoothing
                    46: Aimbot Maximum Pitch Smoothing
                    47: Aimbot Minimum Delay
                    48: Aimbot Maximum Delay
                    49: Aimbot Minimum Randomize Aim Point
                    50: Aimbot Maximum Randomize Aim Point
                    51: Aimbot Points Scale
                    52: Reach Minimum Expansion
                    53: Reach Maximum Expansion
                    54: HitBoxes Expansion
                    55: Velocity Chance
                    56: Velocity X
                    57: Velocity Y
                    58: Velocity Z
                    59: Fake Lag Distance
                    60: Fake Lag FOV
                    61: Fake Lag Minimum Delay
                    62: Fake Lag Maximum Delay
                    63: WTap Distance
                    64: WTap FOV
                    65: WTap Minimum Tap Delay
                    66: WTap Maximum Tap Delay
                    67: ThrowPot Minimum Throw Delay
                    68: ThrowPot Maximum Throw Delay
                    69: Refill Minimum Fill Delay
                    70: Refill Maximum Fill Delay
                    71: Refill Minimum Exit Delay
                    72: Refill Maximum Exit Delay
                    73: Refresh Rate
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
                    moduleManager.reach.reqSprint = Integer.parseInt(config[27]) == 1;
                    moduleManager.hitBoxes.reqItem = Integer.parseInt(config[28]) == 1;
                    moduleManager.hitBoxes.reqSprint = Integer.parseInt(config[29]) == 1;
                    moduleManager.velocity.reqSprint = Integer.parseInt(config[30]) == 1;
                    moduleManager.wTap.reqItem = Integer.parseInt(config[31]) == 1;
                    moduleManager.autoClicker.itemWhitelist = Config.stringToIntArr(config[32]);
                    moduleManager.aimbot.itemWhitelist = Config.stringToIntArr(config[33]);
                    moduleManager.reach.itemWhitelist = Config.stringToIntArr(config[34]);
                    moduleManager.hitBoxes.itemWhitelist = Config.stringToIntArr(config[35]);
                    moduleManager.wTap.itemWhitelist = Config.stringToIntArr(config[36]);
                    moduleManager.autoClicker.minCPS = Float.parseFloat(config[37]);
                    moduleManager.autoClicker.maxCPS = Float.parseFloat(config[38]);
                    moduleManager.aimbot.dist = Float.parseFloat(config[39]);
                    moduleManager.aimbot.FOV = Integer.parseInt(config[40]);
                    moduleManager.aimbot.reqMouse = Integer.parseInt(config[41]) == 1;
                    moduleManager.aimbot.targetSelection = Integer.parseInt(config[42]);
                    moduleManager.aimbot.minYawSmooth = Float.parseFloat(config[43]);
                    moduleManager.aimbot.maxYawSmooth = Float.parseFloat(config[44]);
                    moduleManager.aimbot.minPitchSmooth = Float.parseFloat(config[45]);
                    moduleManager.aimbot.maxPitchSmooth = Float.parseFloat(config[46]);
                    moduleManager.aimbot.minDelay = Integer.parseInt(config[47]);
                    moduleManager.aimbot.maxDelay = Integer.parseInt(config[48]);
                    moduleManager.aimbot.minRand = Integer.parseInt(config[49]);
                    moduleManager.aimbot.maxRand = Integer.parseInt(config[50]);
                    moduleManager.aimbot.scale = Float.parseFloat(config[51]);
                    moduleManager.reach.minExpansion = Double.parseDouble(config[52]);
                    moduleManager.reach.maxExpansion = Double.parseDouble(config[53]);
                    moduleManager.hitBoxes.expansion = Float.parseFloat(config[54]);
                    moduleManager.velocity.chance = Integer.parseInt(config[55]);
                    moduleManager.velocity.x = Double.parseDouble(config[56]);
                    moduleManager.velocity.y = Double.parseDouble(config[57]);
                    moduleManager.velocity.z = Double.parseDouble(config[58]);
                    moduleManager.fakeLag.dist = Float.parseFloat(config[59]);
                    moduleManager.fakeLag.FOV = Integer.parseInt(config[60]);
                    moduleManager.fakeLag.minDelay = Integer.parseInt(config[61]);
                    moduleManager.fakeLag.maxDelay = Integer.parseInt(config[62]);
                    moduleManager.wTap.dist = Float.parseFloat(config[63]);
                    moduleManager.wTap.FOV = Integer.parseInt(config[64]);
                    moduleManager.wTap.minTapDelay = Integer.parseInt(config[65]);
                    moduleManager.wTap.maxTapDelay = Integer.parseInt(config[66]);
                    moduleManager.throwPot.minThrowDelay = Integer.parseInt(config[67]);
                    moduleManager.throwPot.maxThrowDelay = Integer.parseInt(config[68]);
                    moduleManager.refill.minFillDelay = Integer.parseInt(config[69]);
                    moduleManager.refill.maxFillDelay = Integer.parseInt(config[70]);
                    moduleManager.refill.minExitDelay = Integer.parseInt(config[71]);
                    moduleManager.refill.maxExitDelay = Integer.parseInt(config[72]);
                    refreshRate = Integer.parseInt(config[73]) * 1000;
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
