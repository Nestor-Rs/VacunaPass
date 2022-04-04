package com.jaguarteam.vacunaspass.ui.dashboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jaguarteam.vacunaspass.R;
import com.jaguarteam.vacunaspass.VistaVacuna;
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
        holder.setOnclikLiseners();
    }

    @Override
    public int getItemCount() {
        return publicacionList.size();
    }

    class  ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        VacunasCartilla miVacuna;
        TextView textNombreVacuna;
        ImageButton verDatos;

        ViewHolder(View itemView){
            super(itemView);
            verDatos=itemView.findViewById(R.id.buttonViewVacuna);
        }

        public  void bindItems(VacunasCartilla publicacion){
            //Tomar todos los item view
            miVacuna=publicacion;
            textNombreVacuna = itemView.findViewById(R.id.nombreVacunaView);
            textNombreVacuna.setText(publicacion.getVacuna().getNombre());
        }

        public void setOnclikLiseners() {
            verDatos.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            final Context context;
            context = itemView.getContext();
            Intent vistaVacuna = new Intent(context.getApplicationContext(), VistaVacuna.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("Vacuna",miVacuna);
            vistaVacuna.putExtras(bundle);
            context.startActivity(vistaVacuna);
        }
    }

}
