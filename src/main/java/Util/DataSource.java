package Util;

import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
    //private   String url="jdbc:mysql://localhost:3306/artspace";
    private   String url="jdbc:mysql://localhost:8111/ArtSpace";

    private String login="root";
    private String pwd="";
    private static DataSource data;

    private Connection con;
    private DataSource(){

        try {
            con= (Connection) DriverManager.getConnection(url,login,pwd);
            System.out.println("Connexion établie");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public Connection getCon(){
        return con;
    }


    public static DataSource getInstance()
    {
        if (data==null)
            data=new DataSource();
        return data;
    }

    public static Connection getConnection() {
        return null;
    }

    public void closeConnection() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
                System.out.println("Connexion fermée");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
        }
    }
}
