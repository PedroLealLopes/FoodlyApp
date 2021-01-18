package pt.ipleiria.estg.dei.foodlyandroid.modelos;

public class Restaurante {
    private int restaurantId, maxPeople, currentPeople;
    private String name, image, phone, email, description, location, openingHour, closingHour, wifiPassword;
    private int allowsPets, hasVegan;

    public Restaurante(int restaurantId, int maxPeople, int currentPeople, String name, String image, String phone, String email, String description, String location, String openingHour, String closingHour, String wifiPassword, int allowsPets, int hasVegan) {
        this.restaurantId = restaurantId;
        this.maxPeople = maxPeople;
        this.currentPeople = currentPeople;
        this.name = name;
        this.image = image;
        this.phone = phone;
        this.email = email;
        this.description = description;
        this.location = location;
        this.openingHour = openingHour;
        this.closingHour = closingHour;
        this.wifiPassword = wifiPassword;
        this.allowsPets = allowsPets;
        this.hasVegan = hasVegan;
    }

    @Override
    public String toString() {
        return "Restaurante{" +
                "restaurantId=" + restaurantId +
                ", maxPeople=" + maxPeople +
                ", currentPeople=" + currentPeople +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", openingHour='" + openingHour + '\'' +
                ", closingHour='" + closingHour + '\'' +
                ", wifiPassword='" + wifiPassword + '\'' +
                ", allowsPets=" + allowsPets +
                ", hasVegan=" + hasVegan +
                '}';
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(int maxPeople) {
        this.maxPeople = maxPeople;
    }

    public int getCurrentPeople() {
        return currentPeople;
    }

    public void setCurrentPeople(int currentPeople) {
        this.currentPeople = currentPeople;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOpeningHour() {
        return openingHour;
    }

    public void setOpeningHour(String openingHour) {
        this.openingHour = openingHour;
    }

    public String getClosingHour() {
        return closingHour;
    }

    public void setClosingHour(String closingHour) {
        this.closingHour = closingHour;
    }

    public String getWifiPassword() {
        return wifiPassword;
    }

    public void setWifiPassword(String wifiPassword) {
        this.wifiPassword = wifiPassword;
    }

    public int getAllowsPets() {
        return allowsPets;
    }

    public void setAllowsPets(int allowsPets) {
        this.allowsPets = allowsPets;
    }

    public int getVegan() {
        return hasVegan;
    }

    public void setVegan(int hasVegan) {
        this.hasVegan = hasVegan;
    }
}