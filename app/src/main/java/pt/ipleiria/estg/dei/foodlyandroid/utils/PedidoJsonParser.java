package pt.ipleiria.estg.dei.foodlyandroid.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.foodlyandroid.modelos.Ementa;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.Pedido;

public class PedidoJsonParser {

    public static ArrayList<Pedido> parserJsonPedidos(JSONArray response) {
        ArrayList<Pedido> pedidos = new ArrayList<>();
        if (response != null) {
            for (int i = 0; i < response.length(); i++) {
                try {
                    JSONObject pedido = (JSONObject) response.get(i);

                    int orderId = pedido.getInt("orderId");
                    String date = pedido.getString("date");
                    int userId = pedido.getInt("userId");

                    Pedido p = new Pedido(orderId, date, userId);
                    pedidos.add(p);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return pedidos;
    }
}
