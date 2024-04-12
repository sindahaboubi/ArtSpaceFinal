package Entités;


public class Musee {
    private int id;

    public Musee() {
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Musee{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", ville='" + ville + '\'' +
                ", dateDebut='" + dateDebut + '\'' +
                ", dateFin='" + dateFin + '\'' +
                ", heureOuverture='" + heureOuverture + '\'' +
                ", heureFermeture='" + heureFermeture + '\'' +
                ", accept='" + accept + '\'' +
                ", idArtist=" + idArtist +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public String getHeureOuverture() {
        return heureOuverture;
    }

    public void setHeureOuverture(String heureOuverture) {
        this.heureOuverture = heureOuverture;
    }

    public String getHeureFermeture() {
        return heureFermeture;
    }

    public void setHeureFermeture(String heureFermeture) {
        this.heureFermeture = heureFermeture;
    }

    public int getIdArtist() {
        return idArtist;
    }

    public void setIdArtist(int idArtist) {
        this.idArtist = idArtist;
    }

    public int getAccept() {
        return accept;
    }

    public void setAccept(int accept) {
        this.accept = accept;
    }

    public Musee(String nom, String description, String ville, String dateDebut, String dateFin, String heureOuverture, String heureFermeture, int idArtist,int accept) {
        this.nom = nom;
        this.description = description;
        this.ville = ville;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.heureOuverture = heureOuverture;
        this.heureFermeture = heureFermeture;
        this.idArtist = idArtist;
        this.accept = accept;
    }

    private String nom;

    private String description;

    private String ville;
    private String dateDebut;
    private String dateFin;
    private String heureOuverture;
    private String heureFermeture;
    private int idArtist;
    private int accept;

    // Constructors

    public Musee(int id, String nom, String description, String ville, String dateDebut, String dateFin, String heureOuverture, String heureFermeture, int idArtist,int accept) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.ville = ville;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.heureOuverture = heureOuverture;
        this.heureFermeture = heureFermeture;
        this.idArtist = idArtist;
        this.accept = accept;
    }
}
