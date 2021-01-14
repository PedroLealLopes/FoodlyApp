package pt.ipleiria.estg.dei.foodlyandroid.vistas;

import android.os.BaseBundle;
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
        System.out.println("--> Ementa Entrou");
        String[] type = SingletonFoodly.getInstance(getContext()).getEmentaType();
        //System.out.println("--> Ementa Type" + type[0]);
        for (String strTemp : type){
            System.out.println(strTemp);
        }

        if (ementas == null){

        }else{
            if(type.equals("STARTERS"))
                lvListaEmentaEntrada.setAdapter(new ListaEmentaAdaptador(getContext(), ementas));
            if(type.equals("SALADS"))
                lvListaEmentaSalada.setAdapter(new ListaEmentaAdaptador(getContext(), ementas));
            if(type.equals("MAIN COURSE"))
                lvListaEmentaPrincipal.setAdapter(new ListaEmentaAdaptador(getContext(), ementas));
            if(type.equals("DRINKS"))
                lvListaEmenraBebida.setAdapter(new ListaEmentaAdaptador(getContext(), ementas));
            if(type.equals("DESSERTS"))
                lvListaEmenraSobremesa.setAdapter(new ListaEmentaAdaptador(getContext(), ementas));
        }
    }
}