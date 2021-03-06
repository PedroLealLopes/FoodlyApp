package pt.ipleiria.estg.dei.foodlyandroid.adaptadores;

import android.content.Context;
import android.util.Base64;
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
import pt.ipleiria.estg.dei.foodlyandroid.modelos.SingletonFoodly;

public class ListaReviewAdaptador extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Review> reviews;

    public ListaReviewAdaptador(Context context, ArrayList<Review> reviews) {
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
        return reviews.get(position).getRestaurantId();
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
            ivPic = view.findViewById(R.id.imageViewProfilePictureReview);
        }

        public void update(Review review) {
            tvUsername.setText(review.getUsername());
            tvData.setText(review.getCreation_date());
            tvClassificacao.setText(review.getStars() + "");
            tvComentario.setText(review.getComment());
            Glide.with(context)
                    .load(android.util.Base64.decode(review.getImage(), Base64.DEFAULT))
                    .placeholder(R.drawable.noprofile)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(ivPic);
        }
    }
}
