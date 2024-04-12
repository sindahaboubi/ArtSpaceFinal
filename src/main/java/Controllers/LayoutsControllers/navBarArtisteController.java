package Controllers.LayoutsControllers;

import Controllers.Login.DeconnectionController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class navBarArtisteController {
    @FXML
    private Label Oeuv;


    @FXML
    private Label Dec;


    @FXML
    private Label Mus;


    @FXML
    private Label Cat;



    @FXML
    private void goToMusee() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Musee/Espace Artiste/ListeMusee.fxml"));
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/OeuvreArtistique/Espace Artiste/ListeOeuvresArtistiques.fxml"));
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
    private void goToCat() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Categorie/ListeCategorie.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) Cat.getScene().getWindow();
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
}