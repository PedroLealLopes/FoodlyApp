package pt.ipleiria.estg.dei.foodlyandroid.vistas;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.foodlyandroid.R;
import pt.ipleiria.estg.dei.foodlyandroid.adaptadores.ListaEmentaAdaptador;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.Ementa;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.SingletonGestorRestaurantes;

public class RestauranteEmentaFragment extends Fragment implements View.OnClickListener {

    private Context context;
    private ListView lvListaEmentas;
    private ArrayList<Ementa> listaEmentas;
    Button btnEntrada, btnPrincipal, btnSobremesa;

    public RestauranteEmentaFragment(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurante_ementa, container, false);

        listaEmentas = SingletonGestorRestaurantes.getInstance().getEmentas();
        lvListaEmentas = view.findViewById(R.id.listViewEmentas);
        lvListaEmentas.setAdapter(new ListaEmentaAdaptador(getContext(), listaEmentas));

        btnEntrada = view.findViewById(R.id.buttonEntrada);
        btnEntrada.setOnClickListener(this);
        btnEntrada.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.button_ementa_focused));
        btnEntrada.setTextColor(getResources().getColor(R.color.branco));

        btnPrincipal = view.findViewById(R.id.buttonPrincipal);
        btnPrincipal.setOnClickListener(this);

        btnSobremesa = view.findViewById(R.id.buttonSobremesa);
        btnSobremesa.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.buttonEntrada:
                btnEntrada.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.button_ementa_focused));
                btnEntrada.setTextColor(getResources().getColor(R.color.branco));
                btnPrincipal.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.button_ementa));
                btnPrincipal.setTextColor(getResources().getColor(R.color.roxo));
                btnSobremesa.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.button_ementa));
                btnSobremesa.setTextColor(getResources().getColor(R.color.roxo));
                break;
            case R.id.buttonPrincipal:
                btnPrincipal.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.button_ementa_focused));
                btnPrincipal.setTextColor(getResources().getColor(R.color.branco));
                btnEntrada.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.button_ementa));
                btnEntrada.setTextColor(getResources().getColor(R.color.roxo));
                btnSobremesa.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.button_ementa));
                btnSobremesa.setTextColor(getResources().getColor(R.color.roxo));
                break;
            case R.id.buttonSobremesa:
                btnSobremesa.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.button_ementa_focused));
                btnSobremesa.setTextColor(getResources().getColor(R.color.branco));
                btnEntrada.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.button_ementa));
                btnEntrada.setTextColor(getResources().getColor(R.color.roxo));
                btnPrincipal.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.button_ementa));
                btnPrincipal.setTextColor(getResources().getColor(R.color.roxo));
                break;
            default:
        }
    }
}