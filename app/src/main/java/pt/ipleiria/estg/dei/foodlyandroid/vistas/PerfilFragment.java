package pt.ipleiria.estg.dei.foodlyandroid.vistas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.Base64;

import pt.ipleiria.estg.dei.foodlyandroid.R;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.Profile;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.SingletonFoodly;

public class PerfilFragment extends Fragment {

    private Context context;
    private ChipNavigationBar bottomNav;
    private FragmentManager fragmentManager;
    private static final String TAG = PerfilFragment.class.getSimpleName();
    private ImageView imageViewProfilePic;

    public PerfilFragment(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        System.out.println("--> onCreateView PerfilFragment");

        //region Bottom Navigation
        bottomNav = view.findViewById(R.id.bottom_nav);

        if (savedInstanceState == null) {
            bottomNav.setItemSelected(R.id.itemInformacao, true);
            fragmentManager = getFragmentManager();
            PerfilTabInfoFragment infoFragment = new PerfilTabInfoFragment();
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
                        fragment = new PerfilTabInfoFragment();
                        break;
                    case R.id.itemFav:
                        fragment = new PerfilTabFavFragment();
                        break;
                    case R.id.itemReviews:
                        fragment = new PerfilTabReviewFragment();
                        break;
                }

                if (fragment != null) {
                    fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, fragment)
                            .commit();
                } else {
                    Log.e(TAG, "Erro ao criar fragmento");
                }
            }
        });
        //endregion

        Button btnEditar = view.findViewById(R.id.buttonEditar);
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), EditarPerfilActivity.class);
                startActivity(intent);
            }
        });

        imageViewProfilePic = view.findViewById(R.id.imageViewProfilePic);


        byte[] image = Base64.getDecoder().decode(SingletonFoodly.getInstance(context).getProfile().getImage());
        Glide.with(this)
                .load(image)
                .placeholder(R.drawable.gordon)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageViewProfilePic);

        return view;
    }
}