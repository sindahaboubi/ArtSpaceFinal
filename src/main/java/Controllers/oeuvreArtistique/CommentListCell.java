package Controllers.oeuvreArtistique;

import Entités.Commentaire;
import Services.CommentaireService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommentListCell extends ListCell<String> {

    private final CommentaireService commentaireService = new CommentaireService();

    @FXML
    private ListView<String> commentListView;

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            // Diviser la chaîne de l'élément en contenu et date de création
            String[] parts = item.split("     ");
            String content = parts[0];
            String dateCreation = parts[1];

            // Créer les boutons de suppression et de modification
            Button editButton = new Button("Modifier");
            Button deleteButton = new Button("Supprimer");



            // Définir les styles des boutons
            editButton.setStyle("-fx-background-color: #f5d000; ");
            deleteButton.setStyle("-fx-background-color: #C0392B; -fx-text-fill: white;");




            // Définir les actions des boutons
            editButton.setOnAction(event -> {
                int idCommentaire = extractCommentId(item);
                modifierCommentaireAction(idCommentaire);
            });
            deleteButton.setOnAction(event -> {
                int idCommentaire = extractCommentId(item);
                supprimerCommentaireAction(idCommentaire);
            });




            // Créer des étiquettes pour afficher le contenu et la date de création
            Label contentLabel = new Label(content);
            Label dateLabel = new Label(dateCreation);

            // Créer un conteneur HBox
            HBox buttonsBox = new HBox();
            buttonsBox.getChildren().addAll(contentLabel, dateLabel, editButton,deleteButton );
            buttonsBox.setSpacing(10);

            // Ajouter le conteneur HBox à la cellule
            setGraphic(buttonsBox);
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


    // Méthode pour supprimer le commentaire
    private void supprimerCommentaireAction(int idCommentaire) {
        // Récupérer le commentaire correspondant à l'ID
        Commentaire commentaire = commentaireService.getCommentaireById(idCommentaire);
        // Appeler la méthode de votre service pour supprimer le commentaire
        if (commentaire != null) {
            System.out.println("Commentaire trouvé : "+commentaire);
            commentaireService.supprimerCommentaire(commentaire,idCommentaire);
            showAlert(Alert.AlertType.INFORMATION, "Information", "Le commentaire a été supprimé avec succès.");
        } else {
            // Gérer le cas où aucun commentaire correspondant à l'ID n'est trouvé
            System.out.println("Aucun commentaire trouvé pour l'ID : " + idCommentaire);
        }
    }

    // Méthode pour modifier le commentaire
    private void modifierCommentaireAction(int idCommentaire) {
        Commentaire commentaire = commentaireService.getCommentaireById(idCommentaire);
        if (commentaire != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/OeuvreArtistique/Espace Artiste/edit_comment.fxml"));
            try {
                Parent root = loader.load();
                EditCommentController controller = loader.getController();
                controller.setCommentaire(commentaire);
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Aucun commentaire trouvé pour l'ID : " + idCommentaire);
        }
    }


    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
