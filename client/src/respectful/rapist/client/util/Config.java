package respectful.rapist.client.util;

import org.lwjgl.input.Mouse;
import respectful.rapist.client.mapping.Mappings;
import respectful.rapist.client.module.Module;

import java.io.InputStream;
import java.net.URL;

public class Config implements Mappings {
    public static int[] stringToIntArr(String str) {
        String[] arr = str.split(",");
        int[] intArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            intArr[i] = Integer.parseInt(arr[i]);
        }
        return intArr;
    }

    public static boolean safe(boolean reqItem, int[] itemWhitelist, boolean reqMouse) {
        boolean itemCheck = true;
        boolean mouseCheck = true;
        if (reqItem) {
            if (InventoryPlayer.getCurrentItem(EntityPlayer.getInventory(Minecraft.getThePlayer())) == null) {
                itemCheck = false;
            } else {
                for (int ID : itemWhitelist) {
                    if (ID == Item.getIdFromItem(ItemStack.getItem(InventoryPlayer.getCurrentItem(EntityPlayer.getInventory(Minecraft.getThePlayer()))))) {
                        itemCheck = true;
                        break;
                    } else {
                        itemCheck = false;
                    }
                }
            }
        }
        if (reqMouse) {
            mouseCheck = Mouse.isButtonDown(0);
        }
        return itemCheck && mouseCheck && Minecraft.getCurrentScreen() == null;
    }

    public static void setEnabledCloud(Module module, boolean enabled) {
        try {
            URL URL = new URL("http://localhost:1337/setenabled/" + module.name + "/" + (enabled ? 1 : 0));
            InputStream inputStream = URL.openStream();
            inputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
