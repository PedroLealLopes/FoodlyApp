package pt.ipleiria.estg.dei.foodlyandroid.vistas;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

import pt.ipleiria.estg.dei.foodlyandroid.R;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.Profile;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.SingletonFoodly;
import pt.ipleiria.estg.dei.foodlyandroid.utils.DatePickerFragmentUtil;

public class RegistarActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private static final String[] GENEROS = new String[]{"M", "F"};
    private TextInputEditText etUsername, etEmail, etPass, etNomeCompleto, etIdade, etContacto, etMorada, etAlergias;
    private AutoCompleteTextView etGenero;
    private ImageView ivFoto;
    private Button btnAdicionarFoto;
    private Profile profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getString(R.string.criarContaTitulo));

        etUsername = findViewById(R.id.editTextUsernameR);
        etEmail = findViewById(R.id.editTextEmailR);
        etPass = findViewById(R.id.editTextPasswordR);
        etNomeCompleto = findViewById(R.id.editTextNomeCompletoR);
        etGenero = findViewById(R.id.autoCompleteTextViewGeneroR);
        etIdade = findViewById(R.id.editTextIdadeR);
        etContacto = findViewById(R.id.editTextNomeContactoR);
        etMorada = findViewById(R.id.editTextNomeMoradaR);
        etAlergias = findViewById(R.id.editTextNomeAlergiaR);
        ivFoto = findViewById(R.id.imageViewFoto);
        ivFoto.setVisibility(View.GONE);
        ;
        btnAdicionarFoto = findViewById(R.id.buttonAdicionarFoto);
        btnAdicionarFoto.setVisibility(View.GONE);
        ;

        etIdade.setInputType(0);
        AutoCompleteTextView editTextGenero = findViewById(R.id.autoCompleteTextViewGeneroR);
        ArrayAdapter<String> adaptadorGenero = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, GENEROS);
        editTextGenero.setAdapter(adaptadorGenero);
        editTextGenero.setInputType(0);
        etIdade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragmentUtil();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        FloatingActionButton fab = findViewById(R.id.fabCriarConta);
        fab.setImageResource(R.drawable.ic_adicionar);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profile = new Profile(0, etUsername.getText().toString(), etPass.getText().toString(), etEmail.getText().toString(), etNomeCompleto.getText().toString(),
                        etGenero.getText().toString(), etIdade.getText().toString(), etContacto.getText().toString(), etMorada.getText().toString(), etAlergias.getText().toString(), null);

                if (!isUsernameValido(etUsername.getText().toString())) {
                    etUsername.setError(getString(R.string.etUsernameInvalido));
                    return;
                } else if (!isEmailValido(etEmail.getText().toString())) {
                    etEmail.setError(getString(R.string.etEmailInvalido));
                    return;
                } else if (!isPasswordValida(etPass.getText().toString())) {
                    etPass.setError(getString(R.string.etPasswordInvalida));
                    return;
                } else if (!isNomeCompletoValido(etNomeCompleto.getText().toString())) {
                    etNomeCompleto.setError(getString(R.string.etNomeCompletoInvalido));
                    return;
                } else if (!isContactoValido(etContacto.getText().toString())) {
                    etContacto.setError(getString(R.string.etContactoInvalido));
                    return;
                } else if (!isMoradaValida(etMorada.getText().toString())) {
                    etMorada.setError(getString(R.string.etMoradaInvalida));
                    return;
                } else if (!isAlergiasValidas(etAlergias.getText().toString())) {
                    etAlergias.setError(getString(R.string.etAlergiasInvalidas));
                    return;
                } else {
                    SingletonFoodly.getInstance(getApplicationContext()).registarUtilizadorAPI(profile, getApplicationContext());
                    System.out.println("--->profile: " + profile.toString());
                    Intent intent = new Intent(RegistarActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(RegistarActivity.this, R.string.registarSucesso, Toast.LENGTH_SHORT).show();
                }
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

    private boolean isUsernameValido(String username) {
        if (username == null) {
            return false;
        }
        return username.length() >= 4;
    }

    private boolean isEmailValido(String email) {
        if (email == null) {
            return false;
        }
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    private boolean isPasswordValida(String password) {
        if (password == null) {
            return false;
        }
        return password.length() >= 8;
    }

    private boolean isNomeCompletoValido(String nomeCompleto) {
        if (nomeCompleto == null) {
            return false;
        }
        return nomeCompleto.length() >= 4;
    }

    private boolean isContactoValido(String contacto) {
        if (contacto == null) {
            return false;
        }
        return contacto.length() <= 16;
    }

    private boolean isMoradaValida(String morada) {
        if (morada == null) {
            return false;
        }
        return morada.length() >= 4;
    }

    private boolean isAlergiasValidas(String alergias) {
        if (alergias == null) {
            return false;
        }
        return alergias.length() >= 4;
    }
}