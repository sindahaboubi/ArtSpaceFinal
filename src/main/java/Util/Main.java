
package Util;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/Login/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
}
