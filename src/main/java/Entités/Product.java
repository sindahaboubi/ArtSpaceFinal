package Entités;

public class Product {

    private String name;
    private int quantity;
    private double unitPrice;

    public Product(String name, int quantity, double unitPrice) {
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getName() {
        return name;
    }

    public int getQte() {
        return quantity;
    }

    public double getTotalPrice() {
        return quantity * unitPrice;
    }
    public double getUnitPrice() {
        return unitPrice;
    }

}
