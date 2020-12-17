package pt.ipleiria.estg.dei.foodlyandroid.vistas;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import pt.ipleiria.estg.dei.foodlyandroid.R;

public class AdicionarReviewActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView ivEstrela_0_5, ivEstrela_1, ivEstrela_1_5, ivEstrela_2, ivEstrela_2_5, ivEstrela_3, ivEstrela_3_5, ivEstrela_4, ivEstrela_4_5, ivEstrela_5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_review);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Adicionar Review");
    }

    public void onClick(View view) {
        ivEstrela_0_5 = findViewById(R.id.imageViewEstrela0_5);
        ivEstrela_1 = findViewById(R.id.imageViewEstrela1);
        ivEstrela_1_5 = findViewById(R.id.imageViewEstrela1_5);
        ivEstrela_2 = findViewById(R.id.imageViewEstrela2);
        ivEstrela_2_5 = findViewById(R.id.imageViewEstrela2_5);
        ivEstrela_3 = findViewById(R.id.imageViewEstrela3);
        ivEstrela_3_5 = findViewById(R.id.imageViewEstrela3_5);
        ivEstrela_4 = findViewById(R.id.imageViewEstrela4);
        ivEstrela_4_5 = findViewById(R.id.imageViewEstrela4_5);
        ivEstrela_5 = findViewById(R.id.imageViewEstrela5);

        switch (view.getId()) {
            case R.id.imageViewEstrela0_5:
                //HIGHLIGHTS
                ivEstrela_0_5.setImageResource(R.drawable.estrela_roxo_esquerda);

                //OFF
                ivEstrela_1.setImageResource(R.drawable.estrela_cinza_direita);
                ivEstrela_1_5.setImageResource(R.drawable.estrela_cinza_esquerda);
                ivEstrela_2.setImageResource(R.drawable.estrela_cinza_direita);
                ivEstrela_2_5.setImageResource(R.drawable.estrela_cinza_esquerda);
                ivEstrela_3.setImageResource(R.drawable.estrela_cinza_direita);
                ivEstrela_3_5.setImageResource(R.drawable.estrela_cinza_esquerda);
                ivEstrela_4.setImageResource(R.drawable.estrela_cinza_direita);
                ivEstrela_4_5.setImageResource(R.drawable.estrela_cinza_esquerda);
                ivEstrela_5.setImageResource(R.drawable.estrela_cinza_direita);
                break;
            case R.id.imageViewEstrela1:
                //HIGHLIGHTS
                ivEstrela_0_5.setImageResource(R.drawable.estrela_roxo_esquerda);
                ivEstrela_1.setImageResource(R.drawable.estrela_roxo_direita);

                //OFF
                ivEstrela_1_5.setImageResource(R.drawable.estrela_cinza_esquerda);
                ivEstrela_2.setImageResource(R.drawable.estrela_cinza_direita);
                ivEstrela_2_5.setImageResource(R.drawable.estrela_cinza_esquerda);
                ivEstrela_3.setImageResource(R.drawable.estrela_cinza_direita);
                ivEstrela_3_5.setImageResource(R.drawable.estrela_cinza_esquerda);
                ivEstrela_4.setImageResource(R.drawable.estrela_cinza_direita);
                ivEstrela_4_5.setImageResource(R.drawable.estrela_cinza_esquerda);
                ivEstrela_5.setImageResource(R.drawable.estrela_cinza_direita);
                break;
            case R.id.imageViewEstrela1_5:
                //HIGHLIGHTS
                ivEstrela_0_5.setImageResource(R.drawable.estrela_roxo_esquerda);
                ivEstrela_1.setImageResource(R.drawable.estrela_roxo_direita);
                ivEstrela_1_5.setImageResource(R.drawable.estrela_roxo_esquerda);

                //OFF
                ivEstrela_2.setImageResource(R.drawable.estrela_cinza_direita);
                ivEstrela_2_5.setImageResource(R.drawable.estrela_cinza_esquerda);
                ivEstrela_3.setImageResource(R.drawable.estrela_cinza_direita);
                ivEstrela_3_5.setImageResource(R.drawable.estrela_cinza_esquerda);
                ivEstrela_4.setImageResource(R.drawable.estrela_cinza_direita);
                ivEstrela_4_5.setImageResource(R.drawable.estrela_cinza_esquerda);
                ivEstrela_5.setImageResource(R.drawable.estrela_cinza_direita);
                break;
            case R.id.imageViewEstrela2:
                //HIGHLIGHTS
                ivEstrela_0_5.setImageResource(R.drawable.estrela_roxo_esquerda);
                ivEstrela_1.setImageResource(R.drawable.estrela_roxo_direita);
                ivEstrela_1_5.setImageResource(R.drawable.estrela_roxo_esquerda);
                ivEstrela_2.setImageResource(R.drawable.estrela_roxo_direita);

                //OFF
                ivEstrela_2_5.setImageResource(R.drawable.estrela_cinza_esquerda);
                ivEstrela_3.setImageResource(R.drawable.estrela_cinza_direita);
                ivEstrela_3_5.setImageResource(R.drawable.estrela_cinza_esquerda);
                ivEstrela_4.setImageResource(R.drawable.estrela_cinza_direita);
                ivEstrela_4_5.setImageResource(R.drawable.estrela_cinza_esquerda);
                ivEstrela_5.setImageResource(R.drawable.estrela_cinza_direita);
                break;
            case R.id.imageViewEstrela2_5:
                //HIGHLIGHTS
                ivEstrela_0_5.setImageResource(R.drawable.estrela_roxo_esquerda);
                ivEstrela_1.setImageResource(R.drawable.estrela_roxo_direita);
                ivEstrela_1_5.setImageResource(R.drawable.estrela_roxo_esquerda);
                ivEstrela_2.setImageResource(R.drawable.estrela_roxo_direita);
                ivEstrela_2_5.setImageResource(R.drawable.estrela_roxo_esquerda);

                //OFF
                ivEstrela_3.setImageResource(R.drawable.estrela_cinza_direita);
                ivEstrela_3_5.setImageResource(R.drawable.estrela_cinza_esquerda);
                ivEstrela_4.setImageResource(R.drawable.estrela_cinza_direita);
                ivEstrela_4_5.setImageResource(R.drawable.estrela_cinza_esquerda);
                ivEstrela_5.setImageResource(R.drawable.estrela_cinza_direita);
                break;
            case R.id.imageViewEstrela3:
                //HIGHLIGHTS
                ivEstrela_0_5.setImageResource(R.drawable.estrela_roxo_esquerda);
                ivEstrela_1.setImageResource(R.drawable.estrela_roxo_direita);
                ivEstrela_1_5.setImageResource(R.drawable.estrela_roxo_esquerda);
                ivEstrela_2.setImageResource(R.drawable.estrela_roxo_direita);
                ivEstrela_2_5.setImageResource(R.drawable.estrela_roxo_esquerda);
                ivEstrela_3.setImageResource(R.drawable.estrela_roxo_direita);

                //OFF
                ivEstrela_3_5.setImageResource(R.drawable.estrela_cinza_esquerda);
                ivEstrela_4.setImageResource(R.drawable.estrela_cinza_direita);
                ivEstrela_4_5.setImageResource(R.drawable.estrela_cinza_esquerda);
                ivEstrela_5.setImageResource(R.drawable.estrela_cinza_direita);
                break;
            case R.id.imageViewEstrela3_5:
                //HIGHLIGHTS
                ivEstrela_0_5.setImageResource(R.drawable.estrela_roxo_esquerda);
                ivEstrela_1.setImageResource(R.drawable.estrela_roxo_direita);
                ivEstrela_1_5.setImageResource(R.drawable.estrela_roxo_esquerda);
                ivEstrela_2.setImageResource(R.drawable.estrela_roxo_direita);
                ivEstrela_2_5.setImageResource(R.drawable.estrela_roxo_esquerda);
                ivEstrela_3.setImageResource(R.drawable.estrela_roxo_direita);
                ivEstrela_3_5.setImageResource(R.drawable.estrela_roxo_esquerda);

                //OFF
                ivEstrela_4.setImageResource(R.drawable.estrela_cinza_direita);
                ivEstrela_4_5.setImageResource(R.drawable.estrela_cinza_esquerda);
                ivEstrela_5.setImageResource(R.drawable.estrela_cinza_direita);
                break;
            case R.id.imageViewEstrela4:
                //HIGHLIGHTS
                ivEstrela_0_5.setImageResource(R.drawable.estrela_roxo_esquerda);
                ivEstrela_1.setImageResource(R.drawable.estrela_roxo_direita);
                ivEstrela_1_5.setImageResource(R.drawable.estrela_roxo_esquerda);
                ivEstrela_2.setImageResource(R.drawable.estrela_roxo_direita);
                ivEstrela_2_5.setImageResource(R.drawable.estrela_roxo_esquerda);
                ivEstrela_3.setImageResource(R.drawable.estrela_roxo_direita);
                ivEstrela_3_5.setImageResource(R.drawable.estrela_roxo_esquerda);
                ivEstrela_4.setImageResource(R.drawable.estrela_roxo_direita);

                //OFF
                ivEstrela_4_5.setImageResource(R.drawable.estrela_cinza_esquerda);
                ivEstrela_5.setImageResource(R.drawable.estrela_cinza_direita);
                break;
            case R.id.imageViewEstrela4_5:
                //HIGHLIGHTS
                ivEstrela_0_5.setImageResource(R.drawable.estrela_roxo_esquerda);
                ivEstrela_1.setImageResource(R.drawable.estrela_roxo_direita);
                ivEstrela_1_5.setImageResource(R.drawable.estrela_roxo_esquerda);
                ivEstrela_2.setImageResource(R.drawable.estrela_roxo_direita);
                ivEstrela_2_5.setImageResource(R.drawable.estrela_roxo_esquerda);
                ivEstrela_3.setImageResource(R.drawable.estrela_roxo_direita);
                ivEstrela_3_5.setImageResource(R.drawable.estrela_roxo_esquerda);
                ivEstrela_4.setImageResource(R.drawable.estrela_roxo_direita);
                ivEstrela_4_5.setImageResource(R.drawable.estrela_roxo_esquerda);

                //OFF
                ivEstrela_5.setImageResource(R.drawable.estrela_cinza_direita);
                break;
            case R.id.imageViewEstrela5:
                //HIGHLIGHTS
                ivEstrela_0_5.setImageResource(R.drawable.estrela_roxo_esquerda);
                ivEstrela_1.setImageResource(R.drawable.estrela_roxo_direita);
                ivEstrela_1_5.setImageResource(R.drawable.estrela_roxo_esquerda);
                ivEstrela_2.setImageResource(R.drawable.estrela_roxo_direita);
                ivEstrela_2_5.setImageResource(R.drawable.estrela_roxo_esquerda);
                ivEstrela_3.setImageResource(R.drawable.estrela_roxo_direita);
                ivEstrela_3_5.setImageResource(R.drawable.estrela_roxo_esquerda);
                ivEstrela_4.setImageResource(R.drawable.estrela_roxo_direita);
                ivEstrela_4_5.setImageResource(R.drawable.estrela_roxo_esquerda);
                ivEstrela_5.setImageResource(R.drawable.estrela_roxo_direita);
                break;
            default:
                break;
        }
    }
}