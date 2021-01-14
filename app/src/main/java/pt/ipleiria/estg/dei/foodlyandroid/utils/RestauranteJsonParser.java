package pt.ipleiria.estg.dei.foodlyandroid.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.foodlyandroid.modelos.Restaurante;

public class RestauranteJsonParser {

    public static ArrayList<Restaurante> parserJsonRestaurantes(JSONArray response) {
        ArrayList<Restaurante> restaurantes = new ArrayList<>();
        if (response != null) {
            for (int i = 0; i < response.length(); i++) {
                try {
                    JSONObject restaurante = (JSONObject) response.get(i);
                    int restaurantId = restaurante.getInt("restaurantId");
                    int maxPeople = restaurante.getInt("maxPeople");
                    int currentPeople = restaurante.getInt("currentPeople");
                    String name = restaurante.getString("name");
                    String image = restaurante.getString("image");
                    String phone = restaurante.getString("phone");
                    String email = restaurante.getString("email");
                    String description = restaurante.getString("description");
                    String location = restaurante.getString("location");
                    String openingHour = restaurante.getString("openingHour");
                    String closingHour = restaurante.getString("closingHour");
                    String wifiPassword = restaurante.getString("wifiPassword");
                    int allowsPets = restaurante.getInt("allowsPets");
                    int hasVegan = restaurante.getInt("hasVegan");


                    Restaurante l = new Restaurante(restaurantId, maxPeople, currentPeople, name, image, phone, email, description, location, openingHour, closingHour, wifiPassword, allowsPets, hasVegan);
                    restaurantes.add(l);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return restaurantes;
    }

    public static Restaurante parserJsonRestaurante(String response) {
        Restaurante auxRestaurante = null;
        try {
            JSONObject restaurante = new JSONObject(response);

            int restaurantId = restaurante.getInt("restaurantId");
            int maxPeople = restaurante.getInt("maxPeople");
            int currentPeople = restaurante.getInt("currentPeople");
            String name = restaurante.getString("name");
            String image = restaurante.getString("image");
            String phone = restaurante.getString("phone");
            String email = restaurante.getString("email");
            String description = restaurante.getString("description");
            String location = restaurante.getString("location");
            String openingHour = restaurante.getString("openingHour");
            String closingHour = restaurante.getString("closingHour");
            String wifiPassword = restaurante.getString("wifiPassword");
            int allowsPets = restaurante.getInt("allowsPets");
            int hasVegan = restaurante.getInt("hasVegan");

            auxRestaurante = new Restaurante(restaurantId, maxPeople, currentPeople, name, image, phone, email, description, location, openingHour, closingHour, wifiPassword, allowsPets, hasVegan);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return auxRestaurante;
    }

    public static String parserJsonImagemRestaurante(String response) {
        String capa = null;
        try {
            JSONObject login = new JSONObject(response);
            if (login.getBoolean("success"))
                capa = login.getString("image");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return capa;
    }
}
