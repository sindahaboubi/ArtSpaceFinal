package Util;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class CommandeTest extends Application {

    public static void main(String[] args) {
        launch(args); }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file

       //FXMLLoader loader = new FXMLLoader(getClass().getResource("/OeuvreArtistique/Espace Client/AjouterCommande.fxml"));
      // FXMLLoader loader = new FXMLLoader(getClass().getResource("/OeuvreArtistique/Espace Client/ConsulterCommande.fxml"));
    //    FXMLLoader loader = new FXMLLoader(getClass().getResource("/OeuvreArtistique/Espace Admin/TraiterCommande.fxml"));
       //FXMLLoader loader = new FXMLLoader(getClass().getResource("/OeuvreArtistique/Espace Artiste/ListeOeuvresArtistiques.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/OeuvreArtistique/Espace Artiste/ListeOeuvresArtistiques.fxml"));
      //  FXMLLoader loader = new FXMLLoader(getClass().getResource("/Musee/Espace Artiste/ListeMusee.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/OeuvreArtistique/Espace Client/ListeOeuvresArtistiques.fxml"));

        Parent root = loader.load();

        // Set up the scene
        Scene scene = new Scene(root);
        primaryStage.setTitle("ArtSpace");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
