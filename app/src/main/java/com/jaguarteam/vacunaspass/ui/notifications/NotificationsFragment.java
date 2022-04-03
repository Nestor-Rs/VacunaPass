package com.jaguarteam.vacunaspass.ui.notifications;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.jaguarteam.vacunaspass.R;
import com.jaguarteam.vacunaspass.databinding.FragmentNotificationsBinding;
import com.jaguarteam.vacunaspass.localData.Publication;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ArrayList<Publication> puclicaciones;
    RecyclerView recyclerView;
    MyAdapterNoticias adapter;

    @SuppressLint("WrongConstant")
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_notifications,container,false);

        Logger.addLogAdapter(new AndroidLogAdapter());
        puclicaciones = new ArrayList<Publication>();
        //recyclerView

        adapter = new MyAdapterNoticias(puclicaciones);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL,false));

        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        puclicaciones.clear();
        getPublicacion();
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void getPublicacion(){
        db.collection("Noticias").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        //Logger.d(document.getId());
                        puclicaciones.add(
                                new Publication(
                                        document.getData().get("titulo").toString(),
                                        document.getData().get("fechaPublicacion").toString(),
                                        document.getData().get("imgPublicacion").toString(),
                                        document.getData().get("publicacion").toString()
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
}