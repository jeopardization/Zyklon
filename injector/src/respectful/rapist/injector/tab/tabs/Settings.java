package respectful.rapist.injector.tab.tabs;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import respectful.rapist.injector.tab.Tab;

public class Settings extends Tab {
    public Settings(String name, Stage stage) {
        super(name, stage);
    }

    public int getPort() {
        String input = ((TextField) getNode("#port")).getText().trim();
        if (!input.isEmpty()) {
            int port = Integer.parseInt(input);
            if (port >= 0 && port <= 65535) {
                return port;
            }
        }
        return -1;
    }

    public String getHost() {
        return ((TextField) getNode("#host")).getText().trim();
    }
}
