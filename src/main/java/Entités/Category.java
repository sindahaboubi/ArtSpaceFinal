package Entités;

import java.sql.Date;

public class Category {

    private int Id_category;
    private String Name;
    private String Description;
    private Date Created_Date;
    public int getId() {
        return Id_category;
    }
    public Category() {
    }

    // Constructor without id (for insertion where id is auto-generated)
    public Category(String Name, String Description, Date Created_Date) {
        this.Name = Name;
        this.Description = Description;
        this.Created_Date = Created_Date;
    }
    // Constructor wit
// Constructor with id (for retrieval and updates)
    public Category(int Id_category, String Name, String Description , Date Created_Date) {

        this.Name = Name;
        this.Description = Description;
        this.Created_Date = Created_Date;
        this.Id_category = Id_category;

    }

    // Getters and Setters...

    @Override
    public String toString() {
        return "Category{" +
                "Id_category=" + Id_category +
                ", Name='" + Name + '\'' +
                ", Created_Date=" + Created_Date +
                ", Description=" + Description +
                '}';
    }

    // Getters
    public int getId_category() {
        return Id_category;
    }

    public String getName() {
        return Name;
    }
    public Date getCreated_Date() {
        return Created_Date;
    }

    public String getDescription() {
        return Description;
    }


    // Setters
    public void set(int Id_category) {
        this.Id_category = Id_category;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }
    public void setCreated_Date(Date Created_Date) {
        this.Created_Date = Created_Date;
    }


}
