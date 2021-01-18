package pt.ipleiria.estg.dei.foodlyandroid.vistas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import pt.ipleiria.estg.dei.foodlyandroid.R;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.Review;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.SingletonFoodly;

public class AdicionarReviewActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView ivEstrela_0_5, ivEstrela_1, ivEstrela_1_5, ivEstrela_2, ivEstrela_2_5, ivEstrela_3, ivEstrela_3_5, ivEstrela_4, ivEstrela_4_5, ivEstrela_5;
    private EditText etDescricao;
    private Button btnSubmit;
    private Review review;
    private double total;
    public static final String ID_RESTAURANTE = "ID_RESTAURANTE";

    public AdicionarReviewActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_review);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getString(R.string.adicionarReviewTitulo));

        etDescricao = findViewById(R.id.editTextDescricaoReview);
        btnSubmit = findViewById(R.id.buttonSubmeterReview);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAdicionar();
            }
        });
    }

    private void dialogAdicionar() {
        final int restaurantId = getIntent().getIntExtra(ID_RESTAURANTE, -1);

        //region CURRENT DATE AND TIME
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final String current_date = df.format(c.getTime());
        //endregion

        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.adicionarReviewCaps)
                .setMessage(R.string.perguntaAdicionarReview)
                .setPositiveButton(R.string.respostaSim, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        review = new Review(restaurantId,
                                SingletonFoodly.getInstance(getApplicationContext()).getProfileId(),
                                total,
                                etDescricao.getText().toString(),
                                current_date,
                                SingletonFoodly.getInstance(getApplicationContext()).getProfile().getUsername(),
                                SingletonFoodly.getInstance(getApplicationContext()).getProfile().getImage());
                        SingletonFoodly.getInstance(getApplicationContext()).adicionarReviewAPI(review, restaurantId, getApplicationContext());
                        Intent intent = new Intent(AdicionarReviewActivity.this, MenuMainActivity.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(AdicionarReviewActivity.this, R.string.reviewSucesso, Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(R.string.respostaNao, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Cancelar
                    }
                })
                .setIcon(R.drawable.ic_adicionar)
                .show();

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
                total = 0.5;
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
                total = 1;
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
                total = 1.5;
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
                total = 2;
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
                total = 2.5;
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
                total = 3;
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
                total = 3.5;
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
                total = 4;
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
                total = 4.5;
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
                total = 5;
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
                total = 0;
                break;
        }
    }
}