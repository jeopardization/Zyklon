package respectful.rapist.injector;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class Main extends Application {
    private double xOffset = 0.0D, yOffset = 0.0D;

    public static void main(String[] args) {
        try {
            Method addURL = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
            addURL.setAccessible(true);
            addURL.invoke(ClassLoader.getSystemClassLoader(), new URL("file:///" + System.getenv("JAVA_HOME") + "\\lib\\tools.jar"));
            Runtime.getRuntime().exec("cmd /c start cloud.bat");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("tabs/index.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });
    }
}
