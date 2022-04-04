package com.jaguarteam.vacunaspass;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jaguarteam.vacunaspass.localData.Vacunas;
import com.jaguarteam.vacunaspass.localData.VacunasCartilla;

import java.io.Serializable;

public class VistaVacuna extends AppCompatActivity {

    private VacunasCartilla vacuna;
    TextView datosVacuna[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_vacuna);

        datosVacuna = new TextView[8];
        datosVacuna[0] = findViewById(R.id.nombreVV);
        datosVacuna[1] = findViewById(R.id.esquemaVV);
        datosVacuna[2] = findViewById(R.id.fechaVV);
        datosVacuna[3] = findViewById(R.id.marcaVV);
        datosVacuna[4] = findViewById(R.id.loteVV);
        datosVacuna[5] = findViewById(R.id.doctorVV);
        datosVacuna[6] = findViewById(R.id.centroVV);
        datosVacuna[7] = findViewById(R.id.domicilioVV);

        Bundle myObj = getIntent().getExtras();

        if(myObj!=null){
            vacuna = (VacunasCartilla) myObj.getSerializable("Vacuna");
            datosVacuna[0].setText(vacuna.getVacuna().getNombre());
            datosVacuna[1].setText(vacuna.getVacuna().getEsquema());
            datosVacuna[2].setText(vacuna.getFechaAplicacion());
            datosVacuna[3].setText(vacuna.getVacuna().getMarca());
            datosVacuna[4].setText(vacuna.getNumeroLote());
            datosVacuna[5].setText(vacuna.getDoctor());
            datosVacuna[6].setText(vacuna.getCentroAplicacion());
            datosVacuna[7].setText(vacuna.getDomicilio());
        }
    }

    public void onClick(View view){
        //TODO: Agregar la demas funcionalidad para el boton
        switch (view.getId()){
            case R.id.backVV:
                finish();
                break;
        }
    }
}