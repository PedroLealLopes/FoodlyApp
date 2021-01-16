package pt.ipleiria.estg.dei.foodlyandroid.modelos;

public class Review {
    private int restaurantId, profileId;
    private double stars;
    private String comment, creation_date, username, image;

    public Review(int restaurantId, int profileId, double stars, String comment, String creation_date, String username, String image) {
        this.restaurantId = restaurantId;
        this.profileId = profileId;
        this.stars = stars;
        this.comment = comment;
        this.creation_date = creation_date;
        this.username = username;
        this.image = image;
    }

    @Override
    public String toString() {
        return "Review{" +
                "restaurantId=" + restaurantId +
                ", profileId=" + profileId +
                ", stars=" + stars +
                ", comment='" + comment + '\'' +
                ", creation_date='" + creation_date + '\'' +
                ", username='" + username + '\'' +
                ", image='" + image + '\'' +
                '}';
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
