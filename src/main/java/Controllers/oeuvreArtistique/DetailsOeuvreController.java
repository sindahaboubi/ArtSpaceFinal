package Controllers.oeuvreArtistique;

import Entités.Category;
import Entités.Commentaire;
import Entités.OeuvreArtistique;
import Services.CategoryService;
import Services.CommentaireService;
import Services.ServiceOeuvreArtistique;
import Util.DataSource;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.ListView;

public class DetailsOeuvreController {
    @FXML
    private TextField titleLabel;

    @FXML
    private TextField priceLabel;

    @FXML
    private ImageView imageView;
    @FXML
    private Label dateCreationOeuvre;
    @FXML
    private TextArea descriptionOeuvre;
    @FXML
    private Label acceptationLabel;
    private OeuvreArtistique oeuvre;
    private final ServiceOeuvreArtistique serviceOeuvreArtistique = new ServiceOeuvreArtistique();
    private final CommentaireService commentaireService = new CommentaireService();
    @FXML
    private Button btwModifier;
    @FXML
    private ComboBox categorieComboBox;
    @FXML
    private ComboBox<String> etatComboBox;

    @FXML
    private ListView<String> commentListView;// ListView affichant les commentaires sous forme de texte
    public List<Commentaire> listComnt;

    @FXML
    private Button btnPlus;

    @FXML
    private TextField txtNouveauCommentaire;

    @FXML
    private Button btnAjouter;

    @FXML
    private void initialize() {
        categorieComboBox.setDisable(true);
        etatComboBox.setDisable(true);
        ObservableList<String> etatList = FXCollections.observableArrayList("En stock", "Vendu");
        etatComboBox.setItems(etatList);

    }

    public void afficherDetails(OeuvreArtistique oeuvre) {
        CategoryService categoryService = new CategoryService();
        this.oeuvre = oeuvre;
        List<Category> categories = null;
        try {
            categories = categoryService.getAllCategories();
            for (Category category : categories) {
                categorieComboBox.getItems().add(category.getName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int idCategorie = oeuvre.getIdCategorie();
        for (Category category : categories) {
            if (category.getId_category() == idCategorie) {
                categorieComboBox.getSelectionModel().select(category.getName());
                break;
            }
        }

        List<Category> finalCategories = categories;
        categorieComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && newValue instanceof String) {
                String categoryName = (String) newValue;
                Category selectedCategory = null;
                for (Category category : finalCategories) {
                    if (category.getName().equals(categoryName)) {
                        selectedCategory = category;
                        break;
                    }
                }
                if (selectedCategory != null) {
                    oeuvre.setIdCategorie(selectedCategory.getId_category());
                }
            }
        });
        titleLabel.setText(oeuvre.getTitre());
        priceLabel.setText(String.valueOf(oeuvre.getPrix()));
        dateCreationOeuvre.setText(String.valueOf(oeuvre.getDateCreation()));
        descriptionOeuvre.setText(oeuvre.getDescription());
        acceptationLabel.setText(String.valueOf(oeuvre.getAcceptation()));
        String etatTexte = (oeuvre.getEtat() == 0) ? "En stock" : "Vendu";
        etatComboBox.setValue(etatTexte);
        BackgroundFill backgroundFill;
        if (oeuvre.getAcceptation() == 1) {
            acceptationLabel.setText("Publication acceptée");
            backgroundFill = new BackgroundFill(Color.GREEN, new CornerRadii(40), null);
            acceptationLabel.setBackground(new Background(backgroundFill));
        } else if (oeuvre.getAcceptation() == 0)  {
            acceptationLabel.setText("Publication en cours de validation");
            Color blueColor = Color.rgb(0, 0, 255, 0.7);
            backgroundFill = new BackgroundFill(blueColor, new CornerRadii(40), null);
            acceptationLabel.setBackground(new Background(backgroundFill));
        }
        else {
            acceptationLabel.setText("Publication refusée");
            Color lightRed = Color.rgb(255, 99, 71, 0.7);
            backgroundFill = new BackgroundFill(lightRed, new CornerRadii(40), null);
            acceptationLabel.setBackground(new Background(backgroundFill));
            btwModifier.setDisable(true);
        }

        byte[] imageData = oeuvre.getImage();
        if (imageData != null) {
            Image image = new Image(new ByteArrayInputStream(imageData));
            ImageView fixedSizeImageView = new ImageView(image);
            fixedSizeImageView.setFitWidth(173);
            fixedSizeImageView.setFitHeight(174);
            fixedSizeImageView.setLayoutX(8);
            fixedSizeImageView.setLayoutY(37);
            AnchorPane parentPane = (AnchorPane) imageView.getParent();
            parentPane.getChildren().remove(imageView);
            parentPane.getChildren().add(fixedSizeImageView);
            imageView = fixedSizeImageView;
        }
        afficherCommentaires(oeuvre);
    }


