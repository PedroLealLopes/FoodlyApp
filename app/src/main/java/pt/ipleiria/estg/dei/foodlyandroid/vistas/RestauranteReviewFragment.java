package pt.ipleiria.estg.dei.foodlyandroid.vistas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.foodlyandroid.R;
import pt.ipleiria.estg.dei.foodlyandroid.adaptadores.ListaEmentaAdaptador;
import pt.ipleiria.estg.dei.foodlyandroid.adaptadores.ListaReviewAdaptador;
import pt.ipleiria.estg.dei.foodlyandroid.listeners.ReviewsListener;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.Ementa;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.Review;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.SingletonFoodly;
import pt.ipleiria.estg.dei.foodlyandroid.utils.GenericUtils;

public class RestauranteReviewFragment extends Fragment implements ReviewsListener {
    private ListView lvListaReviews;
    private Button btnAdicionar;
    public static final String ID_RESTAURANTE = "ID_RESTAURANTE";
    private TextView tvTotalPessoas;

    public RestauranteReviewFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurante_review, container, false);

        int restaurantId = getActivity().getIntent().getIntExtra(ID_RESTAURANTE, -1);

        lvListaReviews = view.findViewById(R.id.listViewReviews);
        tvTotalPessoas = view.findViewById(R.id.textViewPessoas);

        lvListaReviews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //String comentario = SingletonFoodly.getInstance(getContext()).getReviewComment();

                AlertDialog.Builder builder;
                builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Comentário")
                        .setMessage("comentario")
                        .setNegativeButton(R.string.respostaVoltar, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .show();
            }
        });

        btnAdicionar = view.findViewById(R.id.buttonAdicionarReview);
        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AdicionarReviewActivity.class);
                startActivity(intent);
            }
        });

        SingletonFoodly.getInstance(getContext()).setReviewsListener(this);
        SingletonFoodly.getInstance(getContext()).getAllReviewsAPI(restaurantId, getContext());

        return view;
    }

    @Override
    public void onRefreshListaReviews(ArrayList<Review> reviews) {
        if (reviews != null)
            lvListaReviews.setAdapter(new ListaReviewAdaptador(getContext(), reviews));
    }

    @Override
    public void onRefreshDetalhes() {

    }
}