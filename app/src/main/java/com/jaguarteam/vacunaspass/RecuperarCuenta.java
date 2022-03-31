package com.jaguarteam.vacunaspass;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class RecuperarCuenta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_cuenta);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.exitRecuperar:
                    finish();
                break;
            case R.id.recuperar:

                break;
        }
    }
}