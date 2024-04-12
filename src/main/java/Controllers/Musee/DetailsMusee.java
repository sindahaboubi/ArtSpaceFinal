package Controllers.Musee;
import Controllers.Login.Session;
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
import java.util.List;

public class DetailsMusee {

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
    void handleModifierMusee(ActionEvent event) {
        if (NomMusee.getText().isEmpty() || VilleMusee.getText().isEmpty() || DateDeb.getValue() == null || DateF.getValue() == null || heureO.getText().isEmpty() || heureF.getText().isEmpty() || DescriptionMusee.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Veuillez remplir tous les champs.");
            return;
        }

        // Validate heureO format
        if (!heureO.getText().matches("^([01]?[0-9]|2[0-3]):[0-5][0-9]$")) {
            showAlert(Alert.AlertType.ERROR, "Error", "Format incorrect pour l'heure d'ouverture (hh:mm).");
            return;
        }

        // Validate heureF format
        if (!heureF.getText().matches("^([01]?[0-9]|2[0-3]):[0-5][0-9]$")) {
            showAlert(Alert.AlertType.ERROR, "Error", "Format incorrect pour l'heure de fermeture (hh:mm).");
            return;
        }
        Session session = Session.getInstance();
        String userEmail = session.getUserEmail();
        int userRole = session.getUserRole();
        int useriD = session.getUserId();

        // Retrieve the existing Musee object to get the accept value
        MuseeService museeService = new MuseeService();
        List<Musee> musees = museeService.getAllMuseeById(useriD);
        if (!musees.isEmpty()) {
            Musee existingMusee = musees.get(0);
           System.out.println(existingMusee.getId());
            System.out.println(id);

            // Create the updated Musee object with the same ID and accept value
            Musee updatedMusee = new Musee(
                    id,
                    NomMusee.getText(),
                    DescriptionMusee.getText(),
                    VilleMusee.getText(),
                    DateDeb.getValue().toString(),
                    DateF.getValue().toString(),
                    heureO.getText(),
                    heureF.getText(),
                    existingMusee.getIdArtist() ,// Retain the existing artist ID

                    0 // Retain the existing accept value
            );

            // Use the MuseeService to update the museum
            boolean updated = museeService.updateMusee(updatedMusee);

            // Display success or fail message based on the update result
            if (updated) {
                showAlert(Alert.AlertType.INFORMATION, "Succès", "Musée mis à jour avec succès.");
                try {
                    // Load the FXML file for the list of museums
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Musee/Espace Artiste/ListeMusee.fxml"));
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
                showAlert(Alert.AlertType.ERROR, "Erreur", "Échec de la mise à jour du musée.");
                try {
                    // Load the FXML file for the list of museums
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Musee/Espace Artiste/ListeMusee.fxml"));
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
    private void retournerALaPagePrecedente(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Musee/Espace Client/ListeMusee.fxml"));
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
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Musee/Espace Artiste/ListeMusee.fxml"));
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