package pt.ipleiria.estg.dei.foodlyandroid.modelos;

public class Review {
    private int restaurantId, profileId;
    private double stars;
    private String comment, creation_date;

    public Review(int restaurantId, int profileId, double stars, String comment, String creation_date) {
        this.restaurantId = restaurantId;
        this.profileId = profileId;
        this.stars = stars;
        this.comment = comment;
        this.creation_date = creation_date;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public double getStars() {
        return stars;
    }

    public void setStars(double stars) {
        this.stars = stars;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }
}
