package pt.ipleiria.estg.dei.foodlyandroid.vistas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.foodlyandroid.R;
import pt.ipleiria.estg.dei.foodlyandroid.adaptadores.ListaFazerPedidoAdaptador;
import pt.ipleiria.estg.dei.foodlyandroid.listeners.EmentasListener;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.Ementa;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.SingletonFoodly;
import pt.ipleiria.estg.dei.foodlyandroid.utils.GenericUtils;

public class FazerPedidoActivity extends AppCompatActivity implements EmentasListener {

    public static final String ID_RESTAURANTE = "ID_RESTAURANTE";
    private ListView lvListaEmentaEntrada, lvListaEmentaSalada, lvListaEmentaPrincipal, lvListaEmenraBebida, lvListaEmenraSobremesa;

    private ArrayList<Ementa> orderItems;
    private ArrayList<Ementa> listaEmenta;
    private ListaFazerPedidoAdaptador adaptadorListaEntrada, adaptadorListaSalada, adaptadorListaPrincipal, adaptadorListaBebida, adaptadorListaSobremesa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fazer_pedido);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getString(R.string.fazerPedidoTitulo));

        final int restaurantId = getIntent().getIntExtra(ID_RESTAURANTE, -1);

        //inicializei a lista - nesta fase tem 0 eltos
        listaEmenta = new ArrayList<>();

        lvListaEmentaEntrada = findViewById(R.id.listViewEmentaEntradaPedido);
        lvListaEmentaSalada = findViewById(R.id.listViewEmentaSaladaPedido);
        lvListaEmentaPrincipal = findViewById(R.id.listViewEmentaPrincipalPedido);
        lvListaEmenraBebida = findViewById(R.id.listViewEmentaBebidaPedido);
        lvListaEmenraSobremesa = findViewById(R.id.listViewEmentaSobremesaPedido);

        FloatingActionButton fab = findViewById(R.id.fabFinalizarPedido);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (GenericUtils.isConnectionInternet(getApplicationContext())) {
                    orderItems = new ArrayList<>();
                    for (Ementa e : listaEmenta) {
                        if (e.getQuantity() > 0) {
                            orderItems.add(new Ementa(0, e.getDishId(), e.getName(), e.getType(), e.getPrice(), e.getRestaurantId(), e.getQuantity()));
                            SingletonFoodly.getInstance(getApplicationContext()).setListaPedido(orderItems);
                        }
                    }
                    if (orderItems.size() == 0) {
                        Toast.makeText(FazerPedidoActivity.this, R.string.check_finalizarpedido, Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intent = new Intent(FazerPedidoActivity.this, FinalizarPedidoActivity.class);
                        intent.putExtra(FinalizarPedidoActivity.ID_RESTAURANTE, restaurantId);
                        startActivity(intent);
                    }
                } else
                    Toast.makeText(FazerPedidoActivity.this, R.string.noConnection, Toast.LENGTH_SHORT).show();
            }
        });

        //clique da lista
        lvListaEmentaEntrada.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openQuantityDialog((int) id);
            }
        });
        lvListaEmentaSalada.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openQuantityDialog((int) id);
            }
        });
        lvListaEmentaPrincipal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openQuantityDialog((int) id);
            }
        });
        lvListaEmenraBebida.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openQuantityDialog((int) id);
            }
        });
        lvListaEmenraSobremesa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openQuantityDialog((int) id);
            }
        });
        //criação de um adaptador
        adaptadorListaEntrada = new ListaFazerPedidoAdaptador(this, listaEmenta);
        adaptadorListaSalada = new ListaFazerPedidoAdaptador(this, listaEmenta);
        adaptadorListaSalada = new ListaFazerPedidoAdaptador(this, listaEmenta);
        adaptadorListaPrincipal = new ListaFazerPedidoAdaptador(this, listaEmenta);
        adaptadorListaBebida = new ListaFazerPedidoAdaptador(this, listaEmenta);
        adaptadorListaSobremesa = new ListaFazerPedidoAdaptador(this, listaEmenta);

        //o listener para escutar as respostas
        SingletonFoodly.getInstance(getApplicationContext()).setEmentasListener(this);
        //obter lista de livros da API
        SingletonFoodly.getInstance(getApplicationContext()).getAllEmentasAPI(restaurantId, getApplicationContext());
    }

    private void openQuantityDialog(final int id) {
        Ementa ementa = null;
        for (Ementa e : listaEmenta)
            if (e.getDishId() == id) {
                ementa = e;
                break;
            }
        if (ementa == null)
            return;
        final int quantInicial = ementa.getQuantity();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(ementa.getName());
        final View customLayout = getLayoutInflater().inflate(R.layout.custom_dialog_pedido, null);
        builder.setView(customLayout);
        final EditText etQuantidade = customLayout.findViewById(R.id.etQuantidade);
        etQuantidade.setText(ementa.getQuantity() + "");
        final Button btnSoma = customLayout.findViewById(R.id.btnSoma);
        btnSoma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int val = Integer.parseInt(etQuantidade.getText().toString());
                etQuantidade.setText(val + 1 + "");
            }
        });
        final Button btnSub = customLayout.findViewById(R.id.btnSub);
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int val = Integer.parseInt(etQuantidade.getText().toString());
                if (val > 0)
                    etQuantidade.setText(val - 1 + "");
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int quantFinal = Integer.parseInt(etQuantidade.getText().toString());
                //só envia resposta se houve alteração na quantidade
                if (quantInicial != quantFinal) {
                    // envio de info para o fragmento
                    envioDataFragmento(quantFinal, id);
                    //System.out.println("--->Id: " + id + " quant: " + quantFinal);
                }
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void envioDataFragmento(int quant, int id) {
        //atualiza lista de pratos
        for (Ementa e : listaEmenta)
            if (e.getDishId() == id) {
                e.setQuantity(quant);
                adaptadorListaEntrada.notifyDataSetChanged();
                adaptadorListaSalada.notifyDataSetChanged();
                adaptadorListaPrincipal.notifyDataSetChanged();
                adaptadorListaBebida.notifyDataSetChanged();
                adaptadorListaSobremesa.notifyDataSetChanged();
                break;
            }
    }

    @Override
    public void onRefreshListaEmentas(ArrayList<Ementa> ementas) {
        if (ementas != null) {
            ArrayList<Ementa> starters = new ArrayList<>();
            ArrayList<Ementa> salads = new ArrayList<>();
            ArrayList<Ementa> mainCourse = new ArrayList<>();
            ArrayList<Ementa> drinks = new ArrayList<>();
            ArrayList<Ementa> desserts = new ArrayList<>();

            this.listaEmenta = ementas;

            for (Ementa e : ementas) {
                if (e.getType().equals(getString(R.string.STARTERS)))
                    starters.add(e);
                if (e.getType().equals(getString(R.string.SALADS)))
                    salads.add(e);
                if (e.getType().equals(getString(R.string.MAINCOURSE)))
                    mainCourse.add(e);
                if (e.getType().equals(getString(R.string.DRINKS)))
                    drinks.add(e);
                if (e.getType().equals(getString(R.string.DESSERTS)))
                    desserts.add(e);
            }

            adaptadorListaEntrada = new ListaFazerPedidoAdaptador(getApplicationContext(), starters);
            lvListaEmentaEntrada.setAdapter(adaptadorListaEntrada);

            adaptadorListaSalada = new ListaFazerPedidoAdaptador(getApplicationContext(), salads);
            lvListaEmentaSalada.setAdapter(adaptadorListaSalada);

            adaptadorListaPrincipal = new ListaFazerPedidoAdaptador(getApplicationContext(), mainCourse);
            lvListaEmentaPrincipal.setAdapter(adaptadorListaPrincipal);

            adaptadorListaBebida = new ListaFazerPedidoAdaptador(getApplicationContext(), drinks);
            lvListaEmenraBebida.setAdapter(adaptadorListaBebida);

            adaptadorListaSobremesa = new ListaFazerPedidoAdaptador(getApplicationContext(), desserts);
            lvListaEmenraSobremesa.setAdapter(adaptadorListaSobremesa);


            GenericUtils.setListViewHeightBasedOnChildren(lvListaEmentaEntrada);
            GenericUtils.setListViewHeightBasedOnChildren(lvListaEmentaSalada);
            GenericUtils.setListViewHeightBasedOnChildren(lvListaEmentaPrincipal);
            GenericUtils.setListViewHeightBasedOnChildren(lvListaEmenraBebida);
            GenericUtils.setListViewHeightBasedOnChildren(lvListaEmenraSobremesa);
        }
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