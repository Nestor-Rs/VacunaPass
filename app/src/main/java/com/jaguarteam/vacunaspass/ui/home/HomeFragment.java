package com.jaguarteam.vacunaspass.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.jaguarteam.vacunaspass.R;
import com.jaguarteam.vacunaspass.databinding.FragmentHomeBinding;
import com.orhanobut.logger.Logger;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    //Creacion de usuario con FireAuht
    private FirebaseAuth mAuth;
    //firestore
    private FirebaseFirestore db;
    //textos
    TextView nombre,fechaNacimiento,peso,genero,edadG,tipoSangre;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        db= FirebaseFirestore.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        initTextviews();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void initTextviews(){
        nombre= getActivity().findViewById(R.id.nombreMenu);
        fechaNacimiento = getActivity().findViewById(R.id.fechaNacimietoMenu);
        peso= getActivity().findViewById(R.id.pesonacimientoMenu);
        genero= getActivity().findViewById(R.id.generoMenu);
        edadG= getActivity().findViewById(R.id.edadGestalMenu);
        tipoSangre= getActivity().findViewById(R.id.tipoSangineoMenu);

        db.collection("InformacionUsuario")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (document.getId().equals(mAuth.getUid())){
                                    nombre.setText(document.get("nombre").toString()+" "+document.get("apellidoP").toString()+" "+document.get("apellidoM").toString());
                                    fechaNacimiento.setText(document.get("fechaNacimiento").toString());
                                    peso.setText(document.get("peso").toString());
                                    genero.setText(document.get("genero").toString());
                                    edadG.setText(document.get("edadGestal").toString());
                                    tipoSangre.setText(document.get("tipoSanguineo").toString());
                                }
                            }
                        } else {
                            Logger.w("Error con el documento", "Error getting documents.", task.getException());
                        }
                    }
                });
    }
}