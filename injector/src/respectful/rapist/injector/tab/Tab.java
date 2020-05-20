package respectful.rapist.injector.tab;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public abstract class Tab {
    private static double x, y;
    public Stage stage;
    public Parent root;

    public Tab(String name, Stage stage) {
        try {
            this.stage = stage;
            root = FXMLLoader.load(Tab.class.getResource("tabs/" + name + ".fxml"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        this.stage.setScene(new Scene(root));
        this.stage.setResizable(false);
        this.stage.initStyle(StageStyle.UNDECORATED);
        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {
            this.stage.setX(event.getScreenX() - x);
            this.stage.setY(event.getScreenY() - y);
        });
    }

    public Node getNode(String ID) {
        return root.lookup(ID);
    }

    public void show() {
        stage.show();
    }

    public void hide() {
        stage.hide();
    }
}
