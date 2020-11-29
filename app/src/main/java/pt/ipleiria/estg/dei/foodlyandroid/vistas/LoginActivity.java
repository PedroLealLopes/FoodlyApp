package pt.ipleiria.estg.dei.foodlyandroid.vistas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import pt.ipleiria.estg.dei.foodlyandroid.R;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    TextView tvEsquecer, tvRegistar;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.editTextEmail);
        etPassword = findViewById(R.id.editTextPassword);

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
}