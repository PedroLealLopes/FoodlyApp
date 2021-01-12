package pt.ipleiria.estg.dei.foodlyandroid.vistas;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.foodlyandroid.R;
import pt.ipleiria.estg.dei.foodlyandroid.adaptadores.ListaRestauranteAdaptador;
import pt.ipleiria.estg.dei.foodlyandroid.listeners.RestaurantesListener;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.Restaurante;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.SingletonFoodly;

public class ListaRestaurantesFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, RestaurantesListener {

    private ListView lvListaRestaurantes;
    private ArrayList<Restaurante> listaRestaurantes;
    private SearchView searchView;
    private SwipeRefreshLayout swipeRefreshLayout;

    public ListaRestaurantesFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_restaurantes, container, false);
        setHasOptionsMenu(true);

        lvListaRestaurantes = view.findViewById(R.id.listViewRestaurantes);

        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);

        lvListaRestaurantes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), DetalhesRestauranteActivity.class);
                intent.putExtra(DetalhesRestauranteActivity.ID, (int) id);
                startActivity(intent);
            }
        });

        SingletonFoodly.getInstance(getContext()).setRestaurantesListener(this);
        SingletonFoodly.getInstance(getContext()).getAllRestaurantesAPI(getContext());
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_pesquisa, menu);

        MenuItem itemPesquisa = menu.findItem(R.id.itemPesquisa);
        searchView = (SearchView) itemPesquisa.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<Restaurante> tempRestaurantes = new ArrayList<>();

                for (Restaurante r : SingletonFoodly.getInstance(getContext()).getRestaurantesBD())
                    if (r.getNome().toLowerCase().contains(newText.toLowerCase()))
                        tempRestaurantes.add(r);
                lvListaRestaurantes.setAdapter(new ListaRestauranteAdaptador(getContext(), tempRestaurantes));
                return true;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (searchView != null)
            searchView.onActionViewCollapsed();
        super.onResume();
    }

    @Override
    public void onRefresh() {
        SingletonFoodly.getInstance(getContext()).getAllRestaurantesAPI(getContext());
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefreshListaRestaurantes(ArrayList<Restaurante> restaurantes) {
        if (restaurantes != null)
            lvListaRestaurantes.setAdapter(new ListaRestauranteAdaptador(getContext(), restaurantes));
    }

    @Override
    public void onRefreshDetalhes() {

    }
}