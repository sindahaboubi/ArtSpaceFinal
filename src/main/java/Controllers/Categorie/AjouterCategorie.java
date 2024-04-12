package Controllers.Categorie;

import java.net.URL;
import java.util.ResourceBundle;

import Entités.Category;
import Services.CategoryService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.stage.Stage;


import java.io.IOException;

public class AjouterCategorie {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button AjouterCategorie;

    @FXML
    private DatePicker DateCreation;

    @FXML
    private Label DateCreationError;

    @FXML
    private TextArea DescriptionCategorie;

    @FXML
    private Label DescriptionCategorieError;

    @FXML
    private TextField NomCategorie;

    @FXML
    private Label NomCategorieError;

    @FXML
    void initialize() {
        assert AjouterCategorie != null : "fx:id=\"AjouterCategorie\" was not injected: check your FXML file 'AjouterCategorie.fxml'.";
        assert DateCreation != null : "fx:id=\"DateCreation\" was not injected: check your FXML file 'AjouterCategorie.fxml'.";
        assert DateCreationError != null : "fx:id=\"DateCreationError\" was not injected: check your FXML file 'AjouterCategorie.fxml'.";
        assert DescriptionCategorie != null : "fx:id=\"DescriptionCategorie\" was not injected: check your FXML file 'AjouterCategorie.fxml'.";
        assert DescriptionCategorieError != null : "fx:id=\"DescriptionCategorieError\" was not injected: check your FXML file 'AjouterCategorie.fxml'.";
        assert NomCategorie != null : "fx:id=\"NomCategorie\" was not injected: check your FXML file 'AjouterCategorie.fxml'.";
        assert NomCategorieError != null : "fx:id=\"NomCategorieError\" was not injected: check your FXML file 'AjouterCategorie.fxml'.";

    }
    @FXML
    private void handleAjouterCategorie(ActionEvent event) {
        try {
            // Validate input fields
            if (NomCategorie.getText().isEmpty() || DateCreation == null  || DescriptionCategorie.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Error", "Veuillez remplir tous les champs.");
                return;
            }



            // Create a Musee object with the data from the UI elements
            Category category = new Category();
            category.setName(NomCategorie.getText());
            category.setDescription(DescriptionCategorie.getText());

            java.sql.Date dc = java.sql.Date.valueOf(DateCreation.getValue());


            category.setCreated_Date(dc);

            //category.setIdadmin(1);  // Change as needed


            // Add Musee to service
            CategoryService categoryService = new CategoryService();
            categoryService.addCategory(category);

            // Show success alert
            showAlert(Alert.AlertType.INFORMATION, "Success", "Categorie ajouté avec succès.");


            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Categorie/ListeCategorie.fxml"));
            Parent root = loader.load();

            // Create a new Scene
            Scene scene = new Scene(root);

            // Get the Stage from the current event
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new Scene
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            // Show failure alert
            //showAlert(Alert.AlertType.ERROR, "Error", "Erreur lors de l'ajout du categorie.");
        }

    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    @FXML
    private void retournerALaPagePrecedente(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Categorie/ListeCategorie.fxml"));
            Parent root = loader.load();

            // Create a new Scene
            Scene scene = new Scene(root);

            // Get the Stage from the current event
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new Scene
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void handleConsulterCategorie(ActionEvent event) {
        try {
            // Load the FXML file for the new scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Categorie/ListeCategorie.fxml"));
            Parent root = loader.load();

            // Create a new Scene
            Scene scene = new Scene(root);

            // Get the Stage from the current event
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new Scene
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
