package pt.ipleiria.estg.dei.foodlyandroid.vistas;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import pt.ipleiria.estg.dei.foodlyandroid.R;

public class FinalizarPedidoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalizar_pedido);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Finalizar Pedido");
    }
}