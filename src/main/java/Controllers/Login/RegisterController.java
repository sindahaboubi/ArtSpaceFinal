package Controllers.Login;

import Entités.utilisateur;
import Services.UserService;
import Util.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.net.URL;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.sun.javafx.css.FontFaceImpl.FontFaceSrcType.URL;

public class RegisterController  implements Initializable {
    @FXML
    private ComboBox<String> role;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField confirmPassword;
    @FXML
    private DatePicker birthday ;
    @FXML
    private   TextField firstName;
    @FXML
    private   TextField lastName;
    @FXML
    private   TextField email;
    @FXML
    private   TextField phone;
    @FXML
    private   TextField address;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> roles = FXCollections.observableArrayList("client", "artiste");
        role.setItems(roles);
    }

    @FXML
    protected void backToLoginPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/Login/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Main.stage.setScene(scene);
        Main.stage.show();
    }
    @FXML
    protected void afterRegister(ActionEvent event) throws IOException, SQLException {
        String errorMessage = "";

        if (confirmPassword.getText().isEmpty() ||
                password.getText().isEmpty() ||
                firstName.getText().isEmpty() ||
                lastName.getText().isEmpty() ||
                phone.getText().isEmpty()) {
            errorMessage = "Veuillez remplir tous les champs.";
        }

        if (!isValidEmail(email.getText()) && errorMessage.isEmpty()) {
            errorMessage = "Saisir un email valide.";
        } else if (!isValidEmail(email.getText())) {
            errorMessage += "\nSaisir un email valide.";  // Append if other errors exist
        }

        if (!errorMessage.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champs vides ou email invalide");
            alert.setHeaderText(null);
            alert.setContentText(errorMessage);
            alert.showAndWait();

        } else  if (!confirmPassword.getText().equals(password.getText())) {
            // Afficher une alerte si les mots de passe ne correspondent pas
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Mots de passe non correspondants");
            alert.setHeaderText(null);
            alert.setContentText("Les mots de passe ne correspondent pas.");
            alert.showAndWait();
        } else {
            LocalDate selectedDate = birthday.getValue();


            // Si tous les champs sont remplis et les mots de passe correspondent, procéder à la création de l'utilisateur
            String selectedRole = role.getValue();
            int id_role=0;
            if(selectedRole.equals("client")){
                id_role=1;
            }
            else
                id_role=2;
            utilisateur user=new utilisateur(firstName.getText(), lastName.getText(), selectedDate, phone.getText(),email.getText(), address.getText(),  id_role , password.getText());
            UserService sc =new UserService();
            sc.ajout_user(user);

            // Afficher une alerte de succès
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Utilisateur créé avec succès");
            successAlert.setHeaderText(null);
            successAlert.setContentText("L'utilisateur a été créé avec succès.");
            successAlert.showAndWait();
             FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/Login/Login.fxml"));
            Scene clientScene = new Scene(fxmlLoader.load());
            Main.stage.setScene(clientScene);
            Main.stage.show();

            // Rediriger ou effectuer toute autre action nécessaire après la création de l'utilisateur
            // Par exemple, rediriger vers une autre vue...
        }
    }
    private static boolean isValidEmail(String email) {
        String regex = "^[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$";
        return email.matches(regex);

    }

}