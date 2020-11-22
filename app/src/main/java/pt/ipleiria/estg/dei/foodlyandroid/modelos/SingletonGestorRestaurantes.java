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
        /*restaurantes = new ArrayList<>();
        restaurantes.add(new Livro(2020, R.drawable.programarandroid2, "Programar em Android AMSI - 1", "2ª Temporada", "AMSI TEAM"));
        restaurantes.add(new Livro(2020, R.drawable.programarandroid1, "Programar em Android AMSI - 2", "2ª Temporada", "AMSI TEAM"));
        restaurantes.add(new Livro(2020, R.drawable.logoipl, "Programar em Android AMSI - 3", "2ª Temporada", " AMSI TEAM"));
        restaurantes.add(new Livro(2020, R.drawable.programarandroid1, "Programar em Android AMSI - 5", "2ª Temporada", "AMSI TEAM"));
        restaurantes.add(new Livro(2020, R.drawable.logoipl, "Programar em Android AMSI - 6", "2ª Temporada", " AMSI TEAM"));*/
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
