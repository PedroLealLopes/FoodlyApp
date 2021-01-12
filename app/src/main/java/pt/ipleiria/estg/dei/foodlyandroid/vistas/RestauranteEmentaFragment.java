package pt.ipleiria.estg.dei.foodlyandroid.vistas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.foodlyandroid.R;
import pt.ipleiria.estg.dei.foodlyandroid.adaptadores.ListaEmentaAdaptador;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.Ementa;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.SingletonFoodly;
import pt.ipleiria.estg.dei.foodlyandroid.utils.GenericUtils;

public class RestauranteEmentaFragment extends Fragment {
    private ListView lvListaEmentaEntrada, lvListaEmentaPrincipal, lvListaEmenraSobremesa;
    private ArrayList<Ementa> listaEmentas;

    public RestauranteEmentaFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurante_ementa, container, false);

        lvListaEmentaEntrada = view.findViewById(R.id.listViewEmentaEntrada);
        lvListaEmentaPrincipal = view.findViewById(R.id.listViewEmentaPrincipal);
        lvListaEmenraSobremesa = view.findViewById(R.id.listViewEmentaSobremesa);

        listaEmentas = SingletonFoodly.getInstance(getContext()).getEmentas();
        lvListaEmentaEntrada.setAdapter(new ListaEmentaAdaptador(getContext(), listaEmentas));
        lvListaEmentaPrincipal.setAdapter(new ListaEmentaAdaptador(getContext(), listaEmentas));
        lvListaEmenraSobremesa.setAdapter(new ListaEmentaAdaptador(getContext(), listaEmentas));

        GenericUtils.setListViewHeightBasedOnItems(lvListaEmentaEntrada);
        GenericUtils.setListViewHeightBasedOnItems(lvListaEmentaPrincipal);
        GenericUtils.setListViewHeightBasedOnItems(lvListaEmenraSobremesa);

        return view;
    }
}