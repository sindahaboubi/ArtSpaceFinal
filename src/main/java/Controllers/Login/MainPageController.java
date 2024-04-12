package Controllers.Login;

import java.net.URL;
import java.util.ResourceBundle;

import Util.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import java.io.IOException;

public class MainPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void onCLose(ActionEvent event) {

    }

    @FXML
    void onLogout(ActionEvent event) {

    }

    //@FXML
    //private Label welcomeName;
    @FXML
    protected void onLogout() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainPageController.class.getResource("/Login/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Main.stage.setScene(scene);
        Main.stage.show();
    }


    @FXML
    public void onCLose() {
        Main.stage.close();
    }


}
