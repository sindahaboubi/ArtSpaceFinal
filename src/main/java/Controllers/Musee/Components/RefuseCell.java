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

public class RefuseCell extends TableCell<Musee, Void> {

    private final Button refuseButton = new Button("X");
    private final MuseeService museeService = new MuseeService(); // Instantiate MuseeService
    public RefuseCell() {
        refuseButton.setStyle("-fx-background-color: #ff0000; -fx-text-fill: white;"); // Red color

        refuseButton.setOnAction((ActionEvent event) -> {
            Musee musee = getTableView().getItems().get(getIndex());
            boolean success = museeService.refuseMusee(musee.getId());
            if (success) {
                showAlert(AlertType.INFORMATION, "Refusal Success", "Musee refused successfully.");
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
                showAlert(AlertType.ERROR, "Refusal Failed", "Failed to refuse Musee.");
            }
        });
    }



    @Override
    protected void updateItem(Void item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setGraphic(null);
        } else {
            setGraphic(refuseButton);
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
