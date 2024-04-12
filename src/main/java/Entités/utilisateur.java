package Entités;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.sql.JDBCType.NULL;

public class utilisateur {
    private int id;
    private String nom;
    private String prenom;
    private LocalDate date_naiss;
    private String tel ;
    private String email;
    private String adresse;
    private int id_role;
    private String password;

    public utilisateur(String nom, String prenom, LocalDate date_naiss, String tel, String email, String adresse, int id_role, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.date_naiss = date_naiss;
        this.tel = tel;
        this.email = email;
        this.adresse = adresse;
        this.id_role = id_role;
        this.password = password;
    }

    public utilisateur(String nom, String prenom, Date LocalDate, String tel, String email, String adresse, int id_role, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.date_naiss = date_naiss;
        this.tel = tel;
        this.email = email;
        this.adresse = adresse;
        this.id_role = id_role;
        this.password = password;
    }

    public utilisateur() {

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public utilisateur(int id, String nom, String prenom, LocalDate date_naiss, String tel, String email, String adresse, int id_role) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.date_naiss = date_naiss;
        this.tel = tel;
        this.email = email;
        this.adresse = adresse;
        this.id_role = id_role;
    }

    public utilisateur(String nom, String prenom, LocalDate date_naiss, String tel, String email, String adresse, int id_role) {
        this.nom = nom;
        this.prenom = prenom;
        this.date_naiss = date_naiss;
        this.tel = tel;
        this.email = email;
        this.adresse = adresse;
        this.id_role = id_role;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public LocalDate getDate_naiss() {
        return date_naiss;
    }

    public String getTel() {
        return tel;
    }

    public String getEmail() {
        return email;
    }

    public String getAdresse() {
        return adresse;
    }

    public int getId_role() {
        return id_role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDate_naiss(LocalDate date_naiss) {
        this.date_naiss = date_naiss;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setId_role(int id_role) {
        this.id_role = id_role;
    }

    @Override
    public String toString() {
        return "utilisateur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", date_naiss=" + date_naiss +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", adresse='" + adresse + '\'' +
                ", id_role=" + id_role +
                '}';
    }
}
