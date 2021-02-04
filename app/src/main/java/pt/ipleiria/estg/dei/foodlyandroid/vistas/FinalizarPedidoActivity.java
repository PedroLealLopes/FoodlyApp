package pt.ipleiria.estg.dei.foodlyandroid.vistas;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import pt.ipleiria.estg.dei.foodlyandroid.R;
import pt.ipleiria.estg.dei.foodlyandroid.adaptadores.ListaFinalizarPedidoAdaptador;
import pt.ipleiria.estg.dei.foodlyandroid.listeners.PedidosListener;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.Ementa;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.Pedido;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.Review;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.SingletonFoodly;

public class FinalizarPedidoActivity extends AppCompatActivity implements PedidosListener {

    private TextView tvTotalOrder;
    private Button btnPedir;
    private ListView lvListaFinalizarPedido;
    private ArrayList<Pedido> pedidos;
    private Pedido pedido;
    private int orderId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalizar_pedido);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getString(R.string.finalizarPedido));

        lvListaFinalizarPedido = findViewById(R.id.listViewFinalizarPedido);
        tvTotalOrder = findViewById(R.id.textViewTotalOrder);

        SingletonFoodly.getInstance(getApplicationContext()).setPedidosListener(this);
        SingletonFoodly.getInstance(getApplicationContext()).getAllPedidosAPI(getApplicationContext());

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

        SingletonFoodly.getInstance(getApplicationContext()).setPedidosListener(this);

        btnPedir = findViewById(R.id.buttonFinalizarPedido);
        btnPedir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pedido = new Pedido(0,
                        "",
                        SingletonFoodly.getInstance(getApplicationContext()).getProfileId());

                SingletonFoodly.getInstance(getApplicationContext()).adicionarPedidoAPI(getApplicationContext());
                Toast.makeText(FinalizarPedidoActivity.this, "Pedido adicionado com sucesso", Toast.LENGTH_SHORT).show();
                //System.out.println("---> finalOrder:" + SingletonFoodly.getInstance(getApplicationContext()).getListaPedido().toString());
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

    @Override
    public void onRefreshListaPedidos(ArrayList<Pedido> listaPedidos) {
        pedidos = listaPedidos;
    }

    @Override
    public void onRefreshDetalhes(int orderId) {
        System.out.println("---> orderId: " + orderId);
    }

}