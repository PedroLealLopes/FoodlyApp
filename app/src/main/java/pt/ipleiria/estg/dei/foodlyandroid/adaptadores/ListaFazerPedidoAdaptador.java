package pt.ipleiria.estg.dei.foodlyandroid.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.foodlyandroid.R;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.Ementa;

public class ListaFazerPedidoAdaptador extends BaseAdapter {

    private final Context context;
    private LayoutInflater inflater;
    private final ArrayList<Ementa> orderItems;

    public ListaFazerPedidoAdaptador(Context context, ArrayList<Ementa> orderItems) {
        this.context = context;
        this.orderItems = orderItems;
    }

    @Override
    public int getCount() {
        return orderItems.size();
    }

    @Override
    public Object getItem(int position) {
        return orderItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return orderItems.get(position).getDishId();
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

        viewHolderLista.update(orderItems.get(position));

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

        public void update(Ementa orderItems) {
            quantity = Integer.parseInt(orderItems.getQuantity() + "");

            tvDishName.setText(orderItems.getName());
            tvDishPrice.setText(orderItems.getPrice() + " €");
            if (quantity == 0) {
                tvQuantity.setVisibility(View.INVISIBLE);
            } else {
                tvQuantity.setVisibility(View.VISIBLE);
                tvQuantity.setText(quantity + "");
            }
        }
    }
}
