package com.jaguarteam.vacunaspass;


import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.jaguarteam.vacunaspass.databinding.ActivityMainMenuBinding;
import com.jaguarteam.vacunaspass.localData.User;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

public class MainMenu extends AppCompatActivity {

    //firebase
    private FirebaseFirestore db;
    String UID;
    User myUser;

    //fragment
    private ActivityMainMenuBinding binding;

    //Drawer
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.addLogAdapter(new AndroidLogAdapter());
        //firebase
        UID = getIntent().getExtras().getString("UID");

        db= FirebaseFirestore.getInstance();
        //mas cosas
        binding = ActivityMainMenuBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        setSupportActionBar(binding.appBarMainMenu.toolbar);
        //Drawer
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navViewDrawer;
        toggle=new ActionBarDrawerToggle(this,drawer,binding.appBarMainMenu.toolbar,R.string.drawe_open,R.string.drawe_close);
        drawer.addDrawerListener(toggle);
        //set parts
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        toggle.syncState();

    }

    @Override
    protected void onStart() {
        super.onStart();
        getUser();
    }

    void getUser(){
        db.collection("InformacionUsuario")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (document.getId().equals(UID)){
                                    myUser=new User(
                                            UID,
                                            document.get("nombre").toString(),
                                            document.get("apellidoP").toString(),
                                            document.get("apellidoM").toString(),
                                            document.get("genero").toString(),
                                            document.get("fechaNacimiento").toString(),
                                            Double.parseDouble(document.get("peso").toString()),
                                            Double.parseDouble(document.get("edadGestal").toString()),
                                            document.get("tipoSanguineo").toString(),
                                            document.get("correo").toString()
                                    );
                                }
                            }
                            onCompletGetUser();
                        } else {
                            Logger.w("Error con el documento", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onCompletGetUser(){
        Logger.w(myUser.getNombre());
        View header =  binding.navViewDrawer.getHeaderView(0);
        TextView nombreDrawe = header.findViewById(R.id.nombreDrawer);
        nombreDrawe.setText(myUser.getNombre()+" "+myUser.getApellidoP()+" "+myUser.getApellidoM());
    }
}