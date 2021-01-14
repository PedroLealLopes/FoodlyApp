package pt.ipleiria.estg.dei.foodlyandroid.modelos;

public class Ementa {
    private int dishId;
    private String name, type;
    private double price;

    public Ementa(int dishId, String name, String type, double price) {
        this.dishId = dishId;
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public int getDishId() {
        return dishId;
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
}
