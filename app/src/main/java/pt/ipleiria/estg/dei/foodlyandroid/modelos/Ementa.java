package pt.ipleiria.estg.dei.foodlyandroid.modelos;

public class Ementa {
    private int dishId, restaurantId, quantity;
    private String name, type;
    private double price;

    public Ementa(int dishId, String name, String type, double price, int restaurantId, int quantity) {
        this.dishId = dishId;
        this.name = name;
        this.type = type;
        this.price = price;
        this.restaurantId = restaurantId;
        this.quantity = quantity;
    }

    public Ementa(int dishId, String name, String type, double price, int restaurantId) {
        this.dishId = dishId;
        this.name = name;
        this.type = type;
        this.price = price;
        this.restaurantId = restaurantId;
        this.quantity = 0;
    }

    public int getDishId() {
        return dishId;
    }


    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
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
