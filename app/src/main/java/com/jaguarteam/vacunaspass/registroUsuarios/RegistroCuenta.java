package com.jaguarteam.vacunaspass.registroUsuarios;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jaguarteam.vacunaspass.R;

public class RegistroCuenta extends AppCompatActivity {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_cuenta);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.registroFrament, new ConfirmacionHacerRegistro());
        fragmentTransaction.commit();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void onClick(View view){
        switch (view.getId()) {
            case R.id.exitCHR:
                finish();
                break;
            case R.id.comenzarCHR:
                cambioFracment(new RegistroNuevoUsuario());
                break;
            case R.id.exitRegistro:
                onBackPressed();
                break;
        }
    }

    public void cambioFracment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.registroFrament, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}