package pt.ipleiria.estg.dei.foodlyandroid.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.foodlyandroid.R;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.Ementa;
import pt.ipleiria.estg.dei.foodlyandroid.vistas.FazerPedidoActivity;

public class ListaFazerPedidoAdaptador extends BaseAdapter {

    private final Context context;
    private LayoutInflater inflater;
    private final ArrayList<Ementa> ementas;

    public ListaFazerPedidoAdaptador(Context context, ArrayList<Ementa> ementas) {
        this.context = context;
        this.ementas = ementas;
    }

    @Override
    public int getCount() {
        return ementas.size();
    }

    @Override
    public Object getItem(int position) {
        return ementas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return ementas.get(position).getDishId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.item_lista_fazer_pedido, null);

        //OTIMIZAÇÃO
        ViewHolderLista viewHolderLista = (ViewHolderLista) convertView.getTag();
        if (viewHolderLista == null) {
            viewHolderLista = new ViewHolderLista(convertView);
            convertView.setTag(viewHolderLista);
        }

        viewHolderLista.update(ementas.get(position));

        return convertView;
    }

    public static class ViewHolderLista {
        private final TextView tvDishName, tvDishPrice, tvQuantity;
        private int quantity;

        public ViewHolderLista(View view) {
            tvDishName = view.findViewById(R.id.textViewDishNameOrder);
            tvDishPrice = view.findViewById(R.id.textViewDishPriceOrder);
            tvQuantity = view.findViewById(R.id.textViewQtdOrder);

        }

        public void update(Ementa ementa) {
            quantity = Integer.parseInt(tvQuantity.getText().toString());

            tvDishName.setText(ementa.getName());
            tvDishPrice.setText(ementa.getPrice() + " €");
            if(quantity == 0){
                tvQuantity.setVisibility(View.INVISIBLE);
            }
        }
    }
}
