package Controllers.Login;

import java.net.URL;
import java.util.ResourceBundle;

import Util.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class ForgetPassController {
    @FXML
    protected void onBack() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/Login/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Main.stage.setScene(scene);
        Main.stage.show();
    }
    @FXML
    protected void onConfirm() throws IOException {
        alert.confirm();
    }
}