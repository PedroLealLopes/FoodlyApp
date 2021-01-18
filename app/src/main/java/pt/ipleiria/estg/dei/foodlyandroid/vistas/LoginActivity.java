package pt.ipleiria.estg.dei.foodlyandroid.vistas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import pt.ipleiria.estg.dei.foodlyandroid.R;
import pt.ipleiria.estg.dei.foodlyandroid.listeners.LoginListener;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.SingletonFoodly;
import pt.ipleiria.estg.dei.foodlyandroid.utils.ProfileJsonParser;

public class LoginActivity extends AppCompatActivity implements LoginListener {

    private TextView tvEsquecer, tvRegistar;
    private TextInputEditText etUsername, etPassword;
    private CheckBox cboxLembrarConta;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SingletonFoodly.getInstance(getApplicationContext()).setLoginListener(this);

        etUsername = findViewById(R.id.editTextUsername);
        etPassword = findViewById(R.id.editTextPassword);
        tvRegistar = findViewById(R.id.textViewRegistar);

        cboxLembrarConta = findViewById(R.id.checkBoxLembrarConta);
        btnLogin = findViewById(R.id.buttonLogin);


        SharedPreferences sharedPrefUser = getSharedPreferences(MenuMainActivity.PROFILE, Context.MODE_PRIVATE);
        String profile = sharedPrefUser.getString("Profile", "");
        try {
            System.out.println("profile --->" + profile);
            JSONObject profileObject = new JSONObject(profile);
            SingletonFoodly.getInstance(getApplicationContext()).setProfile(ProfileJsonParser.parserJsonProfiles(profileObject));
            onValidateLogin(true, profileObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onClickLogin(View view) {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        if (!isUsernameValido(username)) {
            etUsername.setError(getString(R.string.etUsernameInvalido));
            return;
        }
        if (!isPasswordValida(password)) {
            etPassword.setError(getString(R.string.etPasswordInvalida));
            return;
        }

        password = encodePassword(password);
        if (password != null) {
            SingletonFoodly.getInstance(getApplicationContext()).loginAPI(username, password, getApplicationContext());
        }
    }

    private boolean isUsernameValido(String username) {
        if (username == null) {
            return false;
        }
        return username.length() >= 4;
    }

    private boolean isPasswordValida(String password) {
        if (password == null) {
            return false;
        }
        return password.length() >= 4;
    }

    private String encodePassword(String password) {
        byte[] data = new byte[0];
        try {
            data = password.getBytes("UTF-8");
            String base64 = Base64.encodeToString(data, Base64.DEFAULT);
            return base64;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void onClickRegistar(View view) {
        Intent intent = new Intent(this, RegistarActivity.class);
        startActivity(intent);
    }

    @Override

    public void onValidateLogin(boolean canLogin, JSONObject profile) {
        if(canLogin){
            if(cboxLembrarConta.isChecked()) {
                SharedPreferences sharedPrefUser = getSharedPreferences(MenuMainActivity.PROFILE, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPrefUser.edit();
                editor.putString("Profile", profile + "");
                editor.apply();
            }
            Intent intent = new Intent(this, MenuMainActivity.class);
            try {
                intent.putExtra("USERNAME", profile.getString("username"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Login Invalida", Toast.LENGTH_SHORT).show();
        }

/*
        if (token != null) {
            SharedPreferences sharedPrefUser = getSharedPreferences(MenuMainActivity.USER, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPrefUser.edit();
            editor.putString(MenuMainActivity.USERNAME, email);
            editor.putString(MenuMainActivity.TOKEN, token);
            editor.apply();

            Intent intent = new Intent(getApplicationContext(), MenuMainActivity.class);
            startActivity(intent);
            finish();
        } else
            Toast.makeText(this, "Login inválido", Toast.LENGTH_SHORT).show();\
 */
    }
}