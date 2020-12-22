package pt.ipleiria.estg.dei.foodlyandroid.vistas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import pt.ipleiria.estg.dei.foodlyandroid.R;

public class RestauranteInfoFragment extends Fragment {

    private Context context;
    private ImageView ivFav;

    public RestauranteInfoFragment(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurante_info, container, false);

        Button btnFazerPedido = view.findViewById(R.id.buttonFazerPedido);
        btnFazerPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FazerPedidoActivity.class);
                startActivity(intent);
            }
        });

        ivFav = view.findViewById(R.id.imageViewFav);
        ivFav.setTag(R.drawable.ic_restaurante_fav_false);
        ivFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((int) ivFav.getTag() == R.drawable.ic_restaurante_fav_false) {
                    ivFav.setImageResource(R.drawable.ic_restaurante_fav_true);
                    ivFav.setTag(R.drawable.ic_restaurante_fav_true);
                } else {
                    if ((int) ivFav.getTag() == R.drawable.ic_restaurante_fav_true) {
                        ivFav.setImageResource(R.drawable.ic_restaurante_fav_false);
                        ivFav.setTag(R.drawable.ic_restaurante_fav_false);
                    }
                }
            }
        });

        return view;
    }


}