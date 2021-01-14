package pt.ipleiria.estg.dei.foodlyandroid.listeners;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.foodlyandroid.modelos.Ementa;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.Restaurante;

public interface EmentasListener {
    void onRefreshListaEmentas(ArrayList<Ementa> listaEmentas);
}