    public void afficherDetailsClient(OeuvreArtistique oeuvre) {
        CategoryService categoryService = new CategoryService();
        this.oeuvre = oeuvre;
        List<Category> categories = null;
        try {
            categories = categoryService.getAllCategories();
            for (Category category : categories) {
                categorieComboBox.getItems().add(category.getName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int idCategorie = oeuvre.getIdCategorie();
        for (Category category : categories) {
            if (category.getId_category() == idCategorie) {
                categorieComboBox.getSelectionModel().select(category.getName());
                break;
            }
        }

        List<Category> finalCategories = categories;
        categorieComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null && newValue instanceof String) {
                String categoryName = (String) newValue;
                Category selectedCategory = null;
                for(Category category : finalCategories) {
                    if(category.getName().equals(categoryName)) {
                        selectedCategory = category;
                        break;
                    }
                }
                if(selectedCategory != null) {
                    oeuvre.setIdCategorie(selectedCategory.getId_category());
                }
            }
        });
        titleLabel.setText(oeuvre.getTitre());
        priceLabel.setText(String.valueOf(oeuvre.getPrix()));
        dateCreationOeuvre.setText(String.valueOf(oeuvre.getDateCreation()));
        descriptionOeuvre.setText(oeuvre.getDescription());
        acceptationLabel.setText(String.valueOf(oeuvre.getAcceptation()));
        String etatTexte = (oeuvre.getEtat() == 0) ? "En stock" : "Vendu";
        etatComboBox.setValue(etatTexte);
        BackgroundFill backgroundFill;
        if (oeuvre.getAcceptation() == 1) {
            acceptationLabel.setText("Publication acceptée");
            backgroundFill = new BackgroundFill(Color.GREEN, new CornerRadii(40), null);
            acceptationLabel.setBackground(new Background(backgroundFill));
        } else if (oeuvre.getAcceptation() == 0)  {
            acceptationLabel.setText("Publication en cours de validation");
            Color blueColor = Color.rgb(0, 0, 255, 0.7);
            backgroundFill = new BackgroundFill(blueColor, new CornerRadii(40), null);
            acceptationLabel.setBackground(new Background(backgroundFill));
        }
        else {
            acceptationLabel.setText("Publication refusée");
            Color lightRed = Color.rgb(255, 99, 71, 0.7);
            backgroundFill = new BackgroundFill(lightRed, new CornerRadii(40), null);
            acceptationLabel.setBackground(new Background(backgroundFill));
            btwModifier.setDisable(true);
        }

        byte[] imageData = oeuvre.getImage();
        if (imageData != null) {
            Image image = new Image(new ByteArrayInputStream(imageData));
            ImageView fixedSizeImageView = new ImageView(image);
            fixedSizeImageView.setFitWidth(173);
            fixedSizeImageView.setFitHeight(174);
            fixedSizeImageView.setLayoutX(10);
            fixedSizeImageView.setLayoutY(39);
            AnchorPane parentPane = (AnchorPane) imageView.getParent();
            parentPane.getChildren().remove(imageView);
            parentPane.getChildren().add(fixedSizeImageView);
            imageView = fixedSizeImageView;
        }
    }

