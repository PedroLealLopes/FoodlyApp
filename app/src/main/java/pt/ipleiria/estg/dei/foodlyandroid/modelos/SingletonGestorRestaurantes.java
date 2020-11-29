package pt.ipleiria.estg.dei.foodlyandroid.modelos;

import pt.ipleiria.estg.dei.foodlyandroid.R;

import java.util.ArrayList;

public class SingletonGestorRestaurantes {

    private static SingletonGestorRestaurantes instance = null;
    private ArrayList<Restaurante> restaurantes;
    private ArrayList<Ingrediente> ingredientes;
    private ArrayList<Ementa> ementas;
    private ArrayList<Review> reviews;

    public static synchronized SingletonGestorRestaurantes getInstance() {
        if (instance == null)
            instance = new SingletonGestorRestaurantes();
        return instance;
    }

    private SingletonGestorRestaurantes() {
        gerarFakeDataRestaurante();
        gerarFakeDataIngrediente();
        gerarFakeDataEmenta();
        gerarFakeDataReviews();
    }

    private void gerarFakeDataRestaurante() {
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
        if (restaurante != null) {
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

    private void gerarFakeDataIngrediente() {
        ingredientes = new ArrayList<>();
        ingredientes.add(new Ingrediente(1, "sal"));
        ingredientes.add(new Ingrediente(2, "salsa"));
        ingredientes.add(new Ingrediente(3, "frango"));
        ingredientes.add(new Ingrediente(4, "peixe"));
    }

    public ArrayList<Ingrediente> getIngredientes() {
        return new ArrayList<>(ingredientes);
    }

    private void gerarFakeDataEmenta() {
        ementas = new ArrayList<>();
        ementas.add(new Ementa(1, "Pao", 0.50, null));
        ementas.add(new Ementa(2, "Sopa", 1, getIngredientes()));
        ementas.add(new Ementa(3, "Bitoque", 5, getIngredientes()));
        ementas.add(new Ementa(4, "Gelado", 1.20, null));
    }

    public ArrayList<Ementa> getEmentas() {
        return new ArrayList<>(ementas);
    }

    private void gerarFakeDataReviews() {
        reviews = new ArrayList<>();
        reviews.add(new Review(1, R.drawable.gordon, 4.8, "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummyLorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummyLorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy", "Pedro", "29/11/2020"));
        reviews.add(new Review(2, R.drawable.gordon, 3, "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy", "Joao", "02/10/2020"));
        reviews.add(new Review(3, R.drawable.gordon, 3.5, "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy", "Tiago", "10/09/2020"));
        reviews.add(new Review(4, R.drawable.gordon, 2, "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy", "Alex", "12/11/2020"));
        reviews.add(new Review(5, R.drawable.gordon, 5, "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy", "Ricardo", "20/08/2020"));
    }

    public ArrayList<Review> getReviews() {
        return new ArrayList<>(reviews);
    }

    public Review getReview(int id) {
        for (Review r : reviews)
            if (r.getId() == id)
                return r;
        return null;
    }
}
