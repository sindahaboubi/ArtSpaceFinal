package Controllers.Categorie;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import Entités.Category;
import Services.CategoryService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class ListeCategorie {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button BoutonAjouter;

    @FXML
    private TableView<Category> TableCategorie;

    @FXML
    private VBox carteContainer;

    @FXML
    private TableColumn<Category, Date> dateCreationColumn;

    @FXML
    private TableColumn<Category, String> descriptionColumn;

    @FXML
    private TableColumn<Category, String> nomColumn;
    public static int idCat = 0;
    @FXML
    private ScrollPane scrollPane;

    private final ObservableList<Category> catData = FXCollections.observableArrayList();
    @FXML
    private void initialize() throws SQLException {
        CategoryService categoryService = new CategoryService();
        List<Category> categories = categoryService.getAllCategories();


        catData.clear();
        catData.addAll(categories);
        TableCategorie.setItems(catData);
        nomColumn.setCellValueFactory
                (
                        new PropertyValueFactory<>("Name")
                );
        dateCreationColumn.setCellValueFactory
                (
                        new PropertyValueFactory<>("Created_Date")
                );
        descriptionColumn.setCellValueFactory
                (
                        new PropertyValueFactory<>("Description")
                );

    }

    @FXML
    void details(ActionEvent event) {

        idCat =TableCategorie.getSelectionModel().getSelectedItem().getId_category();
        Parent root;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Categorie/DetailsCategorie.fxml")));
            Stage myWindow = new Stage();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("Statistique");
            //myWindow.setFullScreen(true);
            myWindow.show();
        } catch (IOException ex) {
            Logger.getLogger(ListeCategorie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void handleAjouterCategorie(ActionEvent event) {
        try {
            // Load the FXML file for the new scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Categorie/AjouterCategorie.fxml"));
            Parent root = loader.load();

            // Create a new Scene
            Scene scene = new Scene(root);

            // Get the Stage from the current event
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new Scene
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleTableViewClick(MouseEvent event) throws IOException {
        if (event.getClickCount() == 2) {
            // Double-click detected
            Category selectedCategory = TableCategorie.getSelectionModel().getSelectedItem();
            if (selectedCategory != null) {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Categorie/DetailsCategorie.fxml"));
                Parent root = loader.load();
                DetailsCategorie controller = loader.getController();


                controller.populateFields(selectedCategory);
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }
        }
    }


    @FXML
    void initial() {
        assert BoutonAjouter != null : "fx:id=\"BoutonAjouter\" was not injected: check your FXML file 'ListeCategorie.fxml'.";
        assert TableCategorie != null : "fx:id=\"TableCategorie\" was not injected: check your FXML file 'ListeCategorie.fxml'.";
        assert carteContainer != null : "fx:id=\"carteContainer\" was not injected: check your FXML file 'ListeCategorie.fxml'.";
        assert dateCreationColumn != null : "fx:id=\"dateCreationColumn\" was not injected: check your FXML file 'ListeCategorie.fxml'.";
        assert descriptionColumn != null : "fx:id=\"descriptionColumn\" was not injected: check your FXML file 'ListeCategorie.fxml'.";
        assert nomColumn != null : "fx:id=\"nomColumn\" was not injected: check your FXML file 'ListeCategorie.fxml'.";
        assert scrollPane != null : "fx:id=\"scrollPane\" was not injected: check your FXML file 'ListeCategorie.fxml'.";

    }

}

