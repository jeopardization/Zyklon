package respectful.rapist.injector;

import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import respectful.rapist.injector.tab.tabs.Index;
import respectful.rapist.injector.tab.tabs.Settings;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class Main extends Application {
    static Index index;
    static Settings settings;
    static String seperator = System.getProperty("file.separator"), tools = "file:///" + System.getenv("JAVA_HOME") + seperator + "lib" + seperator + "tools.jar";

    public static void main(String[] args) {
        try {
            Method addURL = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
            addURL.setAccessible(true);
            addURL.invoke(ClassLoader.getSystemClassLoader(), new URL(tools));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Font.loadFont(getClass().getResourceAsStream("tab/resources/fonts/titilliumweb.ttf"), 10);
        index = new Index("index", primaryStage);
        settings = new Settings("settings", new Stage());
        settings.show();
    }
}
