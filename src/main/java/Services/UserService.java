package Services;

import Entités.utilisateur;
import Util.DataSource;

import java.sql.*;
import java.time.LocalDate;

public class UserService {
    static Statement ste;
    static Connection con;





    public void ajout_user(utilisateur user) throws SQLException {
        try {
            con = DataSource.getInstance().getCon();

            // Utiliser une requête préparée avec des paramètres
            String req = "INSERT INTO utilisateur (nom, prenom, date_naiss, tel, email, adresse, id_role, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(req);

            // Définir les valeurs des paramètres
            ps.setString(1, user.getNom());
            ps.setString(2, user.getPrenom());
            LocalDate localDate = user.getDate_naiss();
            Date sqlDate = Date.valueOf(localDate);
            ps.setDate(3, sqlDate);
            ps.setString(4, user.getTel());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getAdresse());
            ps.setInt(7, user.getId_role());
            ps.setString(8, user.getPassword());

            // Exécuter la requête
            ps.executeUpdate();

            System.out.println("personne ajoutée");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean authenticateUser(String email, String password) {
        String sql = "SELECT COUNT(*) FROM utilisateur WHERE email = ? AND password = ?";
        try {
            con = DataSource.getInstance().getCon();

             PreparedStatement statement = con.prepareStatement(sql) ;
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count == 1; // Si un enregistrement correspond, l'authentification est réussie
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // En cas d'erreur ou si aucun enregistrement ne correspond, retourner false
    }

    public String authenticateUserAndGetRole(String email, String password) {
        String role = null;
        String sql = "SELECT r.name FROM utilisateur u JOIN role r ON u.id_role = r.id WHERE u.email = ? AND u.password = ?";
        try  {
            con = DataSource.getInstance().getCon();

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                role = resultSet.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }
    public utilisateur authenticateUserAndGetUser(String email, String password) {
        utilisateur user = null;
        String sql = "SELECT * FROM utilisateur u JOIN role r ON u.id_role = r.id WHERE u.email = ? AND u.password = ?";
        try {
            con = DataSource.getInstance().getCon();

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new utilisateur();
                // Set user attributes from the result set
                user.setId(resultSet.getInt("id"));
                user.setNom(resultSet.getString("nom"));
                user.setPrenom(resultSet.getString("prenom"));
                user.setDate_naiss(resultSet.getDate("date_naiss").toLocalDate());
                user.setTel(resultSet.getString("tel"));
                user.setEmail(resultSet.getString("email"));
                user.setAdresse(resultSet.getString("adresse"));
                user.setId_role(resultSet.getInt("id_role"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }



}
