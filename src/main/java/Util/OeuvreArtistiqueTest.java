package Util;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OeuvreArtistiqueTest extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/OeuvreArtistique/Espace Artiste/ListeOeuvresArtistiques.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/OeuvreArtistique/Espace Artiste/ListeOeuvresArtistiques.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/OeuvreArtistique/Espace Client/ListeOeuvresArtistiques.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/OeuvreArtistique/Espace Client/accueil.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/OeuvreArtistique/Espace Admin/ListeOeuvresArtistiques.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/Musee/Espace Admin/ListeMuseeAdmin.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Categorie/ListeCategorie.fxml"));

        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("ArtSpace");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
