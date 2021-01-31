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
import pt.ipleiria.estg.dei.foodlyandroid.modelos.SingletonFoodly;

public class ListaFinalizarPedidoAdaptador extends BaseAdapter {

    private final Context context;
    private LayoutInflater inflater;
    private final ArrayList<Ementa> listaEmenta;

    public ListaFinalizarPedidoAdaptador(Context context, ArrayList<Ementa> listaEmenta) {
        this.context = context;
        this.listaEmenta = listaEmenta;
    }

    @Override
    public int getCount() {
        return listaEmenta.size();
    }

    @Override
    public Object getItem(int position) {
        return listaEmenta.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaEmenta.get(position).getDishId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.item_lista_finalizar_pedido, null);

        //OTIMIZAÇÃO
        ViewHolderLista viewHolderLista = (ViewHolderLista) convertView.getTag();
        if (viewHolderLista == null) {
            viewHolderLista = new ViewHolderLista(convertView);
            convertView.setTag(viewHolderLista);
        }

        viewHolderLista.update(listaEmenta.get(position));

        return convertView;
    }

    public static class ViewHolderLista {
        private final TextView tvDishName, tvQuantity, tvDishPrice, tvTotal;

        public ViewHolderLista(View view) {
            tvDishName = view.findViewById(R.id.textViewDishNameFP);
            tvQuantity = view.findViewById(R.id.textViewDishQuantityFP);
            tvDishPrice = view.findViewById(R.id.textViewDishPriceFP);
            tvTotal = view.findViewById(R.id.textViewDishTotalFP);
        }

        public void update(Ementa ementa) {
            double total = ementa.getPrice() * ementa.getQuantity();
            tvDishName.setText(ementa.getName());
            tvQuantity.setText(ementa.getQuantity() + "");
            tvDishPrice.setText(ementa.getPrice() + " €");
            tvTotal.setText(String.format("%.1f", total) + "€");
        }
    }
}
