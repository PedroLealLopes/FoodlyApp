package pt.ipleiria.estg.dei.foodlyandroid.vistas;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
    public static final String QUANTITY_ORDER = "QUANTITY_ORDER";
    private ListView lvListaEmentaEntrada, lvListaEmentaSalada, lvListaEmentaPrincipal, lvListaEmenraBebida, lvListaEmenraSobremesa;
    private ImageView btnSub, btnAdd;
    private TextView tvDishName, tvIngredients, tvQuantity;
    private Button btnOK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fazer_pedido);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getString(R.string.fazerPedidoTitulo));

        int restaurantId = getIntent().getIntExtra(ID_RESTAURANTE, -1);

        lvListaEmentaEntrada = findViewById(R.id.listViewEmentaEntradaPedido);
        lvListaEmentaSalada = findViewById(R.id.listViewEmentaSaladaPedido);
        lvListaEmentaPrincipal = findViewById(R.id.listViewEmentaPrincipalPedido);
        lvListaEmenraBebida = findViewById(R.id.listViewEmentaBebidaPedido);
        lvListaEmenraSobremesa = findViewById(R.id.listViewEmentaSobremesaPedido);

        lvListaEmentaEntrada.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialogQuantidade((int) id);
            }
        });
        lvListaEmentaSalada.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialogQuantidade((int) id);
            }
        });
        lvListaEmentaPrincipal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialogQuantidade((int) id);
            }
        });
        lvListaEmenraBebida.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialogQuantidade((int) id);
            }
        });
        lvListaEmenraSobremesa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialogQuantidade((int) id);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fabFinalizarPedido);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FazerPedidoActivity.this, FinalizarPedidoActivity.class);
                startActivity(intent);
            }
        });

        SingletonFoodly.getInstance(getApplicationContext()).setEmentasListener(this);
        SingletonFoodly.getInstance(getApplicationContext()).getAllEmentasAPI(restaurantId, getApplicationContext());
    }

    private void dialogQuantidade(final int id) {
        int quantity = 0;
        String dishName = null;

        final Ementa orderItem = SingletonFoodly.getInstance(getApplicationContext()).getDishItem(id);
        if(orderItem != null){
            quantity = orderItem.getQuantity();
            dishName = orderItem.getName();
        }else{
            dishName = SingletonFoodly.getInstance(getApplicationContext()).getEmenta(id).getName();
        }

        final Dialog dialog = new Dialog(FazerPedidoActivity.this, R.style.CustomDialogTheme);
        LayoutInflater layoutInflater = this.getLayoutInflater();
        View custom_dialog = layoutInflater.inflate(R.layout.custom_dialog_pedido, null);

        tvDishName = custom_dialog.findViewById(R.id.textViewDishNameDialog);
        tvQuantity = custom_dialog.findViewById(R.id.textViewQtyDialog);
        btnSub = custom_dialog.findViewById(R.id.imageViewSubDialog);
        btnAdd = custom_dialog.findViewById(R.id.imageViewAddDialog);
        btnOK = custom_dialog.findViewById(R.id.buttonOKDialog);

        tvQuantity.setText(quantity + "");
        tvDishName.setText(dishName);

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int auxQuantity = Integer.parseInt(tvQuantity.getText().toString());
                if (auxQuantity != 0) {
                    auxQuantity--;
                    tvQuantity.setText(auxQuantity + "");
                } else {
                    Toast.makeText(FazerPedidoActivity.this, "Quantidade não pode ser menor que 0", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int auxQuantity = Integer.parseInt(tvQuantity.getText().toString());
                auxQuantity++;
                tvQuantity.setText(auxQuantity + "");
            }
        });

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(orderItem != null)
                    envioDataAtividade(tvQuantity.getText().toString(), id, true);
                else
                    envioDataAtividade(tvQuantity.getText().toString(), id, false);
                dialog.dismiss();
            }
        });

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(custom_dialog);
        dialog.show();
    }

    private void envioDataAtividade(String quantity, int id, boolean exist) {
        if(exist){
            SingletonFoodly.getInstance(getApplicationContext()).editarQuantidadePedido(id, Integer.parseInt(quantity));
        }else{
            Ementa ementa = SingletonFoodly.getInstance(getApplicationContext()).getEmenta(id);
            Ementa orderItem = new Ementa(ementa.getDishId(), ementa.getName(), ementa.getType(), ementa.getPrice(), ementa.getRestaurantId(), Integer.parseInt(quantity));
            SingletonFoodly.getInstance(getApplicationContext()).adicionarListaPedido(orderItem);
        }
        //TODO ATUALIZAR ADAPTADOR
        Toast.makeText(this, "id: " + id + "q:" +quantity + " " + exist, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefreshListaEmentas(ArrayList<Ementa> ementas) {
        int restaurantId = getIntent().getIntExtra(ID_RESTAURANTE, -1);
        ArrayList<Ementa> type = SingletonFoodly.getInstance(getApplicationContext()).getEmentaType(restaurantId);
        ArrayList<Ementa> starters = new ArrayList<>();
        ArrayList<Ementa> salads = new ArrayList<>();
        ArrayList<Ementa> mainCourse = new ArrayList<>();
        ArrayList<Ementa> drinks = new ArrayList<>();
        ArrayList<Ementa> desserts = new ArrayList<>();

        ArrayList<Ementa> listaPedido = SingletonFoodly.getInstance(getApplicationContext()).getListaPedido();
        for(Ementa e : listaPedido){

        }

        for (Ementa e : type) {
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

        lvListaEmentaEntrada.setAdapter(new ListaFazerPedidoAdaptador(getApplicationContext(), starters));
        lvListaEmentaSalada.setAdapter(new ListaFazerPedidoAdaptador(getApplicationContext(), salads));
        lvListaEmentaPrincipal.setAdapter(new ListaFazerPedidoAdaptador(getApplicationContext(), mainCourse));
        lvListaEmenraBebida.setAdapter(new ListaFazerPedidoAdaptador(getApplicationContext(), drinks));
        lvListaEmenraSobremesa.setAdapter(new ListaFazerPedidoAdaptador(getApplicationContext(), desserts));

        GenericUtils.setListViewHeightBasedOnChildren(lvListaEmentaEntrada);
        GenericUtils.setListViewHeightBasedOnChildren(lvListaEmentaSalada);
        GenericUtils.setListViewHeightBasedOnChildren(lvListaEmentaPrincipal);
        GenericUtils.setListViewHeightBasedOnChildren(lvListaEmenraBebida);
        GenericUtils.setListViewHeightBasedOnChildren(lvListaEmenraSobremesa);
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