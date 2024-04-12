package Controllers.General;
import Controllers.Login.DeconnectionController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class GeneralLayouts {
    @FXML
    private Label accueilLabel;

    @FXML
    private Label AProposLabel;

    @FXML
    private Label Oeuv;


    @FXML
    private Label Dec;


    @FXML
    private Label Mus;

    @FXML
    private void retournerALaPagePrecedenteAdmint() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/OeuvreArtistique/General/accueil.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) accueilLabel.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goToMusee() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Musee/Espace Client/ListeMusee.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) Mus.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void goToOeuv() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/OeuvreArtistique/Espace Client/ListeOeuvresArtistiques.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) Oeuv.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void goToDec() {

        // Create an instance of DeconnectionController
        DeconnectionController deconnectionController = new DeconnectionController();

        // Call the deconnect() method from DeconnectionController to perform disconnection logic
        deconnectionController.deconnect((Stage) Dec.getScene().getWindow());

        // Load the propos.fxml file


    }
    @FXML
    private void retournerALaPageAPropos() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/OeuvreArtistique/General/propos.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) AProposLabel.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
