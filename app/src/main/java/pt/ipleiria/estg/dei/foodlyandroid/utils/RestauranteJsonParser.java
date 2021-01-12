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

                    int id = restaurante.getInt("id");
                    int maxPessoas = restaurante.getInt("maxPessoas");
                    int currPessoas = restaurante.getInt("currPessoas");
                    String capa = restaurante.getString("capa");
                    String descricao = restaurante.getString("descricao");
                    String localizacao = restaurante.getString("localizacao");
                    String horaAbertura = restaurante.getString("horaAbertura");
                    String horaFecho = restaurante.getString("horaFecho");
                    String wifi = restaurante.getString("wifi");
                    boolean animais = restaurante.getBoolean("animais");
                    boolean vegetariano = restaurante.getBoolean("vegetariano");

                    Restaurante l = new Restaurante(id, maxPessoas, currPessoas, capa, descricao, localizacao, horaAbertura, horaFecho, wifi, animais, vegetariano);
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

            int id = restaurante.getInt("id");
            int maxPessoas = restaurante.getInt("maxPessoas");
            int currPessoas = restaurante.getInt("currPessoas");
            String capa = restaurante.getString("capa");
            String descricao = restaurante.getString("descricao");
            String localizacao = restaurante.getString("localizacao");
            String horaAbertura = restaurante.getString("horaAbertura");
            String horaFecho = restaurante.getString("horaFecho");
            String wifi = restaurante.getString("wifi");
            boolean animais = restaurante.getBoolean("animais");
            boolean vegetariano = restaurante.getBoolean("vegetariano");

            auxRestaurante = new Restaurante(id, maxPessoas, currPessoas, capa, descricao, localizacao, horaAbertura, horaFecho, wifi, animais, vegetariano);
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
                capa = login.getString("capa");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return capa;
    }
}
