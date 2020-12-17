package pt.ipleiria.estg.dei.foodlyandroid.vistas;

import android.content.Context;
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
import pt.ipleiria.estg.dei.foodlyandroid.modelos.SingletonGestorRestaurantes;

public class RestauranteEmentaFragment extends Fragment {

    private Context context;
    private ListView lvListaEmentaEntrada;
    private ArrayList<Ementa> listaEmentas;

    public RestauranteEmentaFragment(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurante_ementa, container, false);

        listaEmentas = SingletonGestorRestaurantes.getInstance().getEmentas();
        lvListaEmentaEntrada = view.findViewById(R.id.listViewEmentaEntrada);
        lvListaEmentaEntrada.setAdapter(new ListaEmentaAdaptador(getContext(), listaEmentas));

        return view;
    }
}