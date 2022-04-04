package com.jaguarteam.vacunaspass.registroCartilla;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

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

    @Override
    public void onStart() {
        super.onStart();
        crearSpinner();
    }

    public void crearSpinner(){
        Spinner genero,sangre;
        genero = (Spinner) getActivity().findViewById(R.id.generoCartilla);
        sangre = (Spinner) getActivity().findViewById(R.id.tipoSangineoCartilla);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> sexo = ArrayAdapter.createFromResource(getActivity(), R.array.Genero,
                android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> sanguineo = ArrayAdapter.createFromResource(getActivity(), R.array.Sangre,
                android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        sanguineo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sexo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        sangre.setAdapter(sanguineo);
        genero.setAdapter(sexo);
    }
}