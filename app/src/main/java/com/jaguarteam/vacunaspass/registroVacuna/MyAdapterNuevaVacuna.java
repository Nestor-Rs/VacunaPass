package com.jaguarteam.vacunaspass.registroVacuna;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jaguarteam.vacunaspass.R;
import com.jaguarteam.vacunaspass.localData.Vacunas;

import java.util.ArrayList;

public class MyAdapterNuevaVacuna extends RecyclerView.Adapter<MyAdapterNuevaVacuna.ViewHolder>{

    ArrayList<Vacunas> publicacionList;

    MyAdapterNuevaVacuna(ArrayList<Vacunas> publicacionList){
        super();
        this.publicacionList=publicacionList;
    }
    @NonNull
    @Override
    public MyAdapterNuevaVacuna.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyAdapterNuevaVacuna.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.tarjeta_noticias,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterNuevaVacuna.ViewHolder holder, int position) {
        holder.bindItems(publicacionList.get(position));
    }

    @Override
    public int getItemCount() {
        return publicacionList.size();
    }

    class  ViewHolder extends RecyclerView.ViewHolder {

        ViewHolder(View itemView){
            super(itemView);
        }

        public  void bindItems(Vacunas publicacion){
            //Tomar todos los item view
        }
    }

}