package Controllers.General;

import Entités.OeuvreArtistique;
import Services.ServiceOeuvreArtistique;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Accueil {
    @FXML
    private FlowPane flowPane;
    @FXML
    private Button AProposLabel;
    @FXML
    private Button nosMusees;

    private ServiceOeuvreArtistique serviceOeuvreArtistique;
    @FXML
    private Button voirPlusBtn;

    public Accueil() {
        serviceOeuvreArtistique = new ServiceOeuvreArtistique();
    }

    @FXML
    public void initialize() {
        afficherOeuvres();
        flowPane.setAlignment(Pos.CENTER);
    }

    private void afficherOeuvres() {
        try {
            List<OeuvreArtistique> oeuvres = serviceOeuvreArtistique.listerOeuvres();
            int count = Math.min(oeuvres.size(), 3);
            double largeurDisponible = flowPane.getWidth();
            double largeurCarte = largeurDisponible / count;
            for (int i = 0; i < count; i++) {
                OeuvreArtistique oeuvre = oeuvres.get(i);
                VBox carte = creerCarte(oeuvre);
                carte.setPrefWidth(largeurCarte);
                flowPane.getChildren().add(carte);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private VBox creerCarte(OeuvreArtistique oeuvre) {
        VBox carte = new VBox();
        carte.setStyle("-fx-background-color: #E1E1E1; " +
                "-fx-border-color: #A4A4A4; " +
                "-fx-border-width: 1px; " +
                "-fx-border-radius: 5px; " +
                "-fx-padding: 14px;");
        carte.setAlignment(Pos.CENTER);
        Image image = new Image(new ByteArrayInputStream(oeuvre.getImage()));
        double tailleImage = 150;
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(tailleImage);
        imageView.setFitHeight(tailleImage);
        Label titreLabel = new Label(oeuvre.getTitre());
        titreLabel.setStyle("-fx-font-size: 14px; " +
                "-fx-font-weight: bold;");
        Label prixLabel = new Label("Prix : " + oeuvre.getPrix());
        prixLabel.setStyle("-fx-font-size: 12px; " +
                "-fx-text-fill: #666;");
        carte.getChildren().addAll(imageView, titreLabel, prixLabel);
        return carte;
    }

    @FXML
    private void retournerALaPageAPropos() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/OeuvreArtistique/General/propos.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) AProposLabel.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void retournerALaPageListe() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/OeuvreArtistique/Espace Client/ListeOeuvresArtistiques.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) voirPlusBtn.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void retournerALaPageMuseesArtiste() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Musee/Espace Client/ListeMusee.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) nosMusees.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}