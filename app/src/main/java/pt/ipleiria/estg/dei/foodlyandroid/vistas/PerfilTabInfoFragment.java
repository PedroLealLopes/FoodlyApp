package pt.ipleiria.estg.dei.foodlyandroid.vistas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

import pt.ipleiria.estg.dei.foodlyandroid.R;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.Profile;
import pt.ipleiria.estg.dei.foodlyandroid.modelos.SingletonFoodly;

public class PerfilTabInfoFragment extends Fragment {

    private TextInputEditText editTextUsername, editTextIdadeProfile, editTextNomeAlergiaProfile,
            editTextGeneroProfile, editTextNomeContactoProfile, editTextNomeMoradaProfile, editTextEmailProfile, editTextNomeCompletoProfile;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil_tab_info, container, false);

        editTextIdadeProfile = view.findViewById(R.id.editTextIdadeProfile);
        editTextNomeAlergiaProfile = view.findViewById(R.id.editTextNomeAlergiaProfile);
        editTextGeneroProfile = view.findViewById(R.id.editTextGeneroProfile);
        editTextNomeContactoProfile = view.findViewById(R.id.editTextNomeContactoProfile);
        editTextNomeMoradaProfile = view.findViewById(R.id.editTextNomeMoradaProfile);
        editTextNomeCompletoProfile = view.findViewById(R.id.editTextNomeCompletoProfile);
        editTextUsername = view.findViewById(R.id.editTextUsernameProfile);
        editTextEmailProfile = view.findViewById(R.id.editTextEmailProfile);


        Profile profile = SingletonFoodly.getInstance(getContext()).getProfile();
        editTextUsername.setText(profile.getUsername());
        editTextEmailProfile.setText(profile.getEmail());
        editTextIdadeProfile.setText(profile.getAge());
        editTextNomeAlergiaProfile.setText(profile.getAlergias());
        editTextGeneroProfile.setText(profile.getGenero());
        editTextNomeContactoProfile.setText(profile.getTelefone());
        editTextNomeMoradaProfile.setText(profile.getMorada());
        editTextNomeCompletoProfile.setText(profile.getFullname());

        return view;
    }
}
