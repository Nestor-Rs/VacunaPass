package com.jaguarteam.vacunaspass.ui.dashboard;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.jaguarteam.vacunaspass.R;
import com.jaguarteam.vacunaspass.databinding.FragmentDashboardBinding;
import com.jaguarteam.vacunaspass.localData.Publication;
import com.jaguarteam.vacunaspass.localData.User;
import com.jaguarteam.vacunaspass.localData.Vacunas;
import com.jaguarteam.vacunaspass.localData.VacunasCartilla;
import com.jaguarteam.vacunaspass.ui.notifications.MyAdapterNoticias;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ArrayList<VacunasCartilla> vacunas;
    private FirebaseAuth mAuth;

    RecyclerView recyclerView;
    MyAdapterVacunas  adapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications,container,false);

        Logger.addLogAdapter(new AndroidLogAdapter());
        vacunas = new ArrayList<VacunasCartilla>();

        mAuth = FirebaseAuth.getInstance();
        db= FirebaseFirestore.getInstance();

        //recyclerView

        adapter = new MyAdapterVacunas(vacunas);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewVacunas);

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL,false));

        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vacunas.clear();
        //getVacunas();
    }

    private void getVacunas() {
        db.collection("InformacionUsuario").document(mAuth.getUid())
                .collection("MisVacunas").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        //Logger.d(document.getId());
                        vacunas.add(
                                new VacunasCartilla(
                                        getVacuna(),
                                        document.getData().get("fechaAplicacion").toString(),
                                        document.getData().get("numeroLote").toString(),
                                        document.getData().get("doctor").toString(),
                                        document.getData().get("centroAplicacion").toString(),
                                        document.getData().get("domicilio").toString()
                                )
                        );
                    }
                    adapter.notifyDataSetChanged();
                }
                else {
                    Logger.w("FireError", "Error getting documents.", task.getException());
                }
            }
        });
    }

    public Vacunas getVacuna(){
        final Vacunas[] myVacuna = new Vacunas[1];
        db.collection("NombresVacunas").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            Vacunas vacunas = null;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                vacunas = new Vacunas(
                                        document.getData().get("nombreVacuna").toString(),
                                        document.getData().get("esquema").toString(),
                                        document.getData().get("marca").toString()
                                );
                            }
                            myVacuna[0] =vacunas;
                        }
                        else {
                            Logger.w("FireError", "Error getting documents.", task.getException());
                        }
                    }
                });
        return myVacuna[0];
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}