package Controllers.Musee;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.io.IOException;

public class SidebarController {

    @FXML
    private AnchorPane sidebarPane;

    @FXML
    private Label musesLabel;

    @FXML
    private Label oeuvresLabel;

    @FXML
    private ImageView musesIcon;

    @FXML
    private ImageView oeuvresIcon;

    @FXML
    private Line separatorLine;

    public AnchorPane getSidebarPane() {
        return sidebarPane;
    }

    public Label getMusesLabel() {
        return musesLabel;
    }

    public Label getOeuvresLabel() {
        return oeuvresLabel;
    }

    public ImageView getMusesIcon() {
        return musesIcon;
    }

    public ImageView getOeuvresIcon() {
        return oeuvresIcon;
    }

    public Line getSeparatorLine() {
        return separatorLine;
    }
    @FXML
    public void handleMuseesClick(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Musee/Espace Artiste/ListeMusee.fxml"));
            Parent root = loader.load();

            // Populate fields with selected museum's information
            // Navigate to DetailsMusee page
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle any potential IOException here
        }
    }
    @FXML
    public void handleOeuvresClick(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/OeuvreArtistique/Espace Artiste/ListeOeuvresArtistiques.fxml"));
            Parent root = loader.load();

            // Populate fields with selected museum's information
            // Navigate to DetailsMusee page
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle any potential IOException here
        }
    }
    @FXML
    public void initialize() {
        // You can add initialization code here
        // For example, setting icons or text
        // For now, let's just set text colors to white
        musesLabel.setTextFill(javafx.scene.paint.Color.WHITE);
        oeuvresLabel.setTextFill(javafx.scene.paint.Color.WHITE);
    }
}
