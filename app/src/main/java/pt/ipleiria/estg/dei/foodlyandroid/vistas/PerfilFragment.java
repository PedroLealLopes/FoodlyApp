package pt.ipleiria.estg.dei.foodlyandroid.vistas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import pt.ipleiria.estg.dei.foodlyandroid.R;

public class PerfilFragment extends Fragment {

    public PerfilFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        return view;
    }
}