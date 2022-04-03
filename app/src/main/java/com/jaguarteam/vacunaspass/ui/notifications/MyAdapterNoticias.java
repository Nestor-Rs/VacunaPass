package com.jaguarteam.vacunaspass.ui.notifications;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jaguarteam.vacunaspass.R;
import com.jaguarteam.vacunaspass.localData.Publication;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapterNoticias extends RecyclerView.Adapter<MyAdapterNoticias.ViewHolder> {


    ArrayList<Publication> publicacionList;

    MyAdapterNoticias(ArrayList<Publication> publicacionList){
        super();
        this.publicacionList=publicacionList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.tarjeta_noticias,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindItems(publicacionList.get(position));
    }

    @Override
    public int getItemCount() {
        return publicacionList.size();
    }
    ImageView imgNoticia;
    TextView tituloNoticia,fechaNoticia,textoNoticia;

    class  ViewHolder extends RecyclerView.ViewHolder {

        ViewHolder(View itemView){
            super(itemView);
        }
        void setOnclikLiseners(){

        }

        public  void bindItems(Publication publicacion){
            //Tomar todos los item view
            imgNoticia = itemView.findViewById(R.id.imgNoticia);
            tituloNoticia = itemView.findViewById(R.id.tituloNoticia);
            fechaNoticia = itemView.findViewById(R.id.fechaNoticia);
            textoNoticia = itemView.findViewById(R.id.textoNoticia);
            //Cambiar los elementos de las imganes
            Picasso.get().load(publicacion.getImg()).error(R.mipmap.ic_launcher).into(imgNoticia);
            tituloNoticia.setText(publicacion.getTitulo());
            fechaNoticia.setText(publicacion.getFecha());
            textoNoticia.setText(publicacion.getPublicacion());
        }
    }
}