package pt.ipleiria.estg.dei.foodlyandroid.vistas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.foodlyandroid.R;
import pt.ipleiria.estg.dei.foodlyandroid.adaptadores.ListaFazerPedidoAdaptador;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.Ementa;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.SingletonGestorRestaurantes;
import pt.ipleiria.estg.dei.foodlyandroid.utils.GenericUtils;

public class FazerPedidoActivity extends AppCompatActivity {

    private ListView lvListaEmentaEntrada, lvListaEmentaPrincipal, lvListaEmenraSobremesa;
    private ArrayList<Ementa> listaEmentas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fazer_pedido);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Fazer Pedido");

        lvListaEmentaEntrada = findViewById(R.id.listViewEmentaEntrada);
        lvListaEmentaPrincipal = findViewById(R.id.listViewEmentaPrincipal);
        lvListaEmenraSobremesa = findViewById(R.id.listViewEmentaSobremesa);

        listaEmentas = SingletonGestorRestaurantes.getInstance().getEmentas();
        lvListaEmentaEntrada.setAdapter(new ListaFazerPedidoAdaptador(getApplicationContext(), listaEmentas));
        lvListaEmentaPrincipal.setAdapter(new ListaFazerPedidoAdaptador(getApplicationContext(), listaEmentas));
        lvListaEmenraSobremesa.setAdapter(new ListaFazerPedidoAdaptador(getApplicationContext(), listaEmentas));

        GenericUtils.setListViewHeightBasedOnItems(lvListaEmentaEntrada);
        GenericUtils.setListViewHeightBasedOnItems(lvListaEmentaPrincipal);
        GenericUtils.setListViewHeightBasedOnItems(lvListaEmenraSobremesa);

        FloatingActionButton fab = findViewById(R.id.fabFinalizarPedido);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FazerPedidoActivity.this, FinalizarPedidoActivity.class);
                startActivity(intent);
            }
        });
    }
}