package com.jaguarteam.vacunaspass.ui.dashboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jaguarteam.vacunaspass.R;
import com.jaguarteam.vacunaspass.localData.Publication;
import com.jaguarteam.vacunaspass.localData.Vacunas;
import com.jaguarteam.vacunaspass.localData.VacunasCartilla;

import java.util.ArrayList;

public class MyAdapterVacunas  extends RecyclerView.Adapter<MyAdapterVacunas.ViewHolder>{

    ArrayList<VacunasCartilla> publicacionList;

    MyAdapterVacunas(ArrayList<VacunasCartilla> publicacionList){
        super();
        this.publicacionList=publicacionList;
    }
    @NonNull
    @Override
    public MyAdapterVacunas.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.tarjeta_vacuna,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterVacunas.ViewHolder holder, int position) {
        holder.bindItems(publicacionList.get(position));
    }

    @Override
    public int getItemCount() {
        return publicacionList.size();
    }

    class  ViewHolder extends RecyclerView.ViewHolder {

        TextView textNombreVacuna;

        ViewHolder(View itemView){
            super(itemView);
        }

        public  void bindItems(VacunasCartilla publicacion){
            //Tomar todos los item view
            textNombreVacuna = itemView.findViewById(R.id.nombreVacunaView);
            textNombreVacuna.setText(publicacion.getVacuna().getNombre());
        }
    }

}
