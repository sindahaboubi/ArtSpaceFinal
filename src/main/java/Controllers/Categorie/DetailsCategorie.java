package Controllers.Categorie;

import Entités.Category;
import Services.CategoryService;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DetailsCategorie {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker DateCreation;

    @FXML
    private TextArea DescriptionCategorie;

    @FXML
    private Button ModifierCategorie;

    @FXML
    private TextField NomCategorie;

    @FXML
    private Button SupprimerCategorie;




    public void populateFields(Category category) {
        NomCategorie.setText(category.getName());
        DescriptionCategorie.setText(category.getDescription());

        java.sql.Date dc = java.sql.Date.valueOf(DateCreation.getValue());


        category.setCreated_Date(dc); // Using getValue() for DatePicker

        String Id_category = String.valueOf(category.getId_category());
    }



    @FXML
    void handleModifierCategorie(ActionEvent event) throws SQLException {
        if (NomCategorie.getText().isEmpty() || DescriptionCategorie.getText().isEmpty() || DateCreation.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Veuillez remplir tous les champs.");
            return;
        }



        // Retrieve the existing Musee object to get the accept value
        CategoryService categoryService = new CategoryService();
        Category categories = categoryService.getCateById(ListeCategorie.idCat);
        if (categories != null) {



            java.sql.Date dc = java.sql.Date.valueOf(DateCreation.getValue());
            Category updatedCategory = new Category(
                    categories.getId_category(),
                    NomCategorie.getText(),
                    DescriptionCategorie.getText(),dc);






            // Use the MuseeService to update the museum
            boolean updated = categoryService.updateCategory(updatedCategory);

            // Display success or fail message based on the update result
            if (updated) {
                showAlert(Alert.AlertType.INFORMATION, "Succès", "Musée mis à jour avec succès.");
                try {
                    // Load the FXML file for the list of museums
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
            } else {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Échec de la mise à jour du categorie.");
                try {
                    // Load the FXML file for the list of museums
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
        } else {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Impossible de trouver le musée pour mise à jour.");
        }
    }
    @FXML
    void handleSupprimerCategorie(ActionEvent event) {
        // You may need to confirm the deletion with the user before proceeding
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setHeaderText("Supprimer la categorie");
        confirmationAlert.setContentText("Êtes-vous sûr de vouloir supprimer cette categorie?");
        confirmationAlert.showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> {

                    CategoryService categoryService = new CategoryService();

                    boolean deleted = categoryService.deleteCategory(ListeCategorie.idCat);

                    // Display success or fail message based on the deletion result
                    if (deleted) {
                        showAlert(Alert.AlertType.INFORMATION, "Succès", "Categorie supprimé avec succès.");
                        try {
                            // Load the FXML file for the list of museums
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
                    } else {
                        showAlert(Alert.AlertType.ERROR, "Erreur", "Échec de la suppression du categorie.");
                    }
                });
    }

//    @FXML
//    void handleSupprimerCategorie(ActionEvent event) {
//        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
//        confirmationAlert.setTitle("Confirmation");
//        confirmationAlert.setHeaderText("Supprimer la categorie");
//        confirmationAlert.setContentText("Êtes-vous sûr de vouloir supprimer cette categorie?");
//        confirmationAlert.showAndWait()
//                .filter(response -> response == ButtonType.OK)
//                .ifPresent(response -> {
//                    // Use the MuseeService to delete the museum
//                    CategoryService categoryService = new CategoryService();
//
//                    Category category = new Category();
//                    boolean deleted = false;
//                    try {
//
//                        String Name = null;
//                        categoryService.deleteCategory(category);
//                    } catch (SQLException e) {
//                        throw new RuntimeException(e);
//                    }
//
//                    // Display success or fail message based on the deletion result
//                    if (deleted) {
//                        showAlert(Alert.AlertType.INFORMATION, "Succès", "Categorie supprimée avec succès.");
//                        try {
//                            // Load the FXML file for the list of museums
//                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Categorie/ListeCategorie.fxml"));
//                            Parent root = loader.load();
//
//                            // Create a new Scene
//                            Scene scene = new Scene(root);
//
//                            // Get the Stage from the current event
//                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//
//                            // Set the new Scene
//                            stage.setScene(scene);
//                            stage.show();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    } else {
//                        showAlert(Alert.AlertType.ERROR, "Erreur", "Échec de la suppression du categorie.");
//                    }
//                });
//    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    void retournerALaPagePrecedente(ActionEvent event) {
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
    void initialize() {

        Category c = new Category();
        CategoryService category = new CategoryService();
        try {
             c = category.getCateById(ListeCategorie.idCat );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        DescriptionCategorie.setText(c.getDescription());
        NomCategorie.setText(c.getName());

        LocalDate localDate = c.getCreated_Date().toLocalDate();
        DateCreation.setValue(localDate);
        assert DateCreation != null : "fx:id=\"DateCreation\" was not injected: check your FXML file 'DetailsCategorie.fxml'.";
        assert DescriptionCategorie != null : "fx:id=\"DescriptionCategorie\" was not injected: check your FXML file 'DetailsCategorie.fxml'.";
        assert ModifierCategorie != null : "fx:id=\"ModifierCategorie\" was not injected: check your FXML file 'DetailsCategorie.fxml'.";
        assert NomCategorie != null : "fx:id=\"NomCategorie\" was not injected: check your FXML file 'DetailsCategorie.fxml'.";
        assert SupprimerCategorie != null : "fx:id=\"SupprimerCategorie\" was not injected: check your FXML file 'DetailsCategorie.fxml'.";

    }

}
