package Services;

import Entités.Commande;
import Util.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommandeService {

    // Connection et PreparedStatement au lieu de Statement pour la sécurité et l'efficacité
    static Connection con;
    static PreparedStatement ps;

    // Ajouter une commande
    public void ajouterCommande(Commande commande) throws SQLException {
        try {
            con = DataSource.getInstance().getCon();
            String req = "INSERT INTO Commande(idCommande, idClient, idOeuvre, Qte, dateCommande) VALUES (?, ?, ?, ?, NOW())";
            ps = con.prepareStatement(req);
            ps.setInt(1, commande.getIdCommande());
            ps.setInt(2, commande.getIdClient());
            ps.setInt(3, commande.getIdOeuvre());
            ps.setInt(4, commande.getQte());
            ps.executeUpdate();
            System.out.println("Commande ajoutée avec succès !");
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de l'ajout de la commande: " + e.getMessage());
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    // Supprimer une commande
    public void supprimerCommande(int idCommande) throws SQLException {
        try {
            con = DataSource.getInstance().getCon();
            String req = "DELETE FROM Commande WHERE idCommande=?";
            ps = con.prepareStatement(req);
            ps.setInt(1, idCommande);
            ps.executeUpdate();
            System.out.println("Commande supprimée avec succès !");
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la suppression de la commande: " + e.getMessage());
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    // Obtenir toutes les commandes
    public List<Commande> getAllCommandes() throws SQLException {
        List<Commande> commandes = new ArrayList<>();
        ResultSet rs = null;
        try {
            con = DataSource.getInstance().getCon();
            String req = "SELECT * FROM Commande";
            ps = con.prepareStatement(req);
            rs = ps.executeQuery();
            while (rs.next()) {
                Commande commande = new Commande();
                commande.setIdCommande(rs.getInt("idCommande"));
                commande.setIdClient(rs.getInt("idClient"));
                commande.setIdOeuvre(rs.getInt("idOeuvre"));
                commande.setQte(rs.getInt("Qte"));

                commandes.add(commande);
            }
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la récupération des commandes: " + e.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
        return commandes;
    }
}
