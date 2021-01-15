package pt.ipleiria.estg.dei.foodlyandroid.vistas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import org.json.JSONException;
import org.json.JSONObject;

import pt.ipleiria.estg.dei.foodlyandroid.R;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.Profile;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.SingletonFoodly;
import pt.ipleiria.estg.dei.foodlyandroid.utils.GenericUtils;
import pt.ipleiria.estg.dei.foodlyandroid.utils.ProfileJsonParser;

public class PerfilFragment extends Fragment {

    private Context context;
    private ChipNavigationBar bottomNav;
    private FragmentManager fragmentManager;
    private static final String TAG = PerfilFragment.class.getSimpleName();
    private TextInputEditText editTextUsername, editTextIdadeProfile, editTextNomeAlergiaProfile,
            editTextGeneroProfile,editTextNomeContactoProfile, editTextNomeMoradaProfile, editTextEmailProfile, editTextNomeCompletoProfile;

    public PerfilFragment(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        Profile profile = SingletonFoodly.getInstance(context).getProfile();
        Toast.makeText(context, "Profile: "  + profile.toString(), Toast.LENGTH_SHORT).show();
        editTextIdadeProfile = view.findViewById(R.id.editTextIdadeProfile);
        editTextNomeAlergiaProfile = view.findViewById(R.id.editTextNomeAlergiaProfile);
        editTextGeneroProfile = view.findViewById(R.id.editTextGeneroProfile);
        editTextNomeContactoProfile = view.findViewById(R.id.editTextNomeContactoProfile);
        editTextNomeMoradaProfile = view.findViewById(R.id.editTextNomeMoradaProfile);
        editTextNomeCompletoProfile = view.findViewById(R.id.editTextNomeCompletoProfile);
        editTextUsername = view.findViewById(R.id.editTextUsernameProfile);
        editTextEmailProfile = view.findViewById(R.id.editTextEmailProfile);



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


        if(profile != null){
            //editTextUsername.setText(profile.getUsername());
            editTextEmailProfile.setText(profile.getEmail());
            editTextIdadeProfile.setText(profile.getAge());
            editTextNomeAlergiaProfile.setText(profile.getAlergias());
            editTextGeneroProfile.setText(profile.getGenero());
            editTextNomeContactoProfile.setText(profile.getTelefone());
            editTextNomeMoradaProfile.setText(profile.getMorada());
            editTextNomeCompletoProfile.setText(profile.getFullname());
        }

        Button btnEditar = view.findViewById(R.id.buttonEditar);
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), EditarPerfilActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}