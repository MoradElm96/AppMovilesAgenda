package com.morad.myapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import morad.myapp.R;

public class AdaptadorDatos extends RecyclerView.Adapter<AdaptadorDatos.ViewHolderDatos> {

    Contacto contacto;
    //referencia a lista
    ArrayList<Contacto> listaPersonas;


    public AdaptadorDatos(ArrayList<Contacto> listaPersonas) {
        this.listaPersonas = listaPersonas;
    }


    @Override
    public ViewHolderDatos onCreateViewHolder(ViewGroup parent, int viewType) {
    //sirve para inflar la vista y llenar  los items
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_personas, null, false);


        return new ViewHolderDatos(view);


    }

    @Override
    public void onBindViewHolder(ViewHolderDatos holder, int position) {
        //comuica adaptor y la clase view holder datos
        //  le enviamos la informacion que quiero que muestre
        holder.nombre.setText(listaPersonas.get(position).getNombre());
        holder.apellido.setText(listaPersonas.get(position).getApellido());
        holder.telefono.setText(String.valueOf(listaPersonas.get(position).getTelefono()));
        //aqui pongo value offf porque la variable es integer en la base de datos,


        holder.correo.setText(listaPersonas.get(position).getCorreo());


    }


    @Override
    public int getItemCount() {
        return listaPersonas.size();
        //retorna el tamaño de la lista
    }


    //creamos la clase
    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        //hacemos referencia a item_personas

        TextView nombre, apellido, telefono, correo;


        public ViewHolderDatos(View itemView) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.tvNombre);
            apellido = (TextView) itemView.findViewById(R.id.tvApellido);

            telefono = (TextView) itemView.findViewById(R.id.tvTelefono);
            correo = (TextView) itemView.findViewById(R.id.tvCorreo);


        }


    }


}
