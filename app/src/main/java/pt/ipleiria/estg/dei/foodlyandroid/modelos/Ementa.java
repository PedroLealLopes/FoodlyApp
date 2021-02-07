package pt.ipleiria.estg.dei.foodlyandroid.modelos;

public class Ementa {
    private int orderId, dishId, restaurantId, quantity;
    private String name, type;
    private double price;

    public Ementa(int orderId, int dishId, String name, String type, double price, int restaurantId, int quantity) {
        this.orderId = orderId;
        this.dishId = dishId;
        this.name = name;
        this.type = type;
        this.price = price;
        this.restaurantId = restaurantId;
        this.quantity = quantity;
    }

    public Ementa(int dishId, String name, String type, double price, int restaurantId) {
        this.orderId = 0;
        this.dishId = dishId;
        this.name = name;
        this.type = type;
        this.price = price;
        this.restaurantId = restaurantId;
        this.quantity = 0;
    }

    @Override
    public String toString() {
        return "Ementa{" +
                "orderId=" + orderId +
                ", dishId=" + dishId +
                ", restaurantId=" + restaurantId +
                ", quantity=" + quantity +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                '}';
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
