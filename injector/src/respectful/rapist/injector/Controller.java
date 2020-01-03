package respectful.rapist.injector;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.jar.JarInputStream;
import java.util.jar.Manifest;

import static net.bytebuddy.agent.ByteBuddyAgent.attach;

public class Controller {
    @FXML
    public Label info;

    public void initialize() {
        try {
            Manifest manifest = new JarInputStream(new URL("http://localhost:1337/client.jar").openStream()).getManifest();
            info.setText("Build Date: " + manifest.getMainAttributes().getValue("Build-Date") + "\n" + "Build Version: " + manifest.getMainAttributes().getValue("Build-Version"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void inject() {
        Alert alert;
        try {
            System.setProperty("java.library.path", System.getenv("JAVA_HOME") + "\\jre\\bin");
            Field sysPath = ClassLoader.class.getDeclaredField("sys_paths");
            sysPath.setAccessible(true);
            sysPath.set(null, null);
            for (VirtualMachineDescriptor VM : VirtualMachine.list()) {
                if (VM.displayName().contains("net.minecraft.launchwrapper.Launch")) {
                    attach(new File("loader.jar"), VM.id());
                    alert = new Alert(Alert.AlertType.CONFIRMATION, "Success", ButtonType.OK);
                    alert.showAndWait();
                    exit();
                }
            }
            alert = new Alert(Alert.AlertType.WARNING, "Make sure you have Minecraft Forge 1.7.10 running", ButtonType.OK);
            alert.showAndWait();
        } catch (Exception ex) {
            alert = new Alert(Alert.AlertType.ERROR, ex.toString(), ButtonType.OK);
            alert.showAndWait();
        }
    }

    public void exit() {
        System.exit(0);
    }
}
