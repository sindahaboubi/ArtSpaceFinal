package Controllers.Login;

public class Session {
    private static Session instance;
    private String userNom;
    private String userEmail;
    private int userRole;
    private int userId;

    private Session() {}

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }
    public String getUserNom() {
        return userNom;
    }

    public void setUserNom(String nom) {
        this.userNom = nom;
    }
    public int getUserId() {
        return userId;
    }

    public void setUserId(int id) {
        this.userId = id;
    }
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getUserRole() {
        return userRole;
    }

    public void setUserRole(int userRole) {
        this.userRole = userRole;
    }
    public void reset() {
        // Clear session data
        userNom = null;
        userEmail = null;
        userRole = -1;
        userId = -1;
        // Add any other session data fields to reset if needed
    }
}
