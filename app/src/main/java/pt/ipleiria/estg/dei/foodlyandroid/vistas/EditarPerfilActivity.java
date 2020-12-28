package pt.ipleiria.estg.dei.foodlyandroid.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.textfield.TextInputEditText;

import pt.ipleiria.estg.dei.foodlyandroid.R;

public class EditarPerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Editar Perfil");

        TextInputEditText etPassword = findViewById(R.id.editTextPassword);
        etPassword.setEnabled(false);
        //Colocar "*" de acordo com a pass do user
        etPassword.setText("----------");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Voltar para trás
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }

        return true;
    }
}