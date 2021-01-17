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

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.foodlyandroid.R;
import pt.ipleiria.estg.dei.foodlyandroid.listeners.RestaurantesListener;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.Restaurante;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.RestauranteFavorito;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.SingletonFoodly;

public class RestauranteInfoFragment extends Fragment implements RestaurantesListener {

    public static final String ID_RESTAURANTE = "ID_RESTAURANTE";
    private Restaurante restaurante;
    private RestauranteFavorito restauranteFavorito;
    private TextView tvCurrentPeople, tvMaxPeople, tvName, tvLocation, tvPhone, tvEmail, tvOpeningHour, tvClosingHour, tvDescription, tvWifiPassword, tvWifiPasswordText, tvHasVegan, tvAllowsPets;
    private ImageView ivImage, ivFav;
    private ArrayList<Restaurante> restauranteArray;

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

        carregarDetalhesRestaurante();

        ivFav = view.findViewById(R.id.imageViewFav);
        ivFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ivFav.getDrawable().getConstantState() == ResourcesCompat.getDrawable(getResources(), R.drawable.ic_restaurante_fav_false, null).getConstantState()) {
                    //Adicionar aos Favoritos
                    dialogAdicionarFav(restauranteId);
                } else {
                    //Remover dos Favoritos
                    //dialogRemoverFav(restauranteId);
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
        final int restauranteId = getActivity().getIntent().getIntExtra(ID_RESTAURANTE, -1);

        if (restaurante != null) {

            //System.out.println("---> profile ID: " + SingletonFoodly.getInstance(getContext()).getProfileId() + " restaurant ID: " + restauranteId);
            //System.out.println("---> profile ID: " + restauranteArray.get().get + " restaurant ID: " + restauranteArray.get().getRestaurantId());

            tvCurrentPeople.setText(restaurante.getCurrentPeople() + "");
            tvMaxPeople.setText(restaurante.getMaxPeople() + "");
            tvName.setText(restaurante.getName());
            tvLocation.setText(restaurante.getLocation());
            tvPhone.setText(restaurante.getPhone());
            tvEmail.setText(restaurante.getEmail());
            tvOpeningHour.setText(restaurante.getOpeningHour());
            tvClosingHour.setText(restaurante.getClosingHour());
            tvDescription.setText(restaurante.getDescription());
            if (restaurante.getWifiPassword() != null) {
                tvWifiPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_task_true, 0, 0, 0);
                tvWifiPasswordText.setText(restaurante.getWifiPassword());
            } else {
                //TODO: QUANDO O WIFI VEM A NULL -> TASK FALSE
            }
            if (restaurante.getVegan() != 0)
                tvHasVegan.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_task_true, 0, 0, 0);
            if (restaurante.getAllowsPets() != 0)
                tvAllowsPets.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_task_true, 0, 0, 0);
            Glide.with(this)
                    .load(restaurante.getImage())
                    .placeholder(R.drawable.gordon)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(ivImage);
        }
    }

    private void dialogAdicionarFav(final int id) {

        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(getContext());
        builder.setTitle("ADICIONAR")
                .setMessage("Deseja adicionar este restaurante aos favoritos?")
                .setPositiveButton(R.string.respostaSim, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        restauranteFavorito = new RestauranteFavorito(SingletonFoodly.getInstance(getContext()).getProfileId(), id);
                        SingletonFoodly.getInstance(getContext()).adicionarFavoritoAPI(restauranteFavorito, getContext());
                        ivFav.setImageResource(R.drawable.ic_restaurante_fav_true);
                    }
                })
                .setNegativeButton(R.string.respostaNao, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Cancelar
                    }
                })
                .setIcon(R.drawable.ic_adicionar)
                .show();
    }

    private void dialogRemoverFav(int id) {
        final Restaurante restaurante = restauranteArray.get(id);

        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(getContext());
        builder.setTitle("REMOVER")
                .setMessage("Deseja remover este restaurante dos favoritos?")
                .setPositiveButton(R.string.respostaSim, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //SingletonFoodly.getInstance(getContext()).removerFavoritoAPI(restaurante, getContext());
                        ivFav.setImageResource(R.drawable.ic_restaurante_fav_false);
                    }
                })
                .setNegativeButton(R.string.respostaNao, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Cancelar
                    }
                })
                .setIcon(R.drawable.ic_adicionar)
                .show();
    }

    @Override
    public void onRefreshListaRestaurantes(ArrayList<Restaurante> restaurantes) {
        restauranteArray = restaurantes;
    }

    @Override
    public void onRefreshDetalhes() {

    }
}