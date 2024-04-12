package Controllers.Login;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Services.UserService;
 import Util.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;


import java.io.IOException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Entités.utilisateur;
public class LoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    protected void forgetPassword() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("/Login/forgetPass.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Main.stage.setScene(scene);
        Main.stage.show();
    }

    @FXML
    protected void loginButton() throws IOException, SQLException, ClassNotFoundException {

        String userEmail = username.getText();
        String userPassword = password.getText();
        if (userEmail.isEmpty() || userPassword.isEmpty()) {
            // Afficher une alerte si l'email ou le mot de passe est vide
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champs vides");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez entrer votre email et votre mot de passe.");
            alert.showAndWait();
            return; // Sortir de la méthode pour éviter l'authentification avec des champs vides
        }
        Pattern pattern = Pattern.compile("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$");
        Matcher matcher = pattern.matcher(userEmail);
        if (!matcher.matches()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Format d'email incorrect");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez entrer un email valide.");
            alert.showAndWait();
            return; // Sortir de la méthode si l'email n'est pas au bon format
        }
        UserService sc =new UserService();
        String userRole = sc.authenticateUserAndGetRole(userEmail, userPassword);
        utilisateur user = sc.authenticateUserAndGetUser(userEmail,userPassword);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Authentifcation ");
        alert.setHeaderText(null);
        alert.setContentText("Login Success.");
        alert.showAndWait();
        if (userRole != null) {
            Session session = Session.getInstance();
            session.setUserEmail(userEmail);
            session.setUserRole(user.getId_role());
            session.setUserId(user.getId());
            session.setUserNom(user.getNom());
            switch (userRole) {
                case "client":
                    // Rediriger vers l'interface client
                    FXMLLoader clientLoader = new FXMLLoader(LoginController.class.getResource("/OeuvreArtistique/General/accueil.fxml"));
                    //FXMLLoader clientLoader = new FXMLLoader(LoginController.class.getResource("/Musee/Espace Client/ListeMusee.fxml"));

                    Scene clientScene = new Scene(clientLoader.load());
                    Main.stage.setScene(clientScene);
                    Main.stage.show();
                    break;
                case "artiste":
                    // Rediriger vers l'interface artiste
                    FXMLLoader artistLoader = new FXMLLoader(LoginController.class.getResource("/OeuvreArtistique/Espace Artiste/ListeOeuvresArtistiques.fxml"));
                    //FXMLLoader artistLoader = new FXMLLoader(LoginController.class.getResource("/Musee/Espace Artiste/ListeMusee.fxml"));

                    Scene artistScene = new Scene(artistLoader.load());
                    Main.stage.setScene(artistScene);
                    Main.stage.show();
                    break;

                default:
                    // Cas par défaut (ne devrait pas se produire)
                    FXMLLoader adminLoader = new FXMLLoader(LoginController.class.getResource("/OeuvreArtistique/Espace Admin/ListeOeuvresArtistiques.fxml"));
                    //FXMLLoader adminLoader = new FXMLLoader(LoginController.class.getResource("/Musee/Espace Admin/ListeMuseeAdmin.fxml"));

                    Scene adminScene = new Scene(adminLoader.load());
                    Main.stage.setScene(adminScene);
                    Main.stage.show();
                    break;
            }
        } else {
            // Authentification échouée
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Authentifcation ");
            alert1.setHeaderText(null);
            alert1.setContentText("Login Failed Verifeed.");
            alert1.showAndWait();        }
    }

    @FXML
    protected void registerButton() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("/Login/Register.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Main.stage.setScene(scene);
        Main.stage.show();
    }
}



