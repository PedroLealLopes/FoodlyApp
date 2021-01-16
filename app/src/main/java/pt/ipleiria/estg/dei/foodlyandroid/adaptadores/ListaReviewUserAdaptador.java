package pt.ipleiria.estg.dei.foodlyandroid.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

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
            convertView = inflater.inflate(R.layout.item_lista_review, null);

        //OTIMIZAÇÃO
        ViewHolderLista viewHolderLista = (ViewHolderLista) convertView.getTag();
        if (viewHolderLista == null) {
            viewHolderLista = new ViewHolderLista(convertView);
            convertView.setTag(viewHolderLista);
        }

        viewHolderLista.update(reviews.get(position));

        return convertView;
    }

    private class ViewHolderLista {
        private TextView tvUsername, tvData, tvClassificacao, tvComentario;
        private ImageView ivPic;

        public ViewHolderLista(View view) {
            tvUsername = view.findViewById(R.id.textViewUsername);
            tvData = view.findViewById(R.id.textViewDataCriacao);
            tvClassificacao = view.findViewById(R.id.textViewClassificacao);
            tvComentario = view.findViewById(R.id.textViewComentario);
            ivPic = view.findViewById(R.id.imageViewProfilePic);
        }

        public void update(Review review) {
            //String profileUsername = SingletonFoodly.getInstance(context).getProfileUsername();
            //String profilePic = SingletonFoodly.getInstance(context).getProfilePic();

            tvUsername.setText("profileUsername");
            tvData.setText(review.getCreation_date());
            tvClassificacao.setText(review.getStars() + "");
            tvComentario.setText(review.getComment());
            Glide.with(context)
                    .load(R.drawable.gordon)
                    .placeholder(R.drawable.gordon)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(ivPic);
        }
    }
}
