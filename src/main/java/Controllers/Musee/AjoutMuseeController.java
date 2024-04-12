package Controllers.Musee;

import Controllers.Login.Session;
import Controllers.Musee.Components.TimePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import Services.MuseeService;
import Entités.Musee;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;

public class AjoutMuseeController {

    @FXML
    private Button AjouterMusee;
    @FXML
    private Button ConsulterMusee;

    @FXML
    private DatePicker DateDeb;

    @FXML
    private  DatePicker DateF;

    @FXML
    private TextArea DescriptionMusee;

    @FXML
    private TextField NomMusee;

    @FXML
    private TextField VilleMusee;

    @FXML
    private TextField heureF;

    @FXML
    private TextField heureO;

    @FXML
    private void handleAjouterMusee(ActionEvent event) {
        try {
            // Validate input fields
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

            // Perform further validation if needed
            Session session = Session.getInstance();
            String userEmail = session.getUserEmail();
            int userRole = session.getUserRole();
            int useriD = session.getUserId();

            // Create a Musee object with the data from the UI elements
            Musee musee = new Musee();
            musee.setNom(NomMusee.getText());
            musee.setDescription(DescriptionMusee.getText());
            musee.setVille(VilleMusee.getText());
            musee.setDateDebut(DateDeb.getValue().toString());
            musee.setDateFin(DateF.getValue().toString());
            musee.setHeureOuverture(heureO.getText());
            musee.setHeureFermeture(heureF.getText());
            musee.setIdArtist(useriD);  // Change as needed
            musee.setAccept(0);

            // Add Musee to service
            MuseeService museeService = new MuseeService();
            museeService.addMusee(musee);

            // Show success alert
            showAlert(Alert.AlertType.INFORMATION, "Success", "Musee ajouté avec succès.");

            // Load the ListeMusee.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Musee/Espace Artiste/ListeMusee.fxml"));
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
            showAlert(Alert.AlertType.ERROR, "Error", "Erreur lors de l'ajout du musee.");
        }
    }
    @FXML
    void handleConsulterMusee(ActionEvent event) {
        try {
            // Load the FXML file for the new scene
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

}