    public void afficherDetailsAdmin(OeuvreArtistique oeuvre) {
        CategoryService categoryService = new CategoryService();
        this.oeuvre = oeuvre;
        List<Category> categories = null;
        try {
            categories = categoryService.getAllCategories();
            for (Category category : categories) {
                categorieComboBox.getItems().add(category.getName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int idCategorie = oeuvre.getIdCategorie();
        for (Category category : categories) {
            if (category.getId_category() == idCategorie) {
                categorieComboBox.getSelectionModel().select(category.getName());
                break;
            }
        }

        List<Category> finalCategories = categories;
        categorieComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null && newValue instanceof String) {
                String categoryName = (String) newValue;
                Category selectedCategory = null;
                for(Category category : finalCategories) {
                    if(category.getName().equals(categoryName)) {
                        selectedCategory = category;
                        break;
                    }
                }
                if(selectedCategory != null) {
                    oeuvre.setIdCategorie(selectedCategory.getId_category());
                }
            }
        });
        titleLabel.setText(oeuvre.getTitre());
        priceLabel.setText(String.valueOf(oeuvre.getPrix()));
        dateCreationOeuvre.setText(String.valueOf(oeuvre.getDateCreation()));
        descriptionOeuvre.setText(oeuvre.getDescription());
        acceptationLabel.setText(String.valueOf(oeuvre.getAcceptation()));
        String etatTexte = (oeuvre.getEtat() == 0) ? "En stock" : "Vendu";
        etatComboBox.setValue(etatTexte);
        byte[] imageData = oeuvre.getImage();
        if (imageData != null) {
            Image image = new Image(new ByteArrayInputStream(imageData));
            ImageView fixedSizeImageView = new ImageView(image);
            fixedSizeImageView.setFitWidth(200);
            fixedSizeImageView.setFitHeight(200);
            fixedSizeImageView.setLayoutX(150);
            fixedSizeImageView.setLayoutY(130);
            AnchorPane parentPane = (AnchorPane) imageView.getParent();
            parentPane.getChildren().remove(imageView);
            parentPane.getChildren().add(fixedSizeImageView);
            imageView = fixedSizeImageView;
        }
    }

