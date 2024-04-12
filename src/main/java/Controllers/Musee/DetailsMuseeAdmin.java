package Controllers.Musee;
import Entités.Musee;
import Services.MuseeService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.Label;
import java.io.IOException;
import java.time.LocalDate;

public class DetailsMuseeAdmin {

    @FXML
    private Button ConsulterMusee;

    @FXML
    private DatePicker DateDeb;

    @FXML
    private DatePicker DateF;

    @FXML
    private TextArea DescriptionMusee;
    @FXML
    private Label backLabel;
    @FXML
    private Button ModifierMusee;

    @FXML
    private TextField NomMusee;

    @FXML
    private Button SupprimerMusee;

    @FXML
    private TextField VilleMusee;

    @FXML
    private TextField heureF;

    @FXML
    private TextField heureO;

    @FXML
    void handleAjouterMusee(ActionEvent event) {

    }

    int id;
    public void populateFields(Musee musee) {
        NomMusee.setText(musee.getNom());
        DescriptionMusee.setText(musee.getDescription());
        VilleMusee.setText(musee.getVille());
        if (musee.getDateDebut() != null && musee.getDateFin() != null) {
            DateDeb.setValue(LocalDate.parse(musee.getDateDebut()));
            DateF.setValue(LocalDate.parse(musee.getDateFin()));
        } else {
            // Handle the case when either date value is null
            // For example, set default values or handle the error differently
            // You can leave this block empty if you don't need to handle the case
        }
        heureO.setText(musee.getHeureOuverture());
        heureF.setText(musee.getHeureFermeture());
        id = musee.getId();
    }

    @FXML
    private void retournerALaPagePrecedente(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Musee/Espace Admin/ListeMuseeAdmin.fxml"));
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
    void handleSupprimerMusee(ActionEvent event) {
        // You may need to confirm the deletion with the user before proceeding
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setHeaderText("Supprimer le musée");
        confirmationAlert.setContentText("Êtes-vous sûr de vouloir supprimer ce musée?");
        confirmationAlert.showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> {
                    // Use the MuseeService to delete the museum
                    MuseeService museeService = new MuseeService();
                    boolean deleted = museeService.deleteMusee(id);

                    // Display success or fail message based on the deletion result
                    if (deleted) {
                        showAlert(Alert.AlertType.INFORMATION, "Succès", "Musée supprimé avec succès.");
                        try {
                            // Load the FXML file for the list of museums
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Musee/Espace Admin/ListeMuseeAdmin.fxml"));
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
                        showAlert(Alert.AlertType.ERROR, "Erreur", "Échec de la suppression du musée.");
                    }
                });
    }


    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }


}
