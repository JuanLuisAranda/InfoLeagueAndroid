package com.example.infoleaguenuevo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adaptadorequipos extends RecyclerView.Adapter<Adaptadorequipos.ViewHolderEquipos> {

    ArrayList<Equipos> listaequipos;


    public Adaptadorequipos(ArrayList<Equipos> listamoviles) {
        this.listaequipos = listamoviles;
    }
    public class ViewHolderEquipos extends RecyclerView.ViewHolder {
        TextView nombre;
        ImageView escudo;
        public ViewHolderEquipos(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombreequipo);
            escudo = itemView.findViewById(R.id.logoescudo);
        }
    }
    @NonNull
    @Override
    public ViewHolderEquipos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_equipos,null, false);
        return new ViewHolderEquipos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderEquipos holder, int position) {
        holder.nombre.setText(listaequipos.get(position).getNombre());
        holder.escudo.setImageResource(listaequipos.get(position).getEscudo());
    }
    @Override
    public int getItemCount() {
        return listaequipos.size();
    }
}
