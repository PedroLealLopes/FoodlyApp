package pt.ipleiria.estg.dei.foodlyandroid.vistas;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import pt.ipleiria.estg.dei.foodlyandroid.R;

public class SplashScreenActivity extends AppCompatActivity {

    private int SLAPSH_TIME_OUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, SLAPSH_TIME_OUT);
    }
}