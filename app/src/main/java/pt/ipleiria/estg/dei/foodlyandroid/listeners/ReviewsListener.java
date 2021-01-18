package pt.ipleiria.estg.dei.foodlyandroid.listeners;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.foodlyandroid.modelos.Review;

public interface ReviewsListener {
    void onRefreshListaReviews(ArrayList<Review> listaReviews);

    void onRefreshDetalhes();
}
