package Services;

import Entités.Commentaire;
import Entités.OeuvreArtistique;
import Util.DataSource;
import java.time.LocalDateTime;
import java.sql.Timestamp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentaireService {

    static Statement ste;
    static Connection con;

    // Create
    // Create

    public void ajouterCommentaire(Commentaire commentaire) throws SQLException {
        try {
            con = DataSource.getInstance().getCon();
            ste = con.createStatement();
            String req = "INSERT INTO commentaire(content, idOeuvre, idClient, dateCreation) VALUES ('" +
                    commentaire.getContent() + "', " +
                    commentaire.getIdOeuvre() + ", " +
                    commentaire.getIdClient() + ", NOW()" +
                    ")";

            ste.executeUpdate(req);
            System.out.println("Commentaire ajouté avec succès !");
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de l'ajout du commentaire: " + e.getMessage());
        }
    }


    // Read
    public List<Commentaire> getAllCommentaires() throws SQLException {
        List<Commentaire> commentaires = new ArrayList<>();
        String query = "SELECT * FROM commentaire";
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Commentaire commentaire = new Commentaire(
                        resultSet.getInt("id"),
                        resultSet.getString("content"),
                        resultSet.getTimestamp("datecreation"),
                        resultSet.getInt("idoeuvre"),
                        resultSet.getInt("idclient")
                );
                commentaires.add(commentaire);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commentaires;
    }

    //Delete
    public void supprimerCommentaire(Commentaire commentaire , int id) {
        String query = "DELETE FROM commentaire WHERE id="+id ;
        try {
            try (Connection conn = DataSource.getInstance().getCon();
                 PreparedStatement pstmt = conn.prepareStatement(query)) {
//                pstmt.setInt(1, commentaire.getId());
                if (pstmt.executeUpdate() == 0) {
                    System.out.println("Le commentaire avec l'ID " + id + " n'existe pas dans la base de données.");
                } else {
                    System.out.println("Le commentaire avec l'ID " + id + " a été supprimé avec succès.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression du commentaire : " + e.getMessage());
        }
    }

    public void modifierCommentaire(Commentaire commentaire, int id) {
        String query = "UPDATE commentaire SET content=?, idClient=?, idOeuvre=?, dateCreation=? WHERE id=?";
        try {
            Connection conn = DataSource.getInstance().getCon();
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, commentaire.getContent());
                pstmt.setInt(2, commentaire.getIdClient());
                pstmt.setInt(3, commentaire.getIdOeuvre());
                pstmt.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
                pstmt.setInt(5, id); // Utilisez l'ID passé en paramètre ici
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected == 0) {
                    System.out.println("Le commentaire avec l'ID " + id + " n'existe pas dans la base de données.");
                } else {
                    System.out.println("Le commentaire avec l'ID " + id + " a été modifié avec succès.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour du commentaire : " + e.getMessage());
        }
        }



    public List<Commentaire> listeCommentaireByIdOeuvre(int idOeuvre) throws SQLException {
        List<Commentaire> commentaires = new ArrayList<>();
        Connection connection = DataSource.getInstance().getCon();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT * FROM commentaire WHERE idOeuvre = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idOeuvre);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String content = resultSet.getString("content");
                java.util.Date datecreation = resultSet.getDate("datecreation");
                int idclient = resultSet.getInt("idclient");
                Commentaire commentaire = new Commentaire(id, content, datecreation, idOeuvre, idclient);
                commentaires.add(commentaire);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }
        return commentaires;


    }

     public Commentaire getCommentaireById(int id) {
        Commentaire commentaire = null;
        String query = "SELECT * FROM commentaire WHERE id = " + id;
        try {
            Connection conn = DataSource.getInstance().getCon();
            PreparedStatement pstmt = conn.prepareStatement(query);
//            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int commentId = rs.getInt("id");
                    String content = rs.getString("content");
                    java.util.Date dateCreation = rs.getDate("dateCreation");
                    int idOeuvre = rs.getInt("idOeuvre");
                    int idClient = rs.getInt("idClient");
                    commentaire = new Commentaire(commentId, content, dateCreation, idOeuvre, idClient);
                } else {
                    System.out.println("Aucun commentaire trouvé pour l'ID : " + id);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération du commentaire : " + e.getMessage());
        }
        return commentaire;
    }


}



