package com.jaguarteam.vacunaspass.registroUsuarios;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jaguarteam.vacunaspass.R;


public class ConfirmacionHacerRegistro extends Fragment {



    public ConfirmacionHacerRegistro() {
        // Required empty public constructor
    }

    public static ConfirmacionHacerRegistro newInstance(String param1, String param2) {
        ConfirmacionHacerRegistro fragment = new ConfirmacionHacerRegistro();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_confirmacion_hacer_registro, container, false);
    }
}