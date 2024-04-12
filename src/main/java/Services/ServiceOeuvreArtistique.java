package Services;

import Entités.OeuvreArtistique;
import Util.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServiceOeuvreArtistique implements IService<OeuvreArtistique> {
    static Statement ste;
    static Connection con;
    @Override
    public void ajouterOeuvre(OeuvreArtistique oeuvreArtistique) throws SQLException {
        try {
            con = DataSource.getInstance().getCon();
            ste = con.createStatement();
            //Image type BIG BLOP
            String req = "INSERT INTO oeuvreArtistique(titre, description, prix, dateCreation, etat, idArtiste, idCategorie, idMusee, acceptation, image) VALUES (?, ?, ?, NOW(), ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(req);
            pstmt.setString(1, oeuvreArtistique.getTitre());
            pstmt.setString(2, oeuvreArtistique.getDescription());
            pstmt.setFloat(3, oeuvreArtistique.getPrix());
            pstmt.setInt(4, oeuvreArtistique.getEtat());
            pstmt.setInt(5, oeuvreArtistique.getIdArtiste());
            pstmt.setInt(6, oeuvreArtistique.getIdCategorie());
            pstmt.setInt(7, oeuvreArtistique.getIdMusee());
            pstmt.setInt(8, oeuvreArtistique.getAcceptation());
            pstmt.setBytes(9, oeuvreArtistique.getImage());
            pstmt.executeUpdate();
            System.out.println("Oeuvre artistique ajoutée avec succès !");
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de l'ajout de l'oeuvre artistique: " + e.getMessage());
        }
    }

    @Override
    public List<OeuvreArtistique> listerOeuvres() throws SQLException {
        List<OeuvreArtistique> oeuvres = new ArrayList<>();
        Connection connection = DataSource.getInstance().getCon();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM oeuvreArtistique WHERE acceptation = 1");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String titre = resultSet.getString("titre");
            String description = resultSet.getString("description");
            float prix = resultSet.getFloat("prix");
            int etat = resultSet.getInt("etat");
            int idArtiste = resultSet.getInt("idArtiste");
            int idCategorie = resultSet.getInt("idCategorie");
            Date dateCreation = resultSet.getDate("dateCreation");
            int idMusee = resultSet.getInt("idMusee");
            int acceptation = resultSet.getInt("acceptation");
            Blob imageBlob = resultSet.getBlob("image");
            byte[] imageBytes = null;
            if (imageBlob != null) {
                imageBytes = imageBlob.getBytes(1, (int) imageBlob.length());
            }
            OeuvreArtistique oeuvre = new OeuvreArtistique(id, titre, description, prix, dateCreation, etat, idArtiste, idCategorie, idMusee, acceptation, imageBytes);
            oeuvres.add(oeuvre);
        }
        return oeuvres;
    }

    @Override
    public List<OeuvreArtistique> listerOeuvresAdmin() throws SQLException {
        List<OeuvreArtistique> oeuvres = new ArrayList<>();
        Connection connection = DataSource.getInstance().getCon();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM oeuvreArtistique");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String titre = resultSet.getString("titre");
            String description = resultSet.getString("description");
            float prix = resultSet.getFloat("prix");
            int etat = resultSet.getInt("etat");
            int idArtiste = resultSet.getInt("idArtiste");
            int idCategorie = resultSet.getInt("idCategorie");
            Date dateCreation = resultSet.getDate("dateCreation");
            int idMusee = resultSet.getInt("idMusee");
            int acceptation = resultSet.getInt("acceptation");
            Blob imageBlob = resultSet.getBlob("image");
            byte[] imageBytes = null;
            if (imageBlob != null) {
                imageBytes = imageBlob.getBytes(1, (int) imageBlob.length());
            }
            OeuvreArtistique oeuvre = new OeuvreArtistique(id, titre, description, prix, dateCreation, etat, idArtiste, idCategorie, idMusee, acceptation, imageBytes);
            oeuvres.add(oeuvre);
        }
        return oeuvres;
    }

    @Override
    public List<OeuvreArtistique> listerOeuvresATraiter() throws SQLException {
        List<OeuvreArtistique> oeuvres = new ArrayList<>();
        Connection connection = DataSource.getInstance().getCon();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM oeuvreArtistique where acceptation= 0");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String titre = resultSet.getString("titre");
            String description = resultSet.getString("description");
            float prix = resultSet.getFloat("prix");
            int etat = resultSet.getInt("etat");
            int idArtiste = resultSet.getInt("idArtiste");
            int idCategorie = resultSet.getInt("idCategorie");
            Date dateCreation = resultSet.getDate("dateCreation");
            int idMusee = resultSet.getInt("idMusee");
            int acceptation = resultSet.getInt("acceptation");
            Blob imageBlob = resultSet.getBlob("image");
            byte[] imageBytes = null;
            if (imageBlob != null) {
                imageBytes = imageBlob.getBytes(1, (int) imageBlob.length());
            }
            OeuvreArtistique oeuvre = new OeuvreArtistique(id, titre, description, prix, dateCreation, etat, idArtiste, idCategorie, idMusee, acceptation, imageBytes);
            oeuvres.add(oeuvre);
        }
        return oeuvres;
    }

    @Override
    public List<OeuvreArtistique> listerOeuvresAcceptees() throws SQLException {
        List<OeuvreArtistique> oeuvres = new ArrayList<>();
        Connection connection = DataSource.getInstance().getCon();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM oeuvreArtistique where acceptation= 1");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String titre = resultSet.getString("titre");
            String description = resultSet.getString("description");
            float prix = resultSet.getFloat("prix");
            int etat = resultSet.getInt("etat");
            int idArtiste = resultSet.getInt("idArtiste");
            int idCategorie = resultSet.getInt("idCategorie");
            Date dateCreation = resultSet.getDate("dateCreation");
            int idMusee = resultSet.getInt("idMusee");
            int acceptation = resultSet.getInt("acceptation");
            Blob imageBlob = resultSet.getBlob("image");
            byte[] imageBytes = null;
            if (imageBlob != null) {
                imageBytes = imageBlob.getBytes(1, (int) imageBlob.length());
            }
            OeuvreArtistique oeuvre = new OeuvreArtistique(id, titre, description, prix, dateCreation, etat, idArtiste, idCategorie, idMusee, acceptation, imageBytes);
            oeuvres.add(oeuvre);
        }
        return oeuvres;
    }

    @Override
    public List<OeuvreArtistique> listerOeuvresRefusees() throws SQLException {
        List<OeuvreArtistique> oeuvres = new ArrayList<>();
        Connection connection = DataSource.getInstance().getCon();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM oeuvreArtistique where acceptation= 2");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String titre = resultSet.getString("titre");
            String description = resultSet.getString("description");
            float prix = resultSet.getFloat("prix");
            int etat = resultSet.getInt("etat");
            int idArtiste = resultSet.getInt("idArtiste");
            int idCategorie = resultSet.getInt("idCategorie");
            Date dateCreation = resultSet.getDate("dateCreation");
            int idMusee = resultSet.getInt("idMusee");
            int acceptation = resultSet.getInt("acceptation");
            Blob imageBlob = resultSet.getBlob("image");
            byte[] imageBytes = null;
            if (imageBlob != null) {
                imageBytes = imageBlob.getBytes(1, (int) imageBlob.length());
            }
            OeuvreArtistique oeuvre = new OeuvreArtistique(id, titre, description, prix, dateCreation, etat, idArtiste, idCategorie, idMusee, acceptation, imageBytes);
            oeuvres.add(oeuvre);
        }
        return oeuvres;
    }

    @Override
    public OeuvreArtistique OeuvreById(int id) throws SQLException {
        OeuvreArtistique oeuvre = null;
        Connection connection = DataSource.getInstance().getCon();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM oeuvreArtistique WHERE id = " + id);
        if (resultSet.next()) {
            String titre = resultSet.getString("titre");
            String description = resultSet.getString("description");
            float prix = resultSet.getFloat("prix");
            int etat = resultSet.getInt("etat");
            int idArtiste = resultSet.getInt("idArtiste");
            int idCategorie = resultSet.getInt("idCategorie");
            Date dateCreation = resultSet.getDate("dateCreation");
            int idMusee = resultSet.getInt("idMusee");
            int acceptation = resultSet.getInt("acceptation");
            Blob imageBlob = resultSet.getBlob("image");
            byte[] imageBytes = null;
            if (imageBlob != null) {
                imageBytes = imageBlob.getBytes(1, (int) imageBlob.length());
            }
            oeuvre = new OeuvreArtistique(id, titre, description, prix, dateCreation, etat, idArtiste, idCategorie, idMusee, acceptation, imageBytes);
        }
        return oeuvre;
    }

    @Override
    public void supprimerOeuvre(OeuvreArtistique oeuvreArtistique) throws SQLException {
        Connection connection = DataSource.getInstance().getCon();
        Statement statement = connection.createStatement();
        String query = "DELETE FROM oeuvreArtistique WHERE id = " + oeuvreArtistique.getId();
        int rowsAffected = statement.executeUpdate(query);
        statement.close();
        if (rowsAffected == 0) {
            System.out.println("L'oeuvre avec l'ID " + oeuvreArtistique.getId() + " n'existe pas dans la base de données.");
        } else {
            System.out.println("L'oeuvre avec l'ID " + oeuvreArtistique.getId() + " a été supprimée avec succès.");
        }
    }

    public void modifierOeuvre(OeuvreArtistique oeuvreArtistique) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DataSource.getInstance().getCon();
            String query = "UPDATE oeuvreArtistique SET titre = ?, description = ?, prix = ?, dateCreation = ?, etat = ?, idArtiste = ?, idCategorie = ?, idMusee = ?, acceptation = ?, image = ? WHERE id = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, oeuvreArtistique.getTitre());
            pstmt.setString(2, oeuvreArtistique.getDescription());
            pstmt.setFloat(3, oeuvreArtistique.getPrix());
            pstmt.setDate(4, new java.sql.Date(oeuvreArtistique.getDateCreation().getTime()));
            pstmt.setInt(5, oeuvreArtistique.getEtat());
            pstmt.setInt(6, oeuvreArtistique.getIdArtiste());
            pstmt.setInt(7, oeuvreArtistique.getIdCategorie());
            pstmt.setInt(8, oeuvreArtistique.getIdMusee());
            pstmt.setInt(9, oeuvreArtistique.getAcceptation());
            pstmt.setBytes(10, oeuvreArtistique.getImage()); // Assurez-vous que getImage() renvoie un tableau de bytes (byte[])
            pstmt.setInt(11, oeuvreArtistique.getId());
            pstmt.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour de l'oeuvre artistique : " + e.getMessage());
        }
    }
    @Override
    public List<OeuvreArtistique> listerOeuvresParArtiste(int idArtiste) throws SQLException {
        List<OeuvreArtistique> oeuvres = new ArrayList<>();
        Connection connection = DataSource.getInstance().getCon();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT * FROM oeuvreArtistique WHERE idArtiste = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idArtiste);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String titre = resultSet.getString("titre");
                String description = resultSet.getString("description");
                float prix = resultSet.getFloat("prix");
                int etat = resultSet.getInt("etat");
                int idCategorie = resultSet.getInt("idCategorie");
                Date dateCreation = resultSet.getDate("dateCreation");
                int idMusee = resultSet.getInt("idMusee");
                int acceptation = resultSet.getInt("acceptation");
                Blob imageBlob = resultSet.getBlob("image");
                byte[] imageBytes = null;
                if (imageBlob != null) {
                    imageBytes = imageBlob.getBytes(1, (int) imageBlob.length());
                }
                OeuvreArtistique oeuvre = new OeuvreArtistique(id, titre, description, prix, dateCreation, etat, idArtiste, idCategorie, idMusee, acceptation, imageBytes);
                oeuvres.add(oeuvre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }

        }
        return oeuvres;
    }
}
