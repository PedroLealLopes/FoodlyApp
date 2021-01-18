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
import pt.ipleiria.estg.dei.foodlyandroid.listeners.EmentasListener;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.Ementa;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.SingletonFoodly;
import pt.ipleiria.estg.dei.foodlyandroid.utils.GenericUtils;

public class RestauranteEmentaFragment extends Fragment implements EmentasListener {

    public static final String ID_RESTAURANTE = "ID_RESTAURANTE";
    private ListView lvListaEmentaEntrada, lvListaEmentaSalada, lvListaEmentaPrincipal, lvListaEmenraBebida, lvListaEmenraSobremesa;

    public RestauranteEmentaFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurante_ementa, container, false);

        int restaurantId = getActivity().getIntent().getIntExtra(ID_RESTAURANTE, -1);

        lvListaEmentaEntrada = view.findViewById(R.id.listViewEmentaEntrada);
        lvListaEmentaSalada = view.findViewById(R.id.listViewEmentaSalada);
        lvListaEmentaPrincipal = view.findViewById(R.id.listViewEmentaPrincipal);
        lvListaEmenraBebida = view.findViewById(R.id.listViewEmentaBebida);
        lvListaEmenraSobremesa = view.findViewById(R.id.listViewEmentaSobremesa);

        SingletonFoodly.getInstance(getContext()).setEmentasListener(this);
        SingletonFoodly.getInstance(getContext()).getAllEmentasAPI(restaurantId, getContext());

        return view;
    }

    @Override
    public void onRefreshListaEmentas(ArrayList<Ementa> ementas) {
        int restaurantId = getActivity().getIntent().getIntExtra(ID_RESTAURANTE, -1);
        ArrayList<Ementa> type = SingletonFoodly.getInstance(getContext()).getEmentaType(restaurantId);
        ArrayList<Ementa> starters = new ArrayList<>();
        ArrayList<Ementa> salads = new ArrayList<>();
        ArrayList<Ementa> mainCourse = new ArrayList<>();
        ArrayList<Ementa> drinks = new ArrayList<>();
        ArrayList<Ementa> desserts = new ArrayList<>();

        for (Ementa e : type) {
            if (e.getType().equals("STARTERS"))
                starters.add(e);
            if (e.getType().equals("SALADS"))
                salads.add(e);
            if (e.getType().equals("MAIN COURSE"))
                mainCourse.add(e);
            if (e.getType().equals("DRINKS"))
                drinks.add(e);
            if (e.getType().equals("DESSERTS"))
                desserts.add(e);
        }

        lvListaEmentaEntrada.setAdapter(new ListaEmentaAdaptador(getContext(), starters));
        lvListaEmentaSalada.setAdapter(new ListaEmentaAdaptador(getContext(), salads));
        lvListaEmentaPrincipal.setAdapter(new ListaEmentaAdaptador(getContext(), mainCourse));
        lvListaEmenraBebida.setAdapter(new ListaEmentaAdaptador(getContext(), drinks));
        lvListaEmenraSobremesa.setAdapter(new ListaEmentaAdaptador(getContext(), desserts));

        GenericUtils.setListViewHeightBasedOnChildren(lvListaEmentaEntrada);
        GenericUtils.setListViewHeightBasedOnChildren(lvListaEmentaSalada);
        GenericUtils.setListViewHeightBasedOnChildren(lvListaEmentaPrincipal);
        GenericUtils.setListViewHeightBasedOnChildren(lvListaEmenraBebida);
        GenericUtils.setListViewHeightBasedOnChildren(lvListaEmenraSobremesa);
    }
}