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
        private TextView tvTitulo, tvSerie, tvAutor, tvAno;
        private ImageView ivCapa;

        public ViewHolderLista(View view){
            tvTitulo = view.findViewById(R.id.textViewTitulo);
            tvSerie = view.findViewById(R.id.textViewSerie);
            tvAutor = view.findViewById(R.id.textViewAutor);
            tvAno = view.findViewById(R.id.textViewAno);
            ivCapa = view.findViewById(R.id.imageViewLivro);
        }

        public void update(Restaurante restaurante){
            tvTitulo.setText(livro.getTitulo());
            tvSerie.setText(livro.getSerie());
            tvAutor.setText(livro.getAutor());
            tvAno.setText(livro.getAno() + "");
            ivCapa.setImageResource(livro.getCapa());
        }
    }
}
