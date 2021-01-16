package pt.ipleiria.estg.dei.foodlyandroid.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.foodlyandroid.R;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.Review;

public class ListaReviewUserAdaptador extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Review> reviews;

    public ListaReviewUserAdaptador(Context context, ArrayList<Review> reviews) {
        this.context = context;
        this.reviews = reviews;
    }

    @Override
    public int getCount() {
        return reviews.size();
    }

    @Override
    public Object getItem(int position) {
        return reviews.get(position);
    }

    @Override
    public long getItemId(int position) {
        return reviews.get(position).getProfileId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.item_lista_review_user, null);

        //OTIMIZAÇÃO
        ViewHolderLista viewHolderLista = (ViewHolderLista) convertView.getTag();
        if (viewHolderLista == null) {
            viewHolderLista = new ViewHolderLista(convertView);
            convertView.setTag(viewHolderLista);
        }

        viewHolderLista.update(reviews.get(position));

        return convertView;
    }

    private static class ViewHolderLista {
        private final TextView tvRestauranteNome, tvRestauranteMorada, tvClassificacao, tvDataCriacao, tvComentario;

        public ViewHolderLista(View view) {
            tvRestauranteNome = view.findViewById(R.id.textViewRestauranteNomeRU);
            tvRestauranteMorada = view.findViewById(R.id.textViewRestauranteMoradaRU);
            tvClassificacao = view.findViewById(R.id.textViewClassificacaoRU);
            tvDataCriacao = view.findViewById(R.id.textViewDataCriacaoRU);
            tvComentario = view.findViewById(R.id.textViewComentarioRU);
        }

        public void update(Review review) {
            //String profileUsername = SingletonFoodly.getInstance(context).getProfileUsername();
            //String profilePic = SingletonFoodly.getInstance(context).getProfilePic();

            tvRestauranteNome.setText("profileUsername");
            tvRestauranteMorada.setText("morada");
            tvClassificacao.setText(review.getStars() + "");
            tvDataCriacao.setText(review.getCreation_date());
            tvComentario.setText(review.getComment());
        }
    }
}
