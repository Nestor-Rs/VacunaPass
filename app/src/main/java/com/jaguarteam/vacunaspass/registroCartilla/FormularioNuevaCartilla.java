package com.jaguarteam.vacunaspass.registroCartilla;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jaguarteam.vacunaspass.R;

public class FormularioNuevaCartilla extends Fragment {

    public FormularioNuevaCartilla() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_formulario_nueva_cartilla, container, false);
    }
}