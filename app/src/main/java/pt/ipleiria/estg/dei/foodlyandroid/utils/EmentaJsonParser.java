package pt.ipleiria.estg.dei.foodlyandroid.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.foodlyandroid.modelos.Ementa;

public class EmentaJsonParser {

    public static ArrayList<Ementa> parserJsonEmentas(JSONArray response, int restaurantId) {
        ArrayList<Ementa> ementas = new ArrayList<>();
        if (response != null) {
            for (int i = 0; i < response.length(); i++) {
                try {
                    JSONObject ementa = (JSONObject) response.get(i);

                    int dishId = ementa.getInt("dishId");
                    String name = ementa.getString("name");
                    String type = ementa.getString("type");
                    double price = ementa.getDouble("price");

                    Ementa e = new Ementa(dishId, name, type, price, restaurantId);
                    ementas.add(e);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return ementas;
    }
}
