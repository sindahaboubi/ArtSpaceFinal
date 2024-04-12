package Controllers.oeuvreArtistique;

import Controllers.Login.Session;
import Entités.OeuvreArtistique;
import Services.ServiceOeuvreArtistique;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ListeOeuvresController {
    @FXML
    private VBox carteContainer;
    @FXML
    private AnchorPane mainAnchorPane;
    @FXML
    private Button btnAjouter;
    private final ServiceOeuvreArtistique serviceOeuvreArtistique = new ServiceOeuvreArtistique();

    private final double CARD_WIDTH = 120.0;
    private final double CARD_HEIGHT = 160.0;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    public void initialize() {
        listerOeuvresByIdArtiste();
        btnAjouter.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/OeuvreArtistique/Espace Artiste/AjouterOeuvreArtistique.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) btnAjouter.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void afficherOeuvres() {

        try {
            List<OeuvreArtistique> oeuvres = serviceOeuvreArtistique.listerOeuvres();
            int count = 0;
            HBox ligneActuelle = new HBox();
            for (OeuvreArtistique oeuvre : oeuvres) {
                VBox carte = new VBox();
                carte.setPrefWidth(150);
                carte.setPrefHeight(200);
                carte.setStyle("-fx-background-color: #f0f0f0; -fx-border-color: #ccc; -fx-border-width: 1px; -fx-border-radius: 5px; -fx-padding: 10px;");
                carte.setSpacing(10);
                ImageView imageView = new ImageView();
                imageView.setFitWidth(100);
                imageView.setFitHeight(100);
                byte[] imageData = oeuvre.getImage();
                if (imageData != null) {
                    Image image = new Image(new ByteArrayInputStream(imageData));
                    imageView.setImage(image);
                }
                Label titleLabel = new Label(oeuvre.getTitre());
                Label priceLabel = new Label(String.valueOf(oeuvre.getPrix()));
                Button detailsButton = new Button("Détails");
                detailsButton.setOnAction(event -> {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/OeuvreArtistique/Espace Artiste/DetailsOeuvreArtistique.fxml"));
                        Parent detailsView = loader.load();
                        DetailsOeuvreController detailsController = loader.getController();
                        detailsController.afficherDetails(oeuvre);
                        mainAnchorPane.getChildren().setAll(detailsView);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                titleLabel.setAlignment(Pos.CENTER);
                priceLabel.setAlignment(Pos.CENTER);
                carte.getChildren().addAll(imageView, titleLabel, priceLabel, detailsButton);
                carte.setAlignment(Pos.CENTER);
                ligneActuelle.getChildren().add(carte);
                count++;
                if (count % 3 == 0) {
                    carteContainer.getChildren().add(ligneActuelle);
                    ligneActuelle = new HBox();
                }
            }
            while (ligneActuelle.getChildren().size() < 3) {
                VBox emptySpace = new VBox();
                emptySpace.setPrefWidth(150);
                ligneActuelle.getChildren().add(emptySpace);
            }
            carteContainer.getChildren().add(ligneActuelle);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void listerOeuvresByIdArtiste() {
        Session session = Session.getInstance();
        String userEmail = session.getUserEmail();
        int userRole = session.getUserRole();
        int useriD = session.getUserId();
        try {

            List<OeuvreArtistique> oeuvres = serviceOeuvreArtistique.listerOeuvresParArtiste(useriD);
            int count = 0;
            HBox ligneActuelle = new HBox();
            for (OeuvreArtistique oeuvre : oeuvres) {
                VBox carte = new VBox();
                carte.setPrefWidth(150);
                carte.setPrefHeight(200);
                carte.setStyle("-fx-background-color: #f0f0f0; -fx-border-color: #ccc; -fx-border-width: 1px; -fx-border-radius: 5px; -fx-padding: 10px;");
                carte.setSpacing(10);
                ImageView imageView = new ImageView();
                imageView.setFitWidth(100);
                imageView.setFitHeight(100);
                byte[] imageData = oeuvre.getImage();
                if (imageData != null) {
                    Image image = new Image(new ByteArrayInputStream(imageData));
                    imageView.setImage(image);
                }
                Label titleLabel = new Label(oeuvre.getTitre());
                Label priceLabel = new Label(String.valueOf(oeuvre.getPrix()) + " DT");
                titleLabel.setStyle("-fx-font-weight: bold;");
                Button detailsButton = new Button("Détails");
                detailsButton.setOnAction(event -> {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/OeuvreArtistique/Espace Artiste/DetailsOeuvreArtistique.fxml"));
                        Parent detailsView = loader.load();
                        DetailsOeuvreController detailsController = loader.getController();
                        detailsController.afficherDetails(oeuvre);
                        mainAnchorPane.getChildren().setAll(detailsView);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                titleLabel.setAlignment(Pos.CENTER);
                priceLabel.setAlignment(Pos.CENTER);
                carte.getChildren().addAll(imageView, titleLabel, priceLabel, detailsButton);
                carte.setAlignment(Pos.CENTER);
                ligneActuelle.getChildren().add(carte);
                count++;
                if (count % 3 == 0) {
                    carteContainer.getChildren().add(ligneActuelle);
                    ligneActuelle = new HBox();
                }
            }
            while (ligneActuelle.getChildren().size() < 3) {
                VBox emptySpace = new VBox();
                emptySpace.setPrefWidth(150);
                ligneActuelle.getChildren().add(emptySpace);
            }
            carteContainer.getChildren().add(ligneActuelle);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

