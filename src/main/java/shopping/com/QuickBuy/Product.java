package shopping.com.QuickBuy;

public class Product {
    private int id;
    private String name;
    private int quantity;
    private double price;

    // constructor for cart/order
    public Product(int id, String name, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    // ✅ extra constructor for simple product listing
    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = 1; // default quantity = 1
    }

    // getters & setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    // helper method
    public double getTotalPrice() {
        return this.quantity * this.price;
    }
}
 
