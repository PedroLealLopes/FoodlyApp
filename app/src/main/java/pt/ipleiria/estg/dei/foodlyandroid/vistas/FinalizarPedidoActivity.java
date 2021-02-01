package pt.ipleiria.estg.dei.foodlyandroid.vistas;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.foodlyandroid.R;
import pt.ipleiria.estg.dei.foodlyandroid.adaptadores.ListaFinalizarPedidoAdaptador;
import pt.ipleiria.estg.dei.foodlyandroid.adaptadores.ListaRestauranteAdaptador;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.Ementa;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.SingletonFoodly;

public class FinalizarPedidoActivity extends AppCompatActivity {

    private TextView tvTotalOrder;
    private Button btnPedir;
    private ListView lvListaFinalizarPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalizar_pedido);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getString(R.string.finalizarPedido));

        lvListaFinalizarPedido = findViewById(R.id.listViewFinalizarPedido);
        tvTotalOrder = findViewById(R.id.textViewTotalOrder);

        final ArrayList<Ementa> listaEmenta = SingletonFoodly.getInstance(getApplicationContext()).getListaPedido();
        if (listaEmenta != null)
            lvListaFinalizarPedido.setAdapter(new ListaFinalizarPedidoAdaptador(getApplicationContext(), listaEmenta));

        double totalPriceOrder = 0;
        for (int i = 0; i < listaEmenta.size(); i++) {
            int quantItem = listaEmenta.get(i).getQuantity();
            double priceItem = listaEmenta.get(i).getPrice();
            double totalPriceItem = quantItem * priceItem;
            totalPriceOrder = totalPriceItem + totalPriceOrder;
        }
        tvTotalOrder.setText(totalPriceOrder + "€");

        btnPedir = findViewById(R.id.buttonFinalizarPedido);
        btnPedir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SingletonFoodly.getInstance(getApplicationContext()).adicionarPedidoAPI(, getApplicationContext());
                Toast.makeText(FinalizarPedidoActivity.this, "Pedido adicionado com sucesso", Toast.LENGTH_SHORT).show();
                System.out.println("---> finalOrder:" + SingletonFoodly.getInstance(getApplicationContext()).getListaPedido().toString());
                //TODO GO TO FRAGMENT
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Voltar para trás
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}