package com.jaguarteam.vacunaspass.registroUsuarios;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.jaguarteam.vacunaspass.R;
import com.jaguarteam.vacunaspass.localData.User;


public class ConfirmacionRegistroExitoso extends Fragment {

    User myUser;
    public ConfirmacionRegistroExitoso(User user) {
        myUser=user;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        TextView miNombre = getActivity().findViewById(R.id.nombreCRE);
        miNombre.setText(myUser.getNombre()+" "+myUser.getApellidoP()+" "+myUser.getApellidoM());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_confirmacion_registro_exitoso, container, false);
    }
}