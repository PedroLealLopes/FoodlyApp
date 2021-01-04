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
import pt.ipleiria.estg.dei.foodlyandroid.modelos.Restaurante;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.SingletonGestorRestaurantes;

public class ListaRestaurantesFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

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

        listaRestaurantes = SingletonGestorRestaurantes.getInstance().getRestaurantes();
        lvListaRestaurantes = view.findViewById(R.id.listViewRestaurantes);
        lvListaRestaurantes.setAdapter(new ListaRestauranteAdaptador(getContext(), listaRestaurantes));

        lvListaRestaurantes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), DetalhesRestauranteActivity.class);
                intent.putExtra(DetalhesRestauranteActivity.ID, (int) id);
                startActivity(intent);
            }
        });

        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);

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
                ArrayList<Restaurante> tempLivros = new ArrayList<>();

                for (Restaurante r : SingletonGestorRestaurantes.getInstance().getRestaurantes())
                    if (r.getNome().toLowerCase().contains(newText.toLowerCase()))
                        tempLivros.add(r);
                lvListaRestaurantes.setAdapter(new ListaRestauranteAdaptador(getContext(), tempLivros));
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
        listaRestaurantes = SingletonGestorRestaurantes.getInstance().getRestaurantes();
        lvListaRestaurantes.setAdapter(new ListaRestauranteAdaptador(getContext(), listaRestaurantes));
        swipeRefreshLayout.setRefreshing(false);
    }
}