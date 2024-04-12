package Entités;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

// Entité Commande
public class Commande {
    private List<Product> products;
    private int idCommande;
    private int Qte;
    private Date dateCommande;
    private int idOeuvre;
    private int idClient;

    // Constructeurs
    public Commande() {
        this.products = new ArrayList<>();
    }



    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    // Ajoutez une méthode pour définir la date de la commande à la date actuelle
    public void setDateCommandeToNow() {
        this.dateCommande = new Date();
    }

    public Commande(int idCommande, int idClient, int idOeuvre, int Qte, Date dateCommande) {
        this.idCommande = idCommande;
        this.idClient = idClient;
        this.idOeuvre = idOeuvre;
        this.Qte = Qte;
        this.dateCommande = new Date();
    }

    // Méthodes pour manipuler les produits
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public void removeProduct(Product product) {
        this.products.remove(product);
    }

    // Autres méthodes de la classe Commande

    // Getters et Setters
    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdOeuvre() {
        return idOeuvre;
    }

    public void setIdOeuvre(int idOeuvre) {
        this.idOeuvre = idOeuvre;
    }

    public int getQte() {
        return Qte;
    }

    public void setQte(int Qte) {
        this.Qte = Qte;
    }


}
