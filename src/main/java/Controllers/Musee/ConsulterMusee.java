package Controllers.Musee;

import Controllers.Musee.Components.DecisionCell;
import Entités.Musee;
import Services.MuseeService;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.util.List;

public class ConsulterMusee {

        @FXML
        private TableView<Musee> TableMusee;

        @FXML
        private TableColumn<Musee, String> nomColumn;

        @FXML
        private TableColumn<Musee, String> descriptionColumn;

        @FXML
        private TableColumn<Musee, String> villeColumn;

        @FXML
        private TableColumn<Musee, String> dateDebutColumn;

        @FXML
        private TableColumn<Musee, String> dateFinColumn;

        @FXML
        private TableColumn<Musee, String> heureOuvertureColumn;

        @FXML
        private TableColumn<Musee, String> heureFermetureColumn;

        @FXML
        private TableColumn<Musee, String> decisionColumn;

        private MuseeService museeService;
    @FXML
    private void initialize() {
        museeService = new MuseeService();

        // Initialize columns
        nomColumn.setCellValueFactory(cellData -> Bindings.createStringBinding(() -> cellData.getValue().getNom()));
        descriptionColumn.setCellValueFactory(cellData -> Bindings.createStringBinding(() -> cellData.getValue().getDescription()));
        villeColumn.setCellValueFactory(cellData -> Bindings.createStringBinding(() -> cellData.getValue().getVille()));
        dateDebutColumn.setCellValueFactory(cellData -> Bindings.createStringBinding(() -> cellData.getValue().getDateDebut()));
        dateFinColumn.setCellValueFactory(cellData -> Bindings.createStringBinding(() -> cellData.getValue().getDateFin()));
        heureOuvertureColumn.setCellValueFactory(cellData -> Bindings.createStringBinding(() -> cellData.getValue().getHeureOuverture()));
        heureFermetureColumn.setCellValueFactory(cellData -> Bindings.createStringBinding(() -> cellData.getValue().getHeureFermeture()));

        // Set cell factory for decision column
        decisionColumn.setCellValueFactory(cellData -> {
            Musee musee = cellData.getValue();
            StringProperty decisionProperty = new SimpleStringProperty();
            if (musee != null) {
                int accept = musee.getAccept();
                switch (accept) {
                    case 0:
                        decisionProperty.set("En attente");
                        break;
                    case 1:
                        decisionProperty.set("Accepté");
                        break;
                    case 2:
                        decisionProperty.set("Refusé");
                        break;
                    default:
                        decisionProperty.set("Unknown");
                        break;
                }
            }
            return decisionProperty;
        });

        // Load data from the service
        List<Musee> musees = museeService.getAllMuseeAdmin();

        // Populate TableView
        TableMusee.getItems().addAll(musees);
    }


    private void setHeaderColor(TableColumn<?, ?> column) {
        // Set the background color of the column header
        column.setStyle("-fx-background-color: #ACE2E1;");
    }
    @FXML
    void handleAjouterMusee(ActionEvent event) {
        try {
            // Load the FXML file for the new scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Musee/Espace Artiste/AjouterMusee.fxml"));
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
    void handleTableViewClick(MouseEvent event) throws IOException {
        if (event.getClickCount() == 2) {
            // Double-click detected
            Musee selectedMusee = TableMusee.getSelectionModel().getSelectedItem();
            if (selectedMusee != null) {
                // Redirect to DetailsMusee page
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Musee/Espace Artiste/DetailsMusee.fxml"));
                Parent root = loader.load();
                DetailsMusee controller = loader.getController();

                // Populate fields with selected museum's information
                controller.populateFields(selectedMusee);

                // Navigate to DetailsMusee page
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }
        }
    }


}
