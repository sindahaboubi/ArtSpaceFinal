package Services;

import Entités.OeuvreArtistique;

import java.sql.SQLException;
import java.util.List;

public interface IService <T> {
    void ajouterOeuvre(T t) throws SQLException;

    List<T> listerOeuvres() throws SQLException;

    List<T> listerOeuvresAdmin() throws SQLException;
    List<T> listerOeuvresATraiter() throws SQLException;
    List<T> listerOeuvresAcceptees() throws SQLException;
    List<T> listerOeuvresRefusees() throws SQLException;

    T OeuvreById(int id) throws SQLException;

    void supprimerOeuvre(T t) throws SQLException;

    void modifierOeuvre(T t) throws SQLException;

    List<OeuvreArtistique> listerOeuvresParArtiste(int idArtiste) throws SQLException;
}