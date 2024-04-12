package Entités;

import java.util.Date;

public class Commentaire {

    private int id;
    private String content;
    private Date dateCreation;
    private int idOeuvre;
    private int idClient;

    public Commentaire() {
    }

    // Constructor without id (for insertion where id is auto-generated)
    public Commentaire(String content, int idOeuvre, int idClient) {
        this.content = content;
        this.idOeuvre = idOeuvre;
        this.idClient = idClient;
    }

    // Constructor with id (for retrieval and updates)
    public Commentaire(int id, String content, Date dateCreation, int idOeuvre, int idClient) {
        this.id = id;
        this.content = content;
        this.dateCreation = dateCreation;
        this.idOeuvre = idOeuvre;
        this.idClient = idClient;
    }

    // Getters and Setters...

    @Override
    public String toString() {
        return "Commentaire{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", dateCreation=" + dateCreation +
                ", idOeuvre=" + idOeuvre +
                ", idClient=" + idClient +
                '}';
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public int getIdOeuvre() {
        return idOeuvre;
    }

    public int getIdClient() {
        return idClient;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public void setIdOeuvre(int idOeuvre) {
        this.idOeuvre = idOeuvre;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }
}
