package pt.ipleiria.estg.dei.foodlyandroid.vistas;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.foodlyandroid.R;
import pt.ipleiria.estg.dei.foodlyandroid.adaptadores.ListaRestauranteAdaptador;
import pt.ipleiria.estg.dei.foodlyandroid.listeners.RestaurantesListener;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.Restaurante;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.SingletonFoodly;

public class PerfilTabFavFragment extends Fragment implements RestaurantesListener {

    private ListView lvListaFavoritos;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil_tab_fav, container, false);

        lvListaFavoritos = view.findViewById(R.id.listViewFav);

        lvListaFavoritos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), DetalhesRestauranteActivity.class);
                intent.putExtra(RestauranteInfoFragment.ID_RESTAURANTE, (int) id);
                intent.putExtra(RestauranteEmentaFragment.ID_RESTAURANTE, (int) id);
                intent.putExtra(RestauranteReviewFragment.ID_RESTAURANTE, (int) id);
                startActivity(intent);
            }
        });

        SingletonFoodly.getInstance(getContext()).setRestaurantesListener(this);
        SingletonFoodly.getInstance(getContext()).getAllFavoritosAPI(getContext());

        return view;
    }

    @Override
    public void onRefreshListaRestaurantes(ArrayList<Restaurante> restaurantes) {
        ArrayList<Restaurante> restaurantesfavoritos = SingletonFoodly.getInstance(getContext()).getFavRestaurants();
        lvListaFavoritos.setAdapter(new ListaRestauranteAdaptador(getContext(), restaurantesfavoritos));
    }

    @Override
    public void onRefreshDetalhes() {

    }
}