    private void supprimerOeuvre() {
        if (oeuvre != null) {
            try {
                serviceOeuvreArtistique.supprimerOeuvre(oeuvre);
                retournerALaPagePrecedenteArtiste();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void retournerALaPagePrecedenteArtiste() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/OeuvreArtistique/Espace Artiste/ListeOeuvresArtistiques.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) titleLabel.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void retournerALaPagePrecedenteClient() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/OeuvreArtistique/Espace Client/ListeOeuvresArtistiques.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) titleLabel.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void retournerALaPagePrecedenteAdmint() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/OeuvreArtistique/Espace Admin/ListeOeuvresArtistiques.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) titleLabel.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void confirmerSuppression() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText("Voulez-vous vraiment supprimer cette œuvre ?");
        ButtonType buttonTypeOK = new ButtonType("Oui");
        ButtonType buttonTypeCancel = new ButtonType("Non");
        alert.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonTypeOK) {
            supprimerOeuvre();
        } else {
        }
    }

    @FXML
    private void modifierOeuvre() {
        titleLabel.setEditable(true);
        descriptionOeuvre.setEditable(true);
        priceLabel.setEditable(true);
        categorieComboBox.setDisable(false);
        etatComboBox.setDisable(false);
        titleLabel.setStyle("-fx-border-color: gray;");
        descriptionOeuvre.setStyle("-fx-border-color: gray;");
        priceLabel.setStyle("-fx-border-color: gray;");
        etatComboBox.setStyle("-fx-border-color: gray;");
        btwModifier.setText("Enregistrer");
        btwModifier.setOnAction(event -> enregistrerModifications());
    }

    private void enregistrerModifications() {
        String nouveauTitre = titleLabel.getText();
        String nouvelleDescription = descriptionOeuvre.getText();
        float nouveauPrix = Float.parseFloat(priceLabel.getText());
        String nouvelleCategorie = (String) categorieComboBox.getValue();
        oeuvre.setTitre(nouveauTitre);
        oeuvre.setDescription(nouvelleDescription);
        oeuvre.setPrix(nouveauPrix);
        String etatTexte = etatComboBox.getValue();
        int nouvelEtat;
        if (etatTexte.equals("En stock")) {
            nouvelEtat = 0;
        } else {
            nouvelEtat = 1;
        }
        oeuvre.setEtat(nouvelEtat);
        try {
            serviceOeuvreArtistique.modifierOeuvre(oeuvre);
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Succès");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Oeuvre Artistique mise à jour avec succès");
            successAlert.showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        titleLabel.setEditable(false);
        descriptionOeuvre.setEditable(false);
        priceLabel.setEditable(false);
        categorieComboBox.setDisable(true);
        etatComboBox.setDisable(true);
        titleLabel.setStyle("-fx-border-color: transparent; -fx-background-color: transparent; -fx-text-inner-color: black;");
        descriptionOeuvre.setStyle("-fx-border-color: transparent; -fx-background-color: transparent; -fx-text-inner-color: black;");
        priceLabel.setStyle("-fx-border-color: transparent; -fx-background-color: transparent; -fx-text-inner-color: black;");
        etatComboBox.setStyle("-fx-border-color: transparent; -fx-background-color: transparent; -fx-text-inner-color: black;");
        btwModifier.setText("Modifier");
        btwModifier.setOnAction(event -> modifierOeuvre());
    }

    // commentaire
    public void afficherCommentaires(OeuvreArtistique oeuvre) {
        List<Commentaire> commentaires = null;
        try {
            commentaires = commentaireService.listeCommentaireByIdOeuvre(oeuvre.getId());
            System.out.println("commentaire" + commentaires);
            // Utilisez votre service pour récupérer les commentaires
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (commentaires != null) {
            ObservableList<String> observableCommentaires = FXCollections.observableArrayList();

            for (Commentaire commentaire : commentaires) {
                String commentaireText =commentaire.getId()+"   "+ commentaire.getContent() + "     " + commentaire.getDateCreation(); // Concaténer le contenu et la date de création
                observableCommentaires.add(commentaireText); // Ajouter le contenu du commentaire avec sa date de création
            }

            commentListView.setItems(observableCommentaires);

          commentListView.setCellFactory(param -> new CommentListCell());


        }
    }



    @FXML
    private void activerAjout() {
        btnPlus.setVisible(false);
        txtNouveauCommentaire.setDisable(false);
        btnAjouter.setVisible(true);
    }

    @FXML
    private void ajouterCommentaire() {
        String nouveauCommentaire = txtNouveauCommentaire.getText();
        if (!nouveauCommentaire.isEmpty()) {
            Commentaire nouveau = new Commentaire();
            nouveau.setContent(nouveauCommentaire);
            nouveau.setIdOeuvre(oeuvre.getId());
            nouveau.setIdClient(0); // ID client par défaut

            try {
                commentaireService.ajouterCommentaire(nouveau);
                // Actualisez l'affichage des commentaires
                afficherCommentaires(oeuvre);
                // Afficher une alerte de succès
                showAlert(Alert.AlertType.INFORMATION, "Success", "Commentaire ajouté avec succès.");
            } catch (SQLException e) {
                e.printStackTrace();
                // Gérer l'erreur d'ajout de commentaire
                // Afficher une alerte d'erreur
                showAlert(Alert.AlertType.ERROR, "Error", "Erreur lors de l'ajout du commentaire.");
            }
        }

        // Réinitialisez les éléments après l'ajout
        txtNouveauCommentaire.clear();
        txtNouveauCommentaire.setDisable(true);
        btnAjouter.setVisible(false);
        btnPlus.setVisible(true);
    }
  // Modifier commentaire
    @FXML
    public void modifierCommentaireAction() {
        String selectedCommentText = commentListView.getSelectionModel().getSelectedItem();
        if (selectedCommentText != null) {
            int idCommentaire = extractCommentId(selectedCommentText);
            if (idCommentaire != 1) {
                String nouveauContenu = txtNouveauCommentaire.getText();
                if (!nouveauContenu.isEmpty()) {
                    try {
                        Connection connection = DataSource.getInstance().getCon();
                        PreparedStatement statement = (PreparedStatement) connection.prepareStatement("UPDATE commentaire SET content = ? WHERE id = ?");
                        statement.setString(1, nouveauContenu);
                        statement.setInt(2, idCommentaire);

                        int rowsAffected = statement.executeUpdate();
                        statement.close();

                        if (rowsAffected == 0) {
                            showAlert(Alert.AlertType.ERROR, "Error", "Le commentaire avec l'ID " + idCommentaire + " n'existe pas dans la base de données.");
                        } else {
                            showAlert(Alert.AlertType.INFORMATION, "Success", "Commentaire modifié avec succès.");
                            refreshCommentList();
                        }
                    } catch (SQLException e) {
                        showAlert(Alert.AlertType.ERROR, "Error", "Erreur lors de la modification du commentaire dans la base de données : " + e.getMessage());
                    }
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "Le contenu du commentaire ne peut pas être vide.");
                }
            }
        }
    }



