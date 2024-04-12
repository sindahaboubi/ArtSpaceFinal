package Controllers.oeuvreArtistique;


import Entités.Commentaire;
import Services.CommentaireService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class EditCommentController {
    @FXML
    private TextArea commentTextArea;

    @FXML
    private Button Enregistrer;

    @FXML
    private Button Fermer;



    private Commentaire commentaire;

    private CommentaireService commentaireService;

    public void initialize() {
        commentaireService = new CommentaireService();
    }

    public void setCommentaire(Commentaire commentaire) {
        this.commentaire = commentaire;
        commentTextArea.setText(commentaire.getContent());
    }

    @FXML
    private void handleSaveButton() {
        String newContent = commentTextArea.getText();
        commentaire.setContent(newContent);
        commentaireService.modifierCommentaire(commentaire, commentaire.getId());
        closeWindow();
        showAlert(Alert.AlertType.INFORMATION, "Information", "Le commentaire a été Modifier avec succès.");
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    private void handleCloseButton() {
        closeWindow();
    }


    @FXML
    private void handleCancelButton() {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) commentTextArea.getScene().getWindow();
        stage.close();
    }





}
