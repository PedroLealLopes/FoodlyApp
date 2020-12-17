package pt.ipleiria.estg.dei.foodlyandroid.vistas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import pt.ipleiria.estg.dei.foodlyandroid.R;

public class LoginActivity extends AppCompatActivity {

    private TextView tvEsquecer, tvRegistar;
    private TextInputEditText etEmail, etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.editTextEmail);
        etPassword = findViewById(R.id.editTextPassword);
        tvRegistar = findViewById(R.id.textViewRegistar);

        btnLogin = findViewById(R.id.buttonLogin);
    }

    public void onClickLogin(View view) {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        if (!isEmailValido(email)) {
            etEmail.setError(getString(R.string.etEmailInvalido));
            return;
        }
        if (!isPasswordValida(password)) {
            etPassword.setError(getString(R.string.etPasswordInvalida));
            return;
        }

        Intent intent = new Intent(this, MenuMainActivity.class);
        intent.putExtra("EMAIL", email);
        startActivity(intent);
    }

    private boolean isEmailValido(String email) {
        if (email == null) {
            return false;
        }
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isPasswordValida(String password) {
        if (password == null) {
            return false;
        }
        return password.length() >= 4;
    }

    public void onClickRegistar(View view) {
        Intent intent = new Intent(this, RegistarActivity.class);
        startActivity(intent);
    }
}