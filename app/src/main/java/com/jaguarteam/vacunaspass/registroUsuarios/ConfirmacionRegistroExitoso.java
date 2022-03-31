package com.jaguarteam.vacunaspass.registroUsuarios;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jaguarteam.vacunaspass.R;


public class ConfirmacionRegistroExitoso extends Fragment {



    public ConfirmacionRegistroExitoso() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ConfirmacionRegistroExitoso newInstance(String param1, String param2) {
        ConfirmacionRegistroExitoso fragment = new ConfirmacionRegistroExitoso();
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
        return inflater.inflate(R.layout.fragment_confirmacion_registro_exitoso, container, false);
    }
}