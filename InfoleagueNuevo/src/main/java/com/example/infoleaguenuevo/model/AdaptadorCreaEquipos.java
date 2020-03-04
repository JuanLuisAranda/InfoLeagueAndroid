package com.example.infoleaguenuevo.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infoleaguenuevo.R;

import java.util.ArrayList;

public class AdaptadorCreaEquipos extends RecyclerView.Adapter<AdaptadorCreaEquipos.ViewHolderCreaEquipos>  {

    ArrayList <CreaEquipo> listaEquiposCreados;

    public AdaptadorCreaEquipos(ArrayList<CreaEquipo> listamoviles) {
        this.listaEquiposCreados = listamoviles;
    }
    public class ViewHolderCreaEquipos extends RecyclerView.ViewHolder {
        TextView nombre, color, jugadores, puntos;
        public ViewHolderCreaEquipos(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.TxtName);
            color = itemView.findViewById(R.id.TxtColorTeam);
            jugadores = itemView.findViewById(R.id.TxtTeamPlayers);
            puntos = itemView.findViewById(R.id.TxtTeamPoints);
        }
    }
    @NonNull
    @Override
    public ViewHolderCreaEquipos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_crea_tu_equipo,null, false);
        return new ViewHolderCreaEquipos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCreaEquipos holder, int position) {
    holder.nombre.setText(listaEquiposCreados.get(position).getNombre());
    holder.color.setText(listaEquiposCreados.get(position).getColorPrincipal());
    holder.jugadores.setText(listaEquiposCreados.get(position).getNumJugadores());
    holder.puntos.setText(listaEquiposCreados.get(position).getPuntos());
    }

    @Override
    public int getItemCount() { return listaEquiposCreados.size(); }


}
