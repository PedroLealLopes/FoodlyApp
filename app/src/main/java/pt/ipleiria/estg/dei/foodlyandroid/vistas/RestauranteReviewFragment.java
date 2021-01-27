package pt.ipleiria.estg.dei.foodlyandroid.vistas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.foodlyandroid.R;
import pt.ipleiria.estg.dei.foodlyandroid.adaptadores.ListaReviewAdaptador;
import pt.ipleiria.estg.dei.foodlyandroid.listeners.ReviewsListener;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.Review;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.SingletonFoodly;

public class RestauranteReviewFragment extends Fragment implements ReviewsListener {
    private ListView lvListaReviews;
    private Button btnAdicionar;
    public static final String ID_RESTAURANTE = "ID_RESTAURANTE";
    private TextView tvRating, tvTotalReviews, tvAverage, tv1, tv2, tv3;
    private ImageView ivRed, ivOrange, ivYellow, ivLightPurple, ivPurple;
    private ArrayList<Review> reviewsArray;
    private FragmentManager fragmentManager;

    public RestauranteReviewFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurante_review, container, false);

        final int restauranteId = getActivity().getIntent().getIntExtra(ID_RESTAURANTE, -1);

        tvRating = view.findViewById(R.id.textViewReviewNotaR);
        tvTotalReviews = view.findViewById(R.id.textViewPessoasR);
        tvAverage = view.findViewById(R.id.textViewReviewClassificacaoR);
        tv1 = view.findViewById(R.id.textView1R);
        tv2 = view.findViewById(R.id.textView2R);
        tv3 = view.findViewById(R.id.textView3R);
        ivRed = view.findViewById(R.id.imageViewVermelho);
        ivOrange = view.findViewById(R.id.imageViewLaranja);
        ivYellow = view.findViewById(R.id.imageViewAmarelo);
        ivLightPurple = view.findViewById(R.id.imageViewRoxo);
        ivPurple = view.findViewById(R.id.imageViewRoxoEscuro);

        lvListaReviews = view.findViewById(R.id.listViewReviews);
        lvListaReviews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String comentario = reviewsArray.get(position).getComment();

                AlertDialog.Builder builder;
                builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle(R.string.comentarioTitulo)
                        .setMessage(comentario)
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
                intent.putExtra(RestauranteEmentaFragment.ID_RESTAURANTE, restauranteId);
                startActivity(intent);
            }
        });

        SingletonFoodly.getInstance(getContext()).setReviewsListener(this);
        SingletonFoodly.getInstance(getContext()).getAllReviewsAPI(restauranteId, getContext());

        return view;
    }

    @Override
    public void onRefreshListaReviews(ArrayList<Review> reviews) {
        double summ;
        double media = 0;
        int i = 0;
        reviewsArray = reviews;


        tvTotalReviews.setText(reviews.size() + "");

        if (reviews.size() == 1) {
            tv2.setText(R.string.pessoa);
        }

        if (reviews.size() == 0) {
            tvRating.setText(R.string.naoExistemReviews);
            tvRating.setTextColor(getResources().getColor(R.color.cinza));
            tv1.setVisibility(View.INVISIBLE);
            tv2.setVisibility(View.INVISIBLE);
            tv3.setVisibility(View.INVISIBLE);
            tvTotalReviews.setVisibility(View.INVISIBLE);
            tvAverage.setVisibility(View.INVISIBLE);
            ivRed.setVisibility(View.INVISIBLE);
            ivOrange.setVisibility(View.INVISIBLE);
            ivYellow.setVisibility(View.INVISIBLE);
            ivLightPurple.setVisibility(View.INVISIBLE);
            ivPurple.setVisibility(View.INVISIBLE);
        } else {
            for (Review review : reviews) {
                summ = review.getStars();
                media = media + summ;
                i++;
            }
        }

        if (media / i > 0 && media / i < 1.5) {
            tvRating.setText(R.string.pessimo);
            tvRating.setTextColor(getResources().getColor(R.color.vermelho));
            ivOrange.setImageResource(R.drawable.retangulo_cinza);
            ivYellow.setImageResource(R.drawable.retangulo_cinza);
            ivLightPurple.setImageResource(R.drawable.retangulo_cinza);
            ivPurple.setImageResource(R.drawable.retangulo_cinza);
        }
        if (media / i >= 1.5 && media / i < 2.5) {
            tvRating.setText(R.string.mau);
            tvRating.setTextColor(getResources().getColor(R.color.laranja));
            ivYellow.setImageResource(R.drawable.retangulo_cinza);
            ivLightPurple.setImageResource(R.drawable.retangulo_cinza);
            ivPurple.setImageResource(R.drawable.retangulo_cinza);
        }
        if (media / i >= 2.5 && media / i < 3.5) {
            tvRating.setText(R.string.mediocre);
            tvRating.setTextColor(getResources().getColor(R.color.amarelo));
            ivLightPurple.setImageResource(R.drawable.retangulo_cinza);
            ivPurple.setImageResource(R.drawable.retangulo_cinza);
        }
        if (media / i >= 3.5 && media / i < 4.5) {
            tvRating.setText(R.string.bom);
            tvRating.setTextColor(getResources().getColor(R.color.roxo_claro));
            ivPurple.setImageResource(R.drawable.retangulo_cinza);
        }
        if (media / i >= 4.5) {
            tvRating.setText(R.string.excelente);
            tvRating.setTextColor(getResources().getColor(R.color.roxo_escuro));
        }
        String result = String.format("%.1f", media / i);
        tvAverage.setText(result);

        if (reviews != null)
            lvListaReviews.setAdapter(new ListaReviewAdaptador(getContext(), reviews));
    }

    @Override
    public void onRefreshDetalhes() {
        Fragment fragment = new RestauranteReviewFragment();
        fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}