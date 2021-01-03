package pt.ipleiria.estg.dei.foodlyandroid.vistas;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

import pt.ipleiria.estg.dei.foodlyandroid.R;
import pt.ipleiria.estg.dei.foodlyandroid.utils.DatePickerFragment;

public class RegistarActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private static final String[] GENEROS = new String[]{"Masculino", "Feminino"};
    private TextInputEditText etIdade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Criar Conta");

        etIdade = findViewById(R.id.editTextIdade);
        etIdade.setInputType(0);
        AutoCompleteTextView editTextGenero = findViewById(R.id.autoCompleteTextViewGenero);
        ArrayAdapter<String> adaptadorGenero = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, GENEROS);
        editTextGenero.setAdapter(adaptadorGenero);
        editTextGenero.setInputType(0);
        etIdade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        FloatingActionButton fab = findViewById(R.id.fabCriarConta);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistarActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        etIdade.setText(Integer.toString(calcularIdade(c.getTimeInMillis())));
    }

    private int calcularIdade(long data) {
        Calendar calendar = Calendar.getInstance();
        Calendar hoje = Calendar.getInstance();

        calendar.setTimeInMillis(data);

        int idade = hoje.get(Calendar.YEAR) - calendar.get(Calendar.YEAR);

        if (hoje.get(Calendar.DAY_OF_YEAR) < calendar.get(Calendar.DAY_OF_YEAR))
            idade--;

        return idade;
    }
}