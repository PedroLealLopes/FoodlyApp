package pt.ipleiria.estg.dei.foodlyandroid.modelos;

public class RestauranteFavorito {
    private int profiles_userId, restaurant_restaurantId;

    public RestauranteFavorito(int profiles_userId, int restaurant_restaurantId) {
        this.profiles_userId = profiles_userId;
        this.restaurant_restaurantId = restaurant_restaurantId;
    }

    public int getProfiles_userId() {
        return profiles_userId;
    }

    public void setProfiles_userId(int profiles_userId) {
        this.profiles_userId = profiles_userId;
    }

    public int getRestaurant_restaurantId() {
        return restaurant_restaurantId;
    }

    public void setRestaurant_restaurantId(int restaurant_restaurantId) {
        this.restaurant_restaurantId = restaurant_restaurantId;
    }
}
