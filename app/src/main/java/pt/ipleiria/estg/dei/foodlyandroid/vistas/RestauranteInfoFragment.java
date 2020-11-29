package pt.ipleiria.estg.dei.foodlyandroid.vistas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pt.ipleiria.estg.dei.foodlyandroid.R;

public class RestauranteInfoFragment extends Fragment {

    public RestauranteInfoFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_restaurante_info, container, false);
    }
}