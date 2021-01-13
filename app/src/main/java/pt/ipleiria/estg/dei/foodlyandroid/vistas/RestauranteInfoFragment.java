package pt.ipleiria.estg.dei.foodlyandroid.vistas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.foodlyandroid.R;
import pt.ipleiria.estg.dei.foodlyandroid.listeners.RestaurantesListener;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.Restaurante;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.SingletonFoodly;

public class RestauranteInfoFragment extends Fragment implements RestaurantesListener {

    private ImageView ivFav;
    public static final String ID = "ID";

    private Restaurante restaurante;
    private TextView tvCurrentPeople, tvMaxPeople, tvName, tvLocation, tvPhone, tvEmail, tvOpeningHour, tvClosingHour, tvDescription, tvWifiPassword, tvWifiPasswordText, tvHasVegan, tvAllowsPets;
    private ImageView ivImage;
    private String token;

    public RestauranteInfoFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurante_info, container, false);

        int id = getActivity().getIntent().getIntExtra(ID, -1);
        restaurante = SingletonFoodly.getInstance(getContext()).getRestaurante(id);

        SharedPreferences sharedPrefUser = getActivity().getSharedPreferences(MenuMainActivity.USER, Context.MODE_PRIVATE);
        token = sharedPrefUser.getString(MenuMainActivity.TOKEN, null);

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

        carregarDetalhesLivro();

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

    private void carregarDetalhesLivro() {
        if (restaurante != null) {
            tvCurrentPeople.setText(restaurante.getCurrentPeople()+"");
            tvMaxPeople.setText(restaurante.getMaxPeople()+"");
            tvName.setText(restaurante.getName());
            tvLocation.setText(restaurante.getLocation());
            tvPhone.setText(restaurante.getPhone());
            tvEmail.setText(restaurante.getEmail());
            tvOpeningHour.setText(restaurante.getOpeningHour());
            tvClosingHour.setText(restaurante.getClosingHour());
            tvDescription.setText(restaurante.getDescription());
            if(restaurante.getWifiPassword() != null){
                tvWifiPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_task_true, 0, 0, 0);
                tvWifiPasswordText.setText(restaurante.getWifiPassword());
            }
            if(restaurante.getVegan() != 0)
                tvHasVegan.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_task_true, 0, 0, 0);
            if(restaurante.getAllowsPets() != 0)
                tvAllowsPets.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_task_true, 0, 0, 0);
            Glide.with(this)
                    .load(restaurante.getImage())
                    .placeholder(R.drawable.gordon)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(ivImage);
        }
    }

    @Override
    public void onRefreshListaRestaurantes(ArrayList<Restaurante> listaRestaurantes) {

    }

    @Override
    public void onRefreshDetalhes() {

    }
}