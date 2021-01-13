package pt.ipleiria.estg.dei.foodlyandroid.vistas;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import pt.ipleiria.estg.dei.foodlyandroid.R;

public class DetalhesRestauranteActivity extends AppCompatActivity {

    private ChipNavigationBar bottomNav;
    private FragmentManager fragmentManager;
    private static final String TAG = DetalhesRestauranteActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_restaurante);

        //region Bottom Navigation
        bottomNav = findViewById(R.id.bottom_nav);

        if (savedInstanceState == null) {
            bottomNav.setItemSelected(R.id.itemInformacao, true);
            fragmentManager = getSupportFragmentManager();
            RestauranteInfoFragment infoFragment = new RestauranteInfoFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, infoFragment)
                    .commit();
        }

        bottomNav.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int id) {
                Fragment fragment = null;
                switch (id) {
                    case R.id.itemInformacao:
                        fragment = new RestauranteInfoFragment();
                        break;
                    case R.id.itemEmenta:
                        fragment = new RestauranteEmentaFragment();
                        break;
                    case R.id.itemReviews:
                        fragment = new RestauranteReviewFragment();
                        break;
                }

                if (fragment != null) {
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, fragment)
                            .commit();
                } else {
                    Log.e(TAG, "Erro ao criar fragmento");
                }
            }
        });
        //endregion

    }
}