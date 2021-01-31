package pt.ipleiria.estg.dei.foodlyandroid.vistas;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.navigation.NavigationView;

import java.io.ByteArrayOutputStream;

import de.hdodenhof.circleimageview.CircleImageView;
import pt.ipleiria.estg.dei.foodlyandroid.R;
import pt.ipleiria.estg.dei.foodlyandroid.listeners.ProfileListener;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.Profile;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.SingletonFoodly;

public class MenuMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String USERNAME = "USERNAME";
    public static final String USER = "USER_PREF";
    public static final String PROFILE = "PROFILE";
    public static final String TOKEN = "TOKEN";
    private String username = "";
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private FragmentManager fragmentManager;
    private CircleImageView profile_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_main);




        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navigationView = findViewById(R.id.nav_view);
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,
                toolbar, R.string.ndOpen, R.string.ndClose) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);


                profile_image = drawerView.findViewById(R.id.profile_image);

                Glide.with(getApplicationContext())
                    .load(android.util.Base64.decode(SingletonFoodly.getInstance(getApplicationContext()).getProfile().getImage(), Base64.DEFAULT))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(profile_image);
            }
        };
        drawer.addDrawerListener(toggle);



        fragmentManager = getSupportFragmentManager();

        carregarCabecalho();
        navigationView.setNavigationItemSelectedListener(this);
        toggle.syncState();
        carregarFragmentoInicial();
    }

    private void carregarCabecalho() {
        username = getIntent().getStringExtra(USERNAME);
        SharedPreferences sharedPrefUser = getSharedPreferences(USER, Context.MODE_PRIVATE);

        if (username == null)
            username = sharedPrefUser.getString(USERNAME, "Sem Nome de Utilizador");
        else {
            SharedPreferences.Editor editor = sharedPrefUser.edit();
            editor.putString(USERNAME, username);
            editor.apply();
        }

        View hView = navigationView.getHeaderView(0);
        TextView tvEmail = hView.findViewById(R.id.nav_header_nome);
        tvEmail.setText(username);
    }

    private void carregarFragmentoInicial() {
        navigationView.setCheckedItem(R.id.nav_lista);
        Fragment fragment = new ListaRestaurantesFragment();
        setTitle(getString(R.string.listaRestaurantesTitulo));
        fragmentManager.beginTransaction().replace(R.id.contentFragment, fragment).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.nav_perfil:
                fragment = new PerfilFragment(getApplicationContext());
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
                fragment = new PerfilFragment(getApplicationContext());
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
        builder.setTitle(R.string.logoutTitulo)
                .setMessage(R.string.logoutPerguntaSair)
                .setPositiveButton(R.string.respostaSim, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences sharedPrefUser = getSharedPreferences(MenuMainActivity.PROFILE, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPrefUser.edit();
                        editor.clear();
                        editor.apply();
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