package Controllers.oeuvreArtistique;

import Entités.OeuvreArtistique;
import Services.ServiceOeuvreArtistique;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ListeOeuvresAdminController {
    @FXML
    private VBox carteContainer;
    @FXML
    private AnchorPane mainAnchorPane;
    private final ServiceOeuvreArtistique serviceOeuvreArtistique = new ServiceOeuvreArtistique();
    private final double CARD_WIDTH = 120.0;
    private final double CARD_HEIGHT = 160.0;

    @FXML
    private RadioButton voirTousRadioButton;
    @FXML
    private RadioButton aTraiterRadioButton;
    @FXML
    private RadioButton accepteesRadioButton;
    @FXML
    private RadioButton refuseesRadioButton;


    @FXML
    private ScrollPane scrollPane;

    @FXML
    public void initialize() {
        afficherOeuvres();

        voirTousRadioButton.setOnAction(event -> {
            if (voirTousRadioButton.isSelected()) {
                System.out.println("Affichage de toutes les œuvres :");
                resetListAndDisplayAll(); // Réinitialiser la liste et afficher toutes les œuvres dans l'interface utilisateur
            }
            deselectionnerAutresBoutons(voirTousRadioButton);
        });

        // Ajoutez un gestionnaire d'événements aux boutons radio "A traiter"
        aTraiterRadioButton.setOnAction(event -> {
            if (aTraiterRadioButton.isSelected()) {
                System.out.println("Affichage des œuvres à traiter :");
                displayOeuvresATraiter(); // Afficher les œuvres à traiter dans l'interface utilisateur
            }
            deselectionnerAutresBoutons(aTraiterRadioButton);
        });

        accepteesRadioButton.setOnAction(event -> {
            if (accepteesRadioButton.isSelected()) {
                System.out.println("Affichage de toutes les œuvres :");
                displayOeuvresAcceptees(); // Réinitialiser la liste et afficher toutes les œuvres dans l'interface utilisateur
            }
            deselectionnerAutresBoutons(accepteesRadioButton);
        });

        refuseesRadioButton.setOnAction(event -> {
            if (refuseesRadioButton.isSelected()) {
                System.out.println("Affichage de toutes les œuvres :");
                displayOeuvresRefusees(); // Réinitialiser la liste et afficher toutes les œuvres dans l'interface utilisateur
            }
            deselectionnerAutresBoutons(refuseesRadioButton);
        });
    }

    private void deselectionnerAutresBoutons(RadioButton boutonSelectionne) {
        // Désélectionne tous les autres boutons radio sauf celui qui a été sélectionné
        if (boutonSelectionne != voirTousRadioButton) {
            voirTousRadioButton.setSelected(false);
        }
        if (boutonSelectionne != aTraiterRadioButton) {
            aTraiterRadioButton.setSelected(false);
        }
        if (boutonSelectionne != accepteesRadioButton) {
            accepteesRadioButton.setSelected(false);
        }
        if (boutonSelectionne != refuseesRadioButton) {
            refuseesRadioButton.setSelected(false);
        }
    }



    private void resetListAndDisplayAll() {
        carteContainer.getChildren().clear(); // Effacer les anciennes données
        afficherOeuvres(); // Afficher toutes les œuvres dans l'interface utilisateur
    }

    private void displayOeuvresATraiter() {
        carteContainer.getChildren().clear(); // Effacer les anciennes données
        try {
            List<OeuvreArtistique> oeuvresATraiter = serviceOeuvreArtistique.listerOeuvresATraiter();
            afficherOeuvresATraiter(oeuvresATraiter); // Afficher les œuvres à traiter dans l'interface utilisateur
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void displayOeuvresAcceptees() {
        carteContainer.getChildren().clear(); // Effacer les anciennes données
        try {
            List<OeuvreArtistique> oeuvresAcceptees = serviceOeuvreArtistique.listerOeuvresAcceptees();
            afficherOeuvresAcceptees(oeuvresAcceptees); // Afficher les œuvres à traiter dans l'interface utilisateur
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void displayOeuvresRefusees() {
        carteContainer.getChildren().clear(); // Effacer les anciennes données
        try {
            List<OeuvreArtistique> oeuvresRefusees = serviceOeuvreArtistique.listerOeuvresRefusees();
            afficherOeuvresRefusees(oeuvresRefusees); // Afficher les œuvres à traiter dans l'interface utilisateur
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void afficherOeuvres() {
        try {
            List<OeuvreArtistique> oeuvres = serviceOeuvreArtistique.listerOeuvresAdmin();
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
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/OeuvreArtistique/Espace Admin/DetailsOeuvreArtistique.fxml"));
                        Parent detailsView = loader.load();
                        DetailsOeuvreController detailsController = loader.getController();
                        detailsController.afficherDetailsAdmin(oeuvre);
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

    public void afficherOeuvresATraiter(List<OeuvreArtistique> oeuvresATraiter) {
        carteContainer.getChildren().clear(); // Effacer les anciennes données
        int count = 0;
        HBox ligneActuelle = new HBox();
        for (OeuvreArtistique oeuvre : oeuvresATraiter) {
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
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/OeuvreArtistique/Espace Admin/DetailsOeuvreArtistique.fxml"));
                    Parent detailsView = loader.load();
                    DetailsOeuvreController detailsController = loader.getController();
                    detailsController.afficherDetailsAdmin(oeuvre);
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
    }

    public void afficherOeuvresAcceptees(List<OeuvreArtistique> oeuvresATraiter) {
        carteContainer.getChildren().clear(); // Effacer les anciennes données
        int count = 0;
        HBox ligneActuelle = new HBox();
        for (OeuvreArtistique oeuvre : oeuvresATraiter) {
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
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/OeuvreArtistique/Espace Admin/DetailsOeuvreArtistique.fxml"));
                    Parent detailsView = loader.load();
                    DetailsOeuvreController detailsController = loader.getController();
                    detailsController.afficherDetailsAdmin(oeuvre);
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
    }

    public void afficherOeuvresRefusees(List<OeuvreArtistique> oeuvresATraiter) {
        carteContainer.getChildren().clear(); // Effacer les anciennes données
        int count = 0;
        HBox ligneActuelle = new HBox();
        for (OeuvreArtistique oeuvre : oeuvresATraiter) {
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
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/OeuvreArtistique/Espace Admin/DetailsOeuvreArtistique.fxml"));
                    Parent detailsView = loader.load();
                    DetailsOeuvreController detailsController = loader.getController();
                    detailsController.afficherDetailsAdmin(oeuvre);
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
    }

}
