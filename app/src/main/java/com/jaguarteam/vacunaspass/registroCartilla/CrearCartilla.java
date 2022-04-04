package com.jaguarteam.vacunaspass.registroCartilla;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.jaguarteam.vacunaspass.R;
import com.jaguarteam.vacunaspass.localData.User;
import com.jaguarteam.vacunaspass.registroUsuarios.ConfirmacionRegistroExitoso;
import com.jaguarteam.vacunaspass.registroUsuarios.RegistroCuenta;
import com.orhanobut.logger.Logger;

public class CrearCartilla extends AppCompatActivity {

    //Creacion de usuario con FireAuht
    private FirebaseAuth mAuth;
    //firestore
    private FirebaseFirestore db;


    //Manejo de fragmentos
    Fragment fragmentNow;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cartilla);

        mAuth = FirebaseAuth.getInstance();
        db= FirebaseFirestore.getInstance();

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
                comprobarCampos();
                if(comprobarCampos()!=false){
                    createCartilla();
                }
                break;
            case R.id.exitCRE:
                finish();
                break;
        }
    }

    private void createCartilla() {
        //ColocarText views
        TextView nom,apeP,apeM,fNaci,pes,edadG;
        Spinner gen,tipoS;
        nom=findViewById(R.id.nombreCartilla);
        apeP=findViewById(R.id.apellidoPCartilla);
        apeM=findViewById(R.id.apellidoMCartilla);
        gen= findViewById(R.id.generoCartilla);
        fNaci=findViewById(R.id.fechaNacimietoCartilla);
        pes=findViewById(R.id.pesoNacimientoCartilla);
        edadG=findViewById(R.id.edadGestalCartilla);
        tipoS=findViewById(R.id.tipoSangineoCartilla);
        //Crear objeto
        User myUser = new User(
                mAuth.getUid(),
                nom.getText().toString(),
                apeP.getText().toString(),
                apeM.getText().toString(),
                gen.getSelectedItem().toString(),
                fNaci.getText().toString(),
                Double.parseDouble(pes.getText().toString()),
                Double.parseDouble(edadG.getText().toString()),
                tipoS.getSelectedItem().toString(),
                "None"
        );
        //Crear documento
        db.collection("InformacionUsuario").document(mAuth.getUid())
                .collection("MiFamilia").document()
                .set(myUser)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Logger.d("Se creo el documento", "DocumentSnapshot successfully written!");
                        cambioFracment(new ConfirmacionRegistroExitoso(myUser));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("No se creo el documento", "Error writing document", e);
                        Toast.makeText(CrearCartilla.this, "Error al crear tus datos",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void cambioFracment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.registroCartillaNueva, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    boolean comprobarCampos(){
        TextView nom,apeP,apeM,fNaci,pes,edadG;
        nom=findViewById(R.id.nombreCartilla);
        apeP=findViewById(R.id.apellidoPCartilla);
        apeM=findViewById(R.id.apellidoMCartilla);
        fNaci=findViewById(R.id.fechaNacimietoCartilla);
        pes=findViewById(R.id.pesoNacimientoCartilla);
        edadG=findViewById(R.id.edadGestalCartilla);

        if(nom.getText().toString().isEmpty()){
            nom.setError("Ingresa tu correo");
            return false;
        }
        else if(apeP.getText().toString().isEmpty()){
            apeP.setError("Ingresa tu correo");
            return false;
        }
        else if(apeM.getText().toString().isEmpty()){
            apeM.setError("Ingresa tu correo");
            return false;
        }
        else if(fNaci.getText().toString().isEmpty()){
            fNaci.setError("Ingresa tu correo");
            return false;
        }
        else if(pes.getText().toString().isEmpty()){
            pes.setError("Ingresa tu correo");
            return false;
        }
        else if(edadG.getText().toString().isEmpty()){
            edadG.setError("Ingresa tu correo");
            return false;
        }
        else{
            return true;
        }
    }
}