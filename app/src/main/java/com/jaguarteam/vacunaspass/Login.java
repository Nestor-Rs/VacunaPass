package com.jaguarteam.vacunaspass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.jaguarteam.vacunaspass.registroUsuarios.RegistroCuenta;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

public class Login extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private TextView correo,contrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Logger.addLogAdapter(new AndroidLogAdapter());

        mAuth = FirebaseAuth.getInstance();

        correo = findViewById(R.id.correo);
        contrasena = findViewById(R.id.contrasena);
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // User is signed in
            updateUI(user);
        }

    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.inicioSesion:
                String mail,contra;
                mail=correo.getText().toString();
                contra = contrasena.getText().toString();
                if(!mail.isEmpty() && !contra.isEmpty()){
                    mAuth.signInWithEmailAndPassword(mail,contra)
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Logger.d("Se autentico", "signInWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        updateUI(user);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Logger.w("Error con autenticacion", "signInWithEmail:failure", task.getException());
                                        Toast.makeText(Login.this, "Tu correo o contraseña es incorrecta",Toast.LENGTH_SHORT).show();
                                        //updateUI(null);
                                    }
                                }
                            });
                }
                break;
            case R.id.recuperaCuenta:
                Intent recuperarCuenta = new Intent(this,RecuperarCuenta.class);
                startActivity(recuperarCuenta);
                break;
            case R.id.registrarse:
                Intent crearCuenta = new Intent(this, RegistroCuenta.class);
                startActivity(crearCuenta);
                break;
        }
    }

    private void updateUI(FirebaseUser user) {
        String UID = user.getUid();
        Intent mainMenu = new Intent(this,MainMenu.class);
        mainMenu.putExtra("UID",UID);
        startActivity(mainMenu);
    }
}