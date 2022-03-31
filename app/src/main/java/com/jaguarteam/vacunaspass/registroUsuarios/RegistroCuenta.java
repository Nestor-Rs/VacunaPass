package com.jaguarteam.vacunaspass.registroUsuarios;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.jaguarteam.vacunaspass.R;
import com.jaguarteam.vacunaspass.localData.User;
import com.orhanobut.logger.Logger;

import java.util.Date;

public class RegistroCuenta extends AppCompatActivity {

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
        setContentView(R.layout.activity_registro_cuenta);

        mAuth = FirebaseAuth.getInstance();
        db= FirebaseFirestore.getInstance();

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        fragmentNow=new ConfirmacionHacerRegistro();
        fragmentTransaction.add(R.id.registroFrament, fragmentNow);
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
            case R.id.Registrar:
                TextView correo,contra;
                correo = findViewById(R.id.nuevoCorreoR);
                contra = findViewById(R.id.nuevoContrasenaR);
                if(comprobarCampos()!=false){
                    crearCuenta(correo.getText().toString(), contra.getText().toString());
                }
                break;
            case R.id.exitCRE:
                finish();
                break;
        }
    }

    public void cambioFracment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.registroFrament, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    void crearCuenta(String email,String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Logger.d("Se creo la cuenta", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            crearDocumentoDatos(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Logger.w("Error con el registro", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegistroCuenta.this, "Error al crear la cuenta",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private void crearDocumentoDatos(FirebaseUser user) {
        //ColocarText views
        TextView nom,apeP,apeM,fNaci,pes,edadG;
        Spinner gen,tipoS;
        nom=findViewById(R.id.nombreR);
        apeP=findViewById(R.id.apellidoPR);
        apeM=findViewById(R.id.apellidoMR);
        gen= findViewById(R.id.generoR);
        fNaci=findViewById(R.id.fechaNacimietoR);
        pes=findViewById(R.id.pesoNacimientoR);
        edadG=findViewById(R.id.edadGestalR);
        tipoS=findViewById(R.id.tipoSangineoR);
        //Crear objeto
        User myUser = new User(
                user.getUid(),
                nom.getText().toString(),
                apeP.getText().toString(),
                apeM.getText().toString(),
                gen.getSelectedItem().toString(),
                fNaci.getText().toString(),
                Double.parseDouble(pes.getText().toString()),
                Double.parseDouble(edadG.getText().toString()),
                tipoS.getSelectedItem().toString(),
                user.getEmail()
        );
        //Crear documento de fireStore
        db.collection("InformacionUsuario").document(user.getUid())
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
                        Toast.makeText(RegistroCuenta.this, "Error al crear tus datos",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    boolean comprobarCampos(){
        TextView correo,contra,nom,apeP,apeM,fNaci,pes,edadG;
        correo = findViewById(R.id.nuevoCorreoR);
        contra = findViewById(R.id.nuevoContrasenaR);
        nom=findViewById(R.id.nombreR);
        apeP=findViewById(R.id.apellidoPR);
        apeM=findViewById(R.id.apellidoMR);
        fNaci=findViewById(R.id.fechaNacimietoR);
        pes=findViewById(R.id.pesoNacimientoR);
        edadG=findViewById(R.id.edadGestalR);
        if(correo.getText().toString().isEmpty()){
            correo.setError("Ingresa tu correo");
            return false;
        }
        else if(contra.getText().toString().isEmpty()){
            contra.setError("Ingresa tu correo");
            return false;
        }
        else if(nom.getText().toString().isEmpty()){
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