package pt.ipleiria.estg.dei.foodlyandroid.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.Signature;
import java.util.ArrayList;

import pt.ipleiria.estg.dei.foodlyandroid.modelos.Restaurante;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.SingletonFoodly;

public class RestauranteFavoritosJsonParser {

    public static ArrayList<Restaurante> parserJsonRestaurantes(JSONArray response) {
        ArrayList<Restaurante> restaurantes = new ArrayList<>();
        if (response != null) {
            for (int i = 0; i < response.length(); i++) {
                try {
                    JSONObject restaurante = (JSONObject) response.get(i);

                    int restaurant_restaurantId = restaurante.getInt("restaurant_restaurantId");

                    Restaurante l = new Restaurante(restaurantId, maxPeople, currentPeople, name, image, phone, email, description, location, openingHour, closingHour, wifiPassword, allowsPets, hasVegan, false);
                    restaurantes.add(l);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return restaurantes;
    }
}
