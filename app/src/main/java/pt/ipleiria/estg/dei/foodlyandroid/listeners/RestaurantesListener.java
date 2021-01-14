package pt.ipleiria.estg.dei.foodlyandroid.listeners;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.foodlyandroid.modelos.Restaurante;

public interface RestaurantesListener {
    void onRefreshListaRestaurantes(ArrayList<Restaurante> listaRestaurantes);

    void onRefreshDetalhes();
}
