package respectful.rapist.client;

import respectful.rapist.client.mapping.Mappings;
import respectful.rapist.client.module.Module;
import respectful.rapist.client.module.Modules;
import respectful.rapist.client.util.Config;
import respectful.rapist.client.util.Timer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.util.Scanner;

public class Events implements Mappings {
    public static Modules modules = new Modules();
    public static Players players = new Players();
    public static String URL;
    private static Plugins plugins;
    private static Timer timer = new Timer();
    private static int refreshRate;

    static {
        try {
            String code = "";
            URL = new BufferedReader(new FileReader(((System.getProperty("os.name").contains("Windows") ? System.getenv("APPDATA") + "\\" : System.getProperty("user.home") + "/") + "url"))).readLine().split(";")[0];
            Scanner scanner = new Scanner(new URL(URL + "/plugins").openStream());
            while (scanner.hasNextLine()) {
                code += scanner.nextLine();
            }
            plugins = new Plugins(code);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void onKey(int keyCode) {
        for (Module module : modules.modules) {
            if (keyCode == module.bind) {
                module.toggle();
            }
        }
        plugins.onKey(keyCode);
    }

    public static void onRenderGUI() {
        for (Module module : modules.modules) {
            if (module.enabled) {
                module.onRenderGUI();
            }
        }
        plugins.onRenderGUI();
    }

    public static void onRender() {
        for (Module module : modules.modules) {
            if (module.enabled) {
                module.onRender();
            }
        }
        plugins.onRender();
    }

    public static void onTick() {
        try {
            if (Minecraft.getTheWorld() != null) {
                if (timer.elapsed(refreshRate)) {
                    String[] players = new Scanner(new URL(URL + "/players").openStream()).next().split(";");
                    /*
                    0: Friends
                    1: Enemies
                     */
                    if ((players.length >= 1)) {
                        Events.players.friends = players[0].split(",");
                    } else {
                        Events.players.friends = null;
                    }
                    if ((players.length >= 2)) {
                        Events.players.enemies = players[1].split(",");
                    } else {
                        Events.players.enemies = null;
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
                    24: Plugins State
                    25: AutoClicker Require Item
                    26: Aimbot Require Item
                    27: Reach Require Item
                    28: Reach Require Sprint
                    29: HitBoxes Require Item
                    30: HitBoxes Require Sprint
                    31: Velocity Require Sprint
                    32: WTap Require Item
                    33: AutoClicker Item Whitelist
                    34: Aimbot Item Whitelist
                    35: Reach Item Whitelist
                    36: HitBoxes Item Whitelist
                    37: WTap Item Whitelist
                    38: AutoClicker Minimum CPS
                    39: AutoClicker Maximum CPS
                    40: Aimbot Distance
                    41: Aimbot FOV
                    42: Aimbot Require Mouse
                    43: Aimbot Target Selection
                    44: Aimbot Minimum Yaw Smoothing
                    45: Aimbot Maximum Yaw Smoothing
                    46: Aimbot Minimum Pitch Smoothing
                    47: Aimbot Maximum Pitch Smoothing
                    48: Aimbot Minimum Delay
                    49: Aimbot Maximum Delay
                    50: Aimbot Minimum Randomize Aim Point
                    51: Aimbot Maximum Randomize Aim Point
                    52: Aimbot Points Scale
                    53: Reach Minimum Expansion
                    54: Reach Maximum Expansion
                    55: HitBoxes Expansion
                    56: Velocity Chance
                    57: Velocity X
                    58: Velocity Y
                    59: Velocity Z
                    60: Fake Lag Distance
                    61: Fake Lag FOV
                    62: Fake Lag Minimum Delay
                    63: Fake Lag Maximum Delay
                    64: WTap Distance
                    65: WTap FOV
                    66: WTap Minimum Tap Delay
                    67: WTap Maximum Tap Delay
                    68: ThrowPot Minimum Throw Delay
                    69: ThrowPot Maximum Throw Delay
                    70: Refill Minimum Fill Delay
                    71: Refill Maximum Fill Delay
                    72: Refill Minimum Exit Delay
                    73: Refill Maximum Exit Delay
                    74: Refresh Rate
                    */
                    for (int i = 0; i < 13; i++) {
                        modules.modules.get(i).bind = Integer.parseInt(config[i]);
                    }
                    for (int i = 13; i < 24; i++) {
                        modules.modules.get(i - 13).setEnabled(Integer.parseInt(config[i]) == 1);
                    }
                    plugins.enabled = Integer.parseInt(config[24]) == 1;
                    modules.autoClicker.reqItem = Integer.parseInt(config[25]) == 1;
                    modules.aimbot.reqItem = Integer.parseInt(config[26]) == 1;
                    modules.reach.reqItem = Integer.parseInt(config[27]) == 1;
                    modules.reach.reqSprint = Integer.parseInt(config[28]) == 1;
                    modules.hitBoxes.reqItem = Integer.parseInt(config[29]) == 1;
                    modules.hitBoxes.reqSprint = Integer.parseInt(config[30]) == 1;
                    modules.velocity.reqSprint = Integer.parseInt(config[31]) == 1;
                    modules.wTap.reqItem = Integer.parseInt(config[32]) == 1;
                    modules.autoClicker.itemWhitelist = Config.stringToIntArr(config[33]);
                    modules.aimbot.itemWhitelist = Config.stringToIntArr(config[34]);
                    modules.reach.itemWhitelist = Config.stringToIntArr(config[35]);
                    modules.hitBoxes.itemWhitelist = Config.stringToIntArr(config[36]);
                    modules.wTap.itemWhitelist = Config.stringToIntArr(config[37]);
                    modules.autoClicker.minCPS = Float.parseFloat(config[38]);
                    modules.autoClicker.maxCPS = Float.parseFloat(config[39]);
                    modules.aimbot.dist = Float.parseFloat(config[40]);
                    modules.aimbot.FOV = Integer.parseInt(config[41]);
                    modules.aimbot.reqMouse = Integer.parseInt(config[42]) == 1;
                    modules.aimbot.targetSelection = Integer.parseInt(config[43]);
                    modules.aimbot.minYawSmooth = Float.parseFloat(config[44]);
                    modules.aimbot.maxYawSmooth = Float.parseFloat(config[45]);
                    modules.aimbot.minPitchSmooth = Float.parseFloat(config[46]);
                    modules.aimbot.maxPitchSmooth = Float.parseFloat(config[47]);
                    modules.aimbot.minDelay = Integer.parseInt(config[48]);
                    modules.aimbot.maxDelay = Integer.parseInt(config[49]);
                    modules.aimbot.minRand = Integer.parseInt(config[50]);
                    modules.aimbot.maxRand = Integer.parseInt(config[51]);
                    modules.aimbot.scale = Float.parseFloat(config[52]);
                    modules.reach.minExpansion = Double.parseDouble(config[53]);
                    modules.reach.maxExpansion = Double.parseDouble(config[54]);
                    modules.hitBoxes.expansion = Float.parseFloat(config[55]);
                    modules.velocity.chance = Integer.parseInt(config[56]);
                    modules.velocity.x = Double.parseDouble(config[57]);
                    modules.velocity.y = Double.parseDouble(config[58]);
                    modules.velocity.z = Double.parseDouble(config[59]);
                    modules.fakeLag.dist = Float.parseFloat(config[60]);
                    modules.fakeLag.FOV = Integer.parseInt(config[61]);
                    modules.fakeLag.minDelay = Integer.parseInt(config[62]);
                    modules.fakeLag.maxDelay = Integer.parseInt(config[63]);
                    modules.wTap.dist = Float.parseFloat(config[64]);
                    modules.wTap.FOV = Integer.parseInt(config[65]);
                    modules.wTap.minTapDelay = Integer.parseInt(config[66]);
                    modules.wTap.maxTapDelay = Integer.parseInt(config[67]);
                    modules.throwPot.minThrowDelay = Integer.parseInt(config[68]);
                    modules.throwPot.maxThrowDelay = Integer.parseInt(config[69]);
                    modules.refill.minFillDelay = Integer.parseInt(config[70]);
                    modules.refill.maxFillDelay = Integer.parseInt(config[71]);
                    modules.refill.minExitDelay = Integer.parseInt(config[72]);
                    modules.refill.maxExitDelay = Integer.parseInt(config[73]);
                    refreshRate = Integer.parseInt(config[74]) * 1000;
                    timer = new Timer();
                }
                for (Module module : modules.modules) {
                    if (module.enabled) {
                        module.onTick();
                    }
                }
                plugins.onTick();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
