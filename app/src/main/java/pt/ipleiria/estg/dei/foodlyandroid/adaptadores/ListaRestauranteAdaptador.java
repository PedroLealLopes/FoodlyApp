package pt.ipleiria.estg.dei.foodlyandroid.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.foodlyandroid.R;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.Restaurante;

public class ListaRestauranteAdaptador extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Restaurante> restaurantes;

    public ListaRestauranteAdaptador(Context context, ArrayList<Restaurante> livros) {
        this.context = context;
        this.restaurantes = restaurantes;
    }

    @Override
    public int getCount() {
        return restaurantes.size();
    }

    @Override
    public Object getItem(int position) {
        return restaurantes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return restaurantes.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.item_lista_restaurante, null);

        //OTIMIZAÇÃO
        ViewHolderLista viewHolderLista = (ViewHolderLista) convertView.getTag();
        if(viewHolderLista == null){
            viewHolderLista = new ViewHolderLista(convertView);
            convertView.setTag(viewHolderLista);
        }

        viewHolderLista.update(restaurantes.get(position));

        return convertView;
    }

    private class ViewHolderLista {
        private TextView tvNome, tvClassificacao, tvLocalizacao;
        private ImageView ivCapa;

        public ViewHolderLista(View view){
            tvNome = view.findViewById(R.id.textViewNome);
            tvClassificacao = view.findViewById(R.id.textViewClass);
            tvLocalizacao = view.findViewById(R.id.textViewLocalizacao);
            ivCapa = view.findViewById(R.id.imageViewCapa);
        }

        public void update(Restaurante restaurante){
            tvNome.setText(restaurante.getNome());
            //tvClassificacao.setText(restaurante.());
            tvLocalizacao.setText(restaurante.getLocalizacao());
            ivCapa.setImageResource(restaurante.getCapa());
        }
    }
}
