package Controllers.oeuvreArtistique;

import Controllers.Login.Session;
import Entités.Category;
import Entités.OeuvreArtistique;
import Services.CategoryService;
import Services.ServiceOeuvreArtistique;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.List;

public class AjouterOeuvreArtistiqueController {

    @FXML
    private ImageView imagePreview;

    @FXML
    private Button AjouterOeuvre;

    @FXML
    private TextField titre;

    @FXML
    private TextField prix;

    @FXML
    private TextArea description;

    @FXML
    private Label titreErrorLabel;

    @FXML
    private Label descriptionErrorLabel;

    @FXML
    private Label prixErrorLabel;

    @FXML
    private Label imageErrorLabel;

    private byte[] imageBytes;
    @FXML
    private ComboBox<String> categorieComboBox;

    CategoryService categoryService = new CategoryService();
    @FXML
    private void initialize() {
        List<Category> categories;
        try {
            categories = categoryService.getAllCategories();
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        for (Category category : categories) {
            categorieComboBox.getItems().add(category.getName());
        }
        description.textProperty().addListener((observable, oldValue, newValue) -> {
            descriptionErrorLabel.setVisible(newValue.isEmpty());
        });
        titre.textProperty().addListener((observable, oldValue, newValue) -> {
            titreErrorLabel.setVisible(newValue.isEmpty());
        });
        prix.textProperty().addListener((observable, oldValue, newValue) -> {
            prixErrorLabel.setVisible(newValue.isEmpty());
        });
        imagePreview.imageProperty().addListener((observable, oldValue, newValue) -> {
            imageErrorLabel.setVisible(newValue == null);
        });
    }

    @FXML
    private void handleImporterImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            try {
                Path imagePath = selectedFile.toPath();
                imageBytes = Files.readAllBytes(imagePath);
                Image image = new Image(new FileInputStream(selectedFile));
                imagePreview.setImage(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleAjouterOeuvre(ActionEvent event) {
        String titreText = titre.getText();
        String prixText = prix.getText();
        String descriptionText = description.getText();
        if (champsObligatoiresRemplis()) {
            int selectedIndex = categorieComboBox.getSelectionModel().getSelectedIndex();
            ServiceOeuvreArtistique service = new ServiceOeuvreArtistique();
            List<Category> categories;
            try {
                categories = categoryService.getAllCategories();
            } catch (SQLException e) {
                e.printStackTrace();
                return;
            }
            int selectedCategoryId = categories.get(selectedIndex).getId_category();
            Session session = Session.getInstance();
            String userEmail = session.getUserEmail();
            int userRole = session.getUserRole();
            int useriD = session.getUserId();
            OeuvreArtistique oeuvre = new OeuvreArtistique(titreText, descriptionText, Float.parseFloat(prixText), 0, useriD, 0, 0, 0, imageBytes);
            oeuvre.setIdCategorie(selectedCategoryId);
            try {
                service.ajouterOeuvre(oeuvre);
                afficherNotification("Succès", "Oeuvre Artistique ajoutée avec succès !");
                redirectToPage("/OeuvreArtistique/Espace Artiste/ListeOeuvresArtistiques.fxml");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void afficherNotification(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void redirectToPage(String cheminFXML) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(cheminFXML));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) titre.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private boolean champsObligatoiresRemplis() {
        return !titre.getText().isEmpty() && !prix.getText().isEmpty() && !description.getText().isEmpty() && imageBytes != null && categorieComboBox != null;
    }

    @FXML
    private void retournerALaPagePrecedente() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/OeuvreArtistique/Espace Artiste/ListeOeuvresArtistiques.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) titre.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}