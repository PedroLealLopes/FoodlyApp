package pt.ipleiria.estg.dei.foodlyandroid.vistas;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.foodlyandroid.R;
import pt.ipleiria.estg.dei.foodlyandroid.adaptadores.ListaRestauranteAdaptador;
import pt.ipleiria.estg.dei.foodlyandroid.adaptadores.ListaReviewAdaptador;
import pt.ipleiria.estg.dei.foodlyandroid.adaptadores.ListaReviewUserAdaptador;
import pt.ipleiria.estg.dei.foodlyandroid.listeners.ReviewsListener;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.Restaurante;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.Review;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.SingletonFoodly;

public class PerfilTabReviewFragment extends Fragment implements ReviewsListener {

    private ListView lvListaReviewsUser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil_tab_review, container, false);
        lvListaReviewsUser = view.findViewById(R.id.listViewReviewsUser);

        lvListaReviewsUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Dialog
            }
        });

        SingletonFoodly.getInstance(getContext()).setReviewsListener(this);
        SingletonFoodly.getInstance(getContext()).getAllReviewsUsersAPI(getContext());

        return view;
    }

    @Override
    public void onRefreshListaReviews(ArrayList<Review> listaReviews) {
        ArrayList<Review> reviewsUsers = SingletonFoodly.getInstance(getContext()).getReviewsUsers();

        lvListaReviewsUser.setAdapter(new ListaReviewUserAdaptador(getContext(), reviewsUsers));
    }

    @Override
    public void onRefreshDetalhes() {

    }
}
