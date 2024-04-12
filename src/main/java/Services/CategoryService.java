package Services;

import Entités.Category;
import Util.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class CategoryService {

    static Connection con;
    static Statement ste;
    static PreparedStatement ps;

    public CategoryService() {
        con = DataSource.getInstance().getCon();
    }

    public void addCategory(Category category) throws SQLException {
        String query = "INSERT INTO category (Name, Description,Created_Date) VALUES (?, ?,?)";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, category.getName());
            preparedStatement.setDate(3,  category.getCreated_Date());
            // Assuming datecreation is a timestamp
            preparedStatement.setString(2, category.getDescription());



            preparedStatement.executeUpdate();

            // Retrieve the generated id and set it in the Category object

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update
    public boolean updateCategory(Category category) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DataSource.getInstance().getCon();
            String query = "UPDATE category SET Name=?, Description=?, Created_Date=? WHERE Id_category=?";
            preparedStatement = connection.prepareStatement(query);
            // preparedStatement = connection.prepareStatement(query); <- Duplicate line, remove this

            // Set parameters for the prepared statement
            preparedStatement.setString(1, category.getName());
            preparedStatement.setString(2, category.getDescription());

            preparedStatement.setDate(3, category.getCreated_Date());

            preparedStatement.setInt(4, category.getId_category());

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


    // Delete

    public boolean deleteCategory(int Id_Category) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DataSource.getInstance().getCon();
            String query = "DELETE FROM category WHERE Id_category=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, Id_Category);
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





    public List<Category> getAllCategories() throws SQLException {
        List<Category> categories = new ArrayList<>();
        Connection connection = DataSource.getInstance().getCon();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM category");
        while (resultSet.next()) {
            int Id_category = resultSet.getInt("Id_category");
            String Name = resultSet.getString("Name");
            String Description = resultSet.getString("Description");
            Date Created_Date = resultSet.getDate("Created_Date");

            Category category = new Category(Id_category, Name, Description, Created_Date);
            categories.add(category);
        }
        return categories;
    }

    public static Category getCategorieById(int id) throws SQLException {
        Category category = null;
        Connection connection = DataSource.getInstance().getCon();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM category WHERE Id_category = " + id);
        if (resultSet.next()) {
            String Name = resultSet.getString("Name");
            String Description = resultSet.getString("Description");
            Date Created_Date = resultSet.getDate("Created_Date");

            category = new Category(Name, Description, Created_Date);
            category.set(id);
            category.setName(Name);
        }
        return category;
    }
    public Category getCateById(int id) throws SQLException {
      Category cat = new Category();
        Connection connection = DataSource.getInstance().getCon();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM category where Id_category="+id);
        while (resultSet.next()) {
            int Id_category = resultSet.getInt("Id_category");
            String Name = resultSet.getString("Name");
            String Description = resultSet.getString("Description");
            Date Created_Date = resultSet.getDate("Created_Date");

             cat = new Category(Id_category, Name, Description, Created_Date);

        }
        return cat;
    }

}
