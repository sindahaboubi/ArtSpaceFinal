package Controllers.Musee.Components;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.Alert.AlertType;
import Entités.Musee;
import Services.MuseeService; // Import MuseeService
import javafx.stage.Stage;

import java.io.IOException;

public class ValidateCell extends TableCell<Musee, Void> {

    private final Button validateButton = new Button("✓");
    private final MuseeService museeService = new MuseeService(); // Instantiate MuseeService

    public ValidateCell() {

        validateButton.setStyle("-fx-background-color: #00ff00; -fx-text-fill: white;"); // Green color


        validateButton.setOnAction((ActionEvent event) -> {
            Musee musee = getTableView().getItems().get(getIndex());
            boolean success = museeService.validateMusee(musee.getId());
            if (success) {
                showAlert(AlertType.INFORMATION, "Validation Success", "Musee validated successfully.");
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
                    showAlert(AlertType.ERROR, "Error", "Failed to load Musee list.");
                }
            } else {
                showAlert(AlertType.ERROR, "Validation Failed", "Failed to validate Musee.");
            }
        });
    }


    @Override
    protected void updateItem(Void item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setGraphic(null);
        } else {
            setGraphic(validateButton);
        }
    }

    private void showAlert(AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
