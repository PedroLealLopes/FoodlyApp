package pt.ipleiria.estg.dei.foodlyandroid.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.foodlyandroid.R;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.Ementa;

public class ListaFazerPedidoAdaptador extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Ementa> ementas;
    private TextInputEditText tvItem;

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
        return ementas.get(position).getId();
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

    private class ViewHolderLista {
        private TextInputEditText tvEmenta;
        //declarar a TextView para qtd

        public ViewHolderLista(View view) {
            tvEmenta = view.findViewById(R.id.textViewItemEmenta);
        }

        public void update(Ementa ementa) {
            // Verificar a qtd != 0, visisel ou não visivel, setText
            tvEmenta.setText(ementa.getNome() + "................" + ementa.getPreco() + "€");
        }
    }
}
