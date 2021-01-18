package pt.ipleiria.estg.dei.foodlyandroid.vistas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.foodlyandroid.R;
import pt.ipleiria.estg.dei.foodlyandroid.listeners.RestaurantesListener;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.Restaurante;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.SingletonFoodly;

public class RestauranteInfoFragment extends Fragment implements RestaurantesListener {

    public static final String ID_RESTAURANTE = "ID_RESTAURANTE";
    private Restaurante restaurante;
    private TextView tvCurrentPeople, tvMaxPeople, tvName, tvLocation, tvPhone, tvEmail, tvOpeningHour, tvClosingHour, tvDescription, tvWifiPassword, tvWifiPasswordText, tvHasVegan, tvAllowsPets;
    private ImageView ivImage, ivFav;
    private ArrayList<Restaurante> favoritosArray;

    public RestauranteInfoFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurante_info, container, false);

        final int restauranteId = getActivity().getIntent().getIntExtra(ID_RESTAURANTE, -1);
        restaurante = SingletonFoodly.getInstance(getContext()).getRestaurante(restauranteId);

        tvCurrentPeople = view.findViewById(R.id.textViewRestaurantCurrentPeople);
        tvMaxPeople = view.findViewById(R.id.textViewRestaurantMaxPeople);
        tvName = view.findViewById(R.id.textViewRestaurantName);
        tvLocation = view.findViewById(R.id.textViewRestaurantLocation);
        tvPhone = view.findViewById(R.id.textViewRestaurantPhone);
        tvEmail = view.findViewById(R.id.textViewRestaurantEmail);
        tvOpeningHour = view.findViewById(R.id.textViewRestaurantOpeningHour);
        tvClosingHour = view.findViewById(R.id.textViewRestaurantClosingHour);
        tvDescription = view.findViewById(R.id.textViewRestaurantDescription);
        tvWifiPassword = view.findViewById(R.id.textViewRestaurantHasWiFiPassword);
        tvWifiPasswordText = view.findViewById(R.id.textViewRestaurantWiFiPasswordText);
        tvHasVegan = view.findViewById(R.id.textViewRestaurantHasVegan);
        tvAllowsPets = view.findViewById(R.id.textViewRestaurantAllowsPets);
        ivImage = view.findViewById(R.id.imageViewRestaurantImage);

        SingletonFoodly.getInstance(getContext()).setRestaurantesListener(this);
        SingletonFoodly.getInstance(getContext()).getAllFavoritosAPI(getContext());

        carregarDetalhesRestaurante();

        ivFav = view.findViewById(R.id.imageViewFav);
        ivFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ivFav.getDrawable().getConstantState() == ResourcesCompat.getDrawable(getResources(), R.drawable.ic_restaurante_fav_false, null).getConstantState()) {
                    //Adicionar aos Favoritos
                    SingletonFoodly.getInstance(getContext()).adicionarFavoritoAPI(SingletonFoodly.getInstance(getContext()).getProfileId(), restauranteId, getContext());
                    ivFav.setImageResource(R.drawable.ic_restaurante_fav_true);
                    Toast.makeText(getContext(), "Restaurante adicionado aos favoritos", Toast.LENGTH_SHORT).show();
                } else {
                    //Remover dos Favoritos
                    dialogRemoverFav(restauranteId);
                }
            }
        });

        Button btnFazerPedido = view.findViewById(R.id.buttonFazerPedido);
        btnFazerPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FazerPedidoActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void carregarDetalhesRestaurante() {
        if (restaurante != null) {

            tvCurrentPeople.setText(restaurante.getCurrentPeople() + "");
            tvMaxPeople.setText(restaurante.getMaxPeople() + "");
            tvName.setText(restaurante.getName());
            tvLocation.setText(restaurante.getLocation());
            tvPhone.setText(restaurante.getPhone());
            tvEmail.setText(restaurante.getEmail());
            tvOpeningHour.setText(restaurante.getOpeningHour());
            tvClosingHour.setText(restaurante.getClosingHour());
            tvDescription.setText(restaurante.getDescription());
            if (!restaurante.getWifiPassword().equals("null")) {
                tvWifiPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_task_true, 0, 0, 0);
                tvWifiPasswordText.setText("- " + restaurante.getWifiPassword());
            } else {
                tvWifiPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_task_false, 0, 0, 0);
                tvWifiPasswordText.setText("");
            }
            if (restaurante.getVegan() != 0)
                tvHasVegan.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_task_true, 0, 0, 0);
            if (restaurante.getAllowsPets() != 0)
                tvAllowsPets.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_task_true, 0, 0, 0);
            Glide.with(this)
                    .load(restaurante.getImage())
                    .placeholder(R.drawable.img_restaurante)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(ivImage);
        }
    }

    private void dialogRemoverFav(final int id) {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(getContext());
        builder.setTitle("REMOVER")
                .setMessage("Deseja remover este restaurante dos favoritos?")
                .setPositiveButton(R.string.respostaSim, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SingletonFoodly.getInstance(getContext()).removerFavoritoAPI(id, getContext());
                        ivFav.setImageResource(R.drawable.ic_restaurante_fav_false);
                        Toast.makeText(getContext(), "Restaurante removido dos favoritos", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onRefreshListaRestaurantes(ArrayList<Restaurante> restaurantes) {
        ArrayList<Restaurante> restaurantesfavoritos = SingletonFoodly.getInstance(getContext()).getFavRestaurants();
        final int restauranteId = getActivity().getIntent().getIntExtra(ID_RESTAURANTE, -1);

        for (int i = 0; i < restaurantesfavoritos.size(); i++) {
            if (restauranteId == restaurantesfavoritos.get(i).getRestaurantId()) {
                ivFav.setImageResource(R.drawable.ic_restaurante_fav_true);
                return;
            }
        }
    }

    @Override
    public void onRefreshDetalhes() {

    }
}