//supprimer commentaire
@FXML
public void supprimerCommentaireAction() {
    String selectedCommentText = commentListView.getSelectionModel().getSelectedItem();
    if (selectedCommentText != null) {
        int idCommentaire = extractCommentId(selectedCommentText);
        if (idCommentaire != 1) {
            try {
                Connection conn = DataSource.getInstance().getCon();
                String query = "DELETE FROM commentaire WHERE id = ?";
                try (PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(query)) {
                    pstmt.setInt(1, idCommentaire);
                    pstmt.executeUpdate();
                    // Si la suppression réussit, mettre à jour la liste de commentaires affichée
                    refreshCommentList();
                    // Afficher une alerte de succès
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Commentaire supprimé avec succès.");
                } catch (SQLException e) {
                    // Gérer toute exception SQL
                    showAlert(Alert.AlertType.ERROR, "Error", "Erreur lors de la suppression du commentaire dans la base de données : " + e.getMessage());
                }
            } catch (Exception e) {
                // Gérer toute exception de connexion à la base de données
                showAlert(Alert.AlertType.ERROR, "Error", "Erreur lors de la connexion à la base de données : " + e.getMessage());
            }
        }
    }
}

    private int extractCommentId(String selectedCommentText) {
        // Utiliser une expression régulière pour extraire l'ID du commentaire de la chaîne de texte
        Pattern pattern = Pattern.compile("^(\\d+)\\s");
        Matcher matcher = pattern.matcher(selectedCommentText);
        if (matcher.find()) {
            try {
                // Récupérer les premiers caractères avant le premier espace qui correspondent à l'ID du commentaire
                String idStr = matcher.group(1);
                // Convertir la chaîne d'ID en entier et le retourner
                return Integer.parseInt(idStr);
            } catch (NumberFormatException e) {
                // Si la conversion en entier échoue, afficher une erreur et retourner une valeur par défaut
                showAlert(Alert.AlertType.ERROR, "Error", "Impossible de convertir l'ID en entier : " + e.getMessage());
                return -1; // ou lever une exception par exemple
            }
        } else {
            // Si aucun ID n'est trouvé dans la chaîne de texte, afficher une erreur et retourner une valeur par défaut
            showAlert(Alert.AlertType.ERROR, "Error", "Aucun ID trouvé dans la chaîne de texte.");
            return -1; // ou lever une exception par exemple
        }
    }



    private void refreshCommentList() {
        // Récupérer les commentaires depuis la base de données et mettre à jour la liste affichée dans le ListView
        // Par exemple, si vous utilisez un modèle de données observable (ObservableList), vous pouvez mettre à jour cette liste ici
        // Par exemple :
        ObservableList<String> items = FXCollections.observableArrayList();
        // Code pour récupérer les commentaires depuis la base de données et les ajouter à la liste items
        // ...
        commentListView.setItems(items); // Mettre à jour le ListView avec la nouvelle liste de commentaires

    }
    @FXML
    private void handleTraitement(ActionEvent event) {
        ChoiceDialog<String> dialog = new ChoiceDialog<>("Accepter", "Accepter", "Refuser");
        dialog.setTitle("Traitement de l'œuvre");
        dialog.setHeaderText(null);
        dialog.setContentText("Choisissez une action à effectuer:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(choice -> {
            if (choice.equals("Accepter")) {
                oeuvre.setAcceptation(1);
            } else if (choice.equals("Refuser")) {
                oeuvre.setAcceptation(2);
            }
            String nouveauTitre = titleLabel.getText();
            String nouvelleDescription = descriptionOeuvre.getText();
            float nouveauPrix = Float.parseFloat(priceLabel.getText());
            oeuvre.setTitre(nouveauTitre);
            oeuvre.setDescription(nouvelleDescription);
            oeuvre.setPrix(nouveauPrix);
            String etatTexte = etatComboBox.getValue();
            int nouvelEtat;
            if (etatTexte.equals("En stock")) {
                nouvelEtat = 0;
            } else {
                nouvelEtat = 1;
            }
            oeuvre.setEtat(nouvelEtat);
            try {
                serviceOeuvreArtistique.modifierOeuvre(oeuvre);
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Modification réussie");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Les modifications ont été enregistrées avec succès.");
                successAlert.showAndWait();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/OeuvreArtistique/Espace Admin/ListeOeuvresArtistiques.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        });
    }




   private void showAlert(Alert.AlertType alertType, String title, String content) {
       Alert alert = new Alert(alertType);
       alert.setTitle(title);
       alert.setContentText(content);
        alert.showAndWait();
   }
}
