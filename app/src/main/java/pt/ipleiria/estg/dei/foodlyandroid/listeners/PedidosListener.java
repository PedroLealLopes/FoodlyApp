package pt.ipleiria.estg.dei.foodlyandroid.listeners;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.foodlyandroid.modelos.Pedido;

public interface PedidosListener {
    void onRefreshListaPedidos(ArrayList<Pedido> listaPedidos);

    void onRefreshDetalhes();
}
