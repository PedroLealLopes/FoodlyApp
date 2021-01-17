package pt.ipleiria.estg.dei.foodlyandroid.vistas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
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
    private ArrayList<Review> reviewsArrayList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil_tab_review, container, false);

        lvListaReviewsUser = view.findViewById(R.id.listViewReviewsUser);

        lvListaReviewsUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialogReview(position);
            }
        });

        SingletonFoodly.getInstance(getContext()).setReviewsListener(this);
        SingletonFoodly.getInstance(getContext()).getAllReviewsUsersAPI(getContext());

        return view;
    }

    @Override
    public void onRefreshListaReviews(ArrayList<Review> reviews) {

        if (reviews != null){
            reviewsArrayList = reviews;
            lvListaReviewsUser.setAdapter(new ListaReviewUserAdaptador(getContext(), reviews));
        }
    }

    @Override
    public void onRefreshDetalhes() {

    }

    private void dialogReview(int id) {

        final Review review = reviewsArrayList.get(id);

        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(getContext());
        builder.setTitle("ELIMINAR REVIEW")
                .setMessage("Deseja eliminar a review?")
                .setPositiveButton(R.string.respostaSim, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(), "review: "+ review.toString(), Toast.LENGTH_SHORT).show();
                        SingletonFoodly.getInstance(getContext()).removerReviewUserAPI(review, getContext());
                    }
                })

                .setNegativeButton(R.string.respostaNao, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Cancelar
                    }
                })
                .setIcon(R.drawable.ic_delete)
                .show();

    }
}
