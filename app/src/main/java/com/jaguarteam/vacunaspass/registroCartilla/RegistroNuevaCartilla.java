package com.jaguarteam.vacunaspass.registroCartilla;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jaguarteam.vacunaspass.R;

public class RegistroNuevaCartilla extends Fragment {

    public RegistroNuevaCartilla() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_registro_nueva_cartilla, container, false);
    }
}