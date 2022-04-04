package com.jaguarteam.vacunaspass.registroCartilla;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.jaguarteam.vacunaspass.R;
import com.jaguarteam.vacunaspass.localData.User;
import com.jaguarteam.vacunaspass.registroUsuarios.ConfirmacionRegistroExitoso;

public class CrearCartilla extends AppCompatActivity {

    //Manejo de fragmentos
    Fragment fragmentNow;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cartilla);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        fragmentNow=new RegistroNuevaCartilla();
        fragmentTransaction.add(R.id.registroCartillaNueva, fragmentNow);
        fragmentTransaction.commit();
    }
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.exitRNC:
                finish();
                break;
            case R.id.comenzarRNC:
                cambioFracment(new FormularioNuevaCartilla());
                break;
            case R.id.exitCartilla:
                onBackPressed();
                break;
            case R.id.RegistrarCartilla:
                cambioFracment(new ConfirmacionRegistroExitoso(new User("Nestor","","","","","",0.1,0.2,"","")));
                break;
            case R.id.exitCRE:
                finish();
                break;
        }
    }

    public void cambioFracment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.registroCartillaNueva, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}