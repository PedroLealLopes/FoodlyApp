package pt.ipleiria.estg.dei.foodlyandroid.modelos;

import pt.ipleiria.estg.dei.foodlyandroid.R;

import java.util.ArrayList;

public class SingletonGestorRestaurantes {

    private static SingletonGestorRestaurantes instance = null;
    private ArrayList<Restaurante> restaurantes;

    public static synchronized SingletonGestorRestaurantes getInstance() {
        if (instance == null)
            instance = new SingletonGestorRestaurantes();
        return instance;
    }

    private SingletonGestorRestaurantes() {
        gerarFakeData();
    }

    private void gerarFakeData() {
        restaurantes = new ArrayList<>();
        restaurantes.add(new Restaurante(1, 20, 10, R.drawable.img_restaurante, "Rei das Pizzas", "Restaurante de Pizzas", "Leiria", "3",
                "07:00:00", "23:00:00", true, true));
        restaurantes.add(new Restaurante(2, 20, 10, R.drawable.img_restaurante, "Rei dos Hambuerguers", "Restaurante de Hambuerguers", "Leiria", "4",
                "07:00:00", "23:00:00", true, false));
        restaurantes.add(new Restaurante(3, 20, 10, R.drawable.img_restaurante, "Sushi", "Restaurante de Sushi", "Leiria", "1",
                "07:00:00", "23:00:00", false, true));
        restaurantes.add(new Restaurante(4, 20, 10, R.drawable.img_restaurante, "KFC", "Melhor restaurante", "Não é em Leiria", "5",
                "07:00:00", "23:00:00", true, true));
    }

    public ArrayList<Restaurante> getRestaurantes() {
        return new ArrayList<>(restaurantes);
    }

    public Restaurante getRestaurante(int id) {
        for (Restaurante l : restaurantes)
            if (l.getId() == id)
                return l;
        return null;
    }

    public void adicionarRestaurante(Restaurante restaurante) {
        restaurantes.add(restaurante);
    }

    public void editarRestaurante(Restaurante restaurante) {
        Restaurante restauranteAux = getRestaurante(restaurante.getId());
        if (restaurante != null){
            restauranteAux.setNome(restaurante.getNome());
            restauranteAux.setLocalizacao(restaurante.getLocalizacao());
            restauranteAux.setCapa(restaurante.getCapa());
        }
    }

    public void removerRestaurante(int id) {
        Restaurante restaurante = getRestaurante(id);
        if (restaurante != null)
            restaurantes.remove(restaurante);
    }

}
