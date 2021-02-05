package pt.ipleiria.estg.dei.foodlyandroid.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.foodlyandroid.modelos.Review;

public class ReviewJsonParser {

    public static ArrayList<Review> parserJsonReviews(JSONArray response) {
        ArrayList<Review> reviews = new ArrayList<>();
        if (response != null) {
            for (int i = 0; i < response.length(); i++) {
                try {
                    JSONObject review = (JSONObject) response.get(i);

                    int restaurantId = review.getInt("restaurant_restaurantId");
                    int profileId = review.getInt("profiles_userId");
                    double stars = review.getDouble("stars");
                    String comment = review.getString("comment");
                    String creation_date = review.getString("creation_date");
                    String username = review.getString("username");
                    String image = review.getString("image");

                    Review r = new Review(restaurantId, profileId, stars, comment, creation_date, username, image);
                    reviews.add(r);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return reviews;
    }
}
