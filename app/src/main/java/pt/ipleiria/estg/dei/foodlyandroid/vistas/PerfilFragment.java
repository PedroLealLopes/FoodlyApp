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
import pt.ipleiria.estg.dei.foodlyandroid.utils.GenericUtils;

public class PerfilFragment extends Fragment {

    private Context context;
    private ChipNavigationBar bottomNav;
    private FragmentManager fragmentManager;
    private static final String TAG = PerfilFragment.class.getSimpleName();
    private static RequestQueue volleyQueue;
    private static final String mUrlAPIProfile = "http://192.168.1.229/FoodlyWeb/frontend/web/api/profiles/1";
    private TextInputEditText editTextUsername, editTextIdadeProfile, editTextNomeAlergiaProfile,
            editTextGeneroProfile,editTextNomeContactoProfile, editTextNomeMoradaProfile;

    public PerfilFragment(Context context) {
        this.context = context;
        volleyQueue = Volley.newRequestQueue(context);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);





        getProfileAPI(getContext());
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

        return view;
    }

    public void getProfileAPI(final Context context) {
        final String[] profile = new String[1];
        if (!GenericUtils.isConnectionInternet(context)) {
            Toast.makeText(context, "Não há internet", Toast.LENGTH_SHORT).show();
        } else {
            JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, mUrlAPIProfile, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        String userId = response.getString("userId");
                        String fullname = response.getString("fullname");
                        String age = response.getString("age");
                        String alergias = response.getString("alergias");
                        String genero = response.getString("genero");
                        String telefone = response.getString("telefone");
                        String morada = response.getString("morada");


                        editTextUsername = getView().findViewById(R.id.editTextUsernameProfile);
                        editTextIdadeProfile = getView().findViewById(R.id.editTextIdadeProfile);
                        editTextNomeAlergiaProfile = getView().findViewById(R.id.editTextNomeAlergiaProfile);
                        editTextGeneroProfile = getView().findViewById(R.id.editTextGeneroProfile);
                        editTextNomeContactoProfile = getView().findViewById(R.id.editTextNomeContactoProfile);
                        editTextNomeMoradaProfile = getView().findViewById(R.id.editTextNomeMoradaProfile);


                        editTextUsername.setText(fullname);
                        editTextIdadeProfile.setText(age);
                        editTextNomeAlergiaProfile.setText(alergias);
                        editTextGeneroProfile.setText(genero);
                        editTextNomeContactoProfile.setText(telefone);
                        editTextNomeMoradaProfile.setText(morada);

                        //Toast.makeText(context, fullname, Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }}, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            volleyQueue.add(req);
        }
    }
}