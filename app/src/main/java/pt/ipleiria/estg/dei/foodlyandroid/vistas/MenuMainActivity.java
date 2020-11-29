package pt.ipleiria.estg.dei.foodlyandroid.vistas;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;

import pt.ipleiria.estg.dei.foodlyandroid.R;

public class MenuMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String EMAIL = "EMAIL";
    private static final String USER = "USER_PREF";
    private String email = "";
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navigationView = findViewById(R.id.nav_view);
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,
                toolbar, R.string.ndOpen, R.string.ndClose);
        toggle.syncState();
        drawer.addDrawerListener(toggle);

        fragmentManager = getSupportFragmentManager();

        carregarCabecalho();
        navigationView.setNavigationItemSelectedListener(this);

        carregarFragmentoInicial();
    }

    private void carregarCabecalho() {
        email = getIntent().getStringExtra(EMAIL);
        SharedPreferences sharedPrefUser = getSharedPreferences(USER, Context.MODE_PRIVATE);

        if (email == null)
            email = sharedPrefUser.getString(EMAIL, "Sem Nome de Utilizador");
        else {
            SharedPreferences.Editor editor = sharedPrefUser.edit();
            editor.putString(EMAIL, email);
            editor.apply();
        }

        View hView = navigationView.getHeaderView(0);
        TextView tvEmail = hView.findViewById(R.id.nav_header_nome);
        tvEmail.setText(email);
    }

    private void carregarFragmentoInicial() {
        navigationView.setCheckedItem(R.id.nav_lista);
        Fragment fragment = new ListaRestaurantesFragment();
        setTitle("Lista Restaurantes");
        fragmentManager.beginTransaction().replace(R.id.contentFragment, fragment).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.nav_perfil:
                fragment = new PerfilFragment();
                setTitle(item.getTitle());
                break;
            case R.id.nav_lista:
                fragment = new ListaRestaurantesFragment();
                setTitle(item.getTitle());
                break;
            case R.id.nav_logout:
                dialogLogout();
                break;
            default:
                fragment = new PerfilFragment();
        }

        if (fragment != null) {
            fragmentManager.beginTransaction().replace(R.id.contentFragment, fragment).commit();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void dialogLogout() {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);
        builder.setTitle("Logout")
                .setMessage("Deseja mesmo sair?")
                .setPositiveButton(R.string.respostaSim, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MenuMainActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })

                .setNegativeButton(R.string.respostaNao, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setIcon(R.drawable.ic_logout)
                .show();
    }
}