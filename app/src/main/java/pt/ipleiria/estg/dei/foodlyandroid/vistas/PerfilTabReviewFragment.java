package pt.ipleiria.estg.dei.foodlyandroid.vistas;

import android.content.DialogInterface;
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
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.foodlyandroid.R;
import pt.ipleiria.estg.dei.foodlyandroid.adaptadores.ListaReviewUserAdaptador;
import pt.ipleiria.estg.dei.foodlyandroid.listeners.ReviewsListener;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.Review;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.SingletonFoodly;

public class PerfilTabReviewFragment extends Fragment implements ReviewsListener {

    private ListView lvListaReviewsUser;
    private ArrayList<Review> reviewsArrayList;
    private FragmentManager fragmentManager;

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
        if (reviews != null) {
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
        builder.setTitle(R.string.eliminarReviewTitulo)
                .setMessage(R.string.eliminarReviewSair)
                .setPositiveButton(R.string.respostaSim, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SingletonFoodly.getInstance(getContext()).removerReviewUserAPI(review, getContext());
                        Toast.makeText(getContext(), R.string.reviewRemovidaSucesso, Toast.LENGTH_SHORT).show();

                        Fragment fragment = new PerfilTabReviewFragment();
                        fragmentManager = getFragmentManager();
                        fragmentManager.beginTransaction()
                                .replace(R.id.fragment_container, fragment)
                                .commit();
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
