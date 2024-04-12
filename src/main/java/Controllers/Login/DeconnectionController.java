package Controllers.Login;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class DeconnectionController {

    private Stage stage;

    public void deconnect(Stage stage) {
        this.stage = stage;

        // Perform disconnection logic
        clearSession(); // Clear session data

        // Redirect to login page on a separate thread to avoid blocking the UI
        new Thread(() -> {
            try {
                // Load the login page FXML
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login/Login.fxml"));
                Parent loginPage = loader.load();

                // Create a new scene for the login page
                Scene loginScene = new Scene(loginPage);

                // Switch the stage to the login scene
                Platform.runLater(() -> stage.setScene(loginScene));

            } catch (Exception e) {
                e.printStackTrace();
                // Handle loading error (e.g., display an error message)
            }
        }).start();
    }

    // Method to clear session data
    private void clearSession() {
        Session.getInstance().reset(); // Clear session data
    }
}
