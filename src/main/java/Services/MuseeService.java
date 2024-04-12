package Services;

import Controllers.Login.Session;
import Entités.Musee;
import Entités.OeuvreArtistique;
import Util.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MuseeService {
    static Statement ste;
    static Connection con;

    // Create
    public void addMusee(Musee musee) throws SQLException  {
        con = DataSource.getInstance().getCon();
        ste = con.createStatement();

        String query = "INSERT INTO musee (nom, description, ville, dateDebut, dateFin, heureOuverture, heureFermeture,accept, idArtiste) VALUES (?, ?, ?, ?, ?, ?, ?,?, ?)";

            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, musee.getNom());
            preparedStatement.setString(2, musee.getDescription());
            preparedStatement.setString(3, musee.getVille());
            preparedStatement.setString(4, musee.getDateDebut());
            preparedStatement.setString(5, musee.getDateFin());
            preparedStatement.setString(6, musee.getHeureOuverture());
            preparedStatement.setString(7, musee.getHeureFermeture());
            preparedStatement.setInt(8, musee.getAccept());
            preparedStatement.setInt(9, musee.getIdArtist());

            preparedStatement.executeUpdate();

    }
    public List<Musee> getAllMuseeAdmin() {
        List<Musee> musees = new ArrayList<>();
        Connection connection = DataSource.getInstance().getCon();
        String query = "SELECT * FROM musee WHERE accept = 0";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String description = resultSet.getString("description");
                String ville = resultSet.getString("ville");
                String dateDebut = resultSet.getString("dateDebut");
                String dateFin = resultSet.getString("dateFin");
                String heureOuverture = resultSet.getString("heureOuverture");
                String heureFermeture = resultSet.getString("heureFermeture");
                int idArtiste = resultSet.getInt("idArtiste");
                int accept = resultSet.getInt("accept");
                Musee musee = new Musee(id, nom, description, ville, dateDebut, dateFin, heureOuverture, heureFermeture, idArtiste, accept);
                musees.add(musee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return musees;
    }

    // Read
    public List<Musee> getAllMusees() {
        List<Musee> musees = new ArrayList<>();
        Connection connection = DataSource.getInstance().getCon();
        String query = "SELECT * FROM musee";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String description = resultSet.getString("description");
                String ville = resultSet.getString("ville");
                String dateDebut = resultSet.getString("dateDebut");
                String dateFin = resultSet.getString("dateFin");
                String heureOuverture = resultSet.getString("heureOuverture");
                String heureFermeture = resultSet.getString("heureFermeture");
                int idArtiste = resultSet.getInt("idArtiste");
                int accept = resultSet.getInt("accept");
                Musee musee = new Musee(id, nom, description, ville, dateDebut, dateFin, heureOuverture, heureFermeture, idArtiste, accept);
                musees.add(musee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return musees;
    }
    public boolean deleteMusee(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DataSource.getInstance().getCon();
            String query = "DELETE FROM musee WHERE id=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0; // If rows were affected (deleted), return true
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false in case of an exception
        } finally {
            // Close resources in the finally block
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                // Do not close the connection here
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public List<Musee> getAllMuseeById(int id) {
        List<Musee> musees = new ArrayList<>();
        Connection connection = DataSource.getInstance().getCon();
        String query = "SELECT * FROM musee WHERE idArtiste = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id); // Set the parameter
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int museeId = resultSet.getInt("id");
                    String nom = resultSet.getString("nom");
                    String description = resultSet.getString("description");
                    String ville = resultSet.getString("ville");
                    String dateDebut = resultSet.getString("dateDebut");
                    String dateFin = resultSet.getString("dateFin");
                    String heureOuverture = resultSet.getString("heureOuverture");
                    String heureFermeture = resultSet.getString("heureFermeture");
                    int idArtiste = resultSet.getInt("idArtiste");
                    int accept = resultSet.getInt("accept");
                    Musee musee = new Musee(museeId, nom, description, ville, dateDebut, dateFin, heureOuverture, heureFermeture, idArtiste, accept);
                    musees.add(musee);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return musees;
    }

    public boolean validateMusee(int museeId) {
        Connection connection = DataSource.getInstance().getCon();
        String query = "UPDATE musee SET accept = 1 WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, museeId);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    // Method to update accept column to 2 (refuse)
    public boolean refuseMusee(int museeId) {
        Connection connection = DataSource.getInstance().getCon();
        String query = "UPDATE musee SET accept = 2 WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, museeId);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateMusee(Musee musee) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DataSource.getInstance().getCon();
            String query = "UPDATE musee SET nom=?, description=?, ville=?, dateDebut=?, dateFin=?, heureOuverture=?, heureFermeture=?, idArtiste=? , accept=? WHERE id=?";
            preparedStatement = connection.prepareStatement(query);
            // preparedStatement = connection.prepareStatement(query); <- Duplicate line, remove this

            // Set parameters for the prepared statement
            preparedStatement.setString(1, musee.getNom());
            preparedStatement.setString(2, musee.getDescription());
            preparedStatement.setString(3, musee.getVille());
            preparedStatement.setString(4, musee.getDateDebut());
            preparedStatement.setString(5, musee.getDateFin());
            preparedStatement.setString(6, musee.getHeureOuverture());
            preparedStatement.setString(7, musee.getHeureFermeture());
            preparedStatement.setInt(8, musee.getIdArtist());
            preparedStatement.setInt(9, musee.getAccept());
            preparedStatement.setInt(10, musee.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0; // If rows were affected (deleted), return true
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false in case of an exception
        } finally {
            // Close resources in the finally block
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                // Do not close the connection here
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Update




}


