package pt.ipleiria.estg.dei.foodlyandroid.vistas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.foodlyandroid.R;
import pt.ipleiria.estg.dei.foodlyandroid.adaptadores.ListaRestauranteAdaptador;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.Restaurante;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.SingletonGestorRestaurantes;

public class ListaRestaurantesFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final int EDITAR = 2;
    private static final int ADICIONAR = 1;
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
        lvListaRestaurantes = view.findViewById(R.id.lvListaRestaurantes);
        lvListaRestaurantes.setAdapter(new ListaRestauranteAdaptador(getContext(), listaRestaurantes));

        lvListaRestaurantes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*Intent intent = new Intent(getContext(), DetalhesRestauranteActivity.class);
                intent.putExtra(DetalhesRestauranteActivity.ID, (int) id);
                startActivityForResult(intent, EDITAR);*/
            }
        });

        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == Activity.RESULT_OK){
            switch (requestCode){
                case ADICIONAR:
                    listaRestaurantes = SingletonGestorRestaurantes.getInstance().getRestaurantes();
                    lvListaRestaurantes.setAdapter(new ListaRestauranteAdaptador(getContext(), listaRestaurantes));
                    Toast.makeText(getContext(), "Restaurante adicionado com sucesso", Toast.LENGTH_SHORT).show();
                    break;
                case EDITAR:
                    listaRestaurantes = SingletonGestorRestaurantes.getInstance().getRestaurantes();
                    lvListaRestaurantes.setAdapter(new ListaRestauranteAdaptador(getContext(), listaRestaurantes));
                    Toast.makeText(getContext(), "Livro editado com sucesso", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
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