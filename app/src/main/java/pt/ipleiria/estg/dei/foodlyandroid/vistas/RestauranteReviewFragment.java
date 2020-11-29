package pt.ipleiria.estg.dei.foodlyandroid.vistas;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.foodlyandroid.R;
import pt.ipleiria.estg.dei.foodlyandroid.adaptadores.ListaReviewAdaptador;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.Review;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.SingletonGestorRestaurantes;

public class RestauranteReviewFragment extends Fragment {
    private ListView lvListaReviews;
    private ArrayList<Review> listaReviews;

    public RestauranteReviewFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurante_review, container, false);

        listaReviews = SingletonGestorRestaurantes.getInstance().getReviews();
        lvListaReviews = view.findViewById(R.id.listViewReviews);
        lvListaReviews.setAdapter(new ListaReviewAdaptador(getContext(), listaReviews));

        lvListaReviews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String comentario = SingletonGestorRestaurantes.getInstance().getReview(position + 1).getComentario();

                AlertDialog.Builder builder;
                builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Comentário")
                        .setMessage(comentario)
                        .setNegativeButton(R.string.respostaVoltar, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .show();
            }
        });

        return view;
    }
}