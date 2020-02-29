package com.example.infoleaguenuevo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

public class BienvenidoActivity extends AppCompatActivity {

    ArrayList<Equipos> listaequipos;
    RecyclerView recyclerequipos;

    public static final String user="names";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenido);

        this.setTitle(R.string.bienvenido);


        listaequipos = new ArrayList<>();
        recyclerequipos = findViewById(R.id.recyclerEquipo);
        recyclerequipos.setLayoutManager(new LinearLayoutManager(this));
        llenarequipos();
        Adaptadorequipos adapter = new Adaptadorequipos(listaequipos);
        recyclerequipos.setAdapter(adapter);

    }

    private void llenarequipos(){
        listaequipos.add(new Equipos("Real Madrid", R.drawable.ico_rmcf));
        listaequipos.add(new Equipos("FC Barcelona", R.drawable.ico_barcelona));
        listaequipos.add(new Equipos("Atlético de Madrid", R.drawable.atm));
        listaequipos.add(new Equipos("Real Sociedad", R.drawable.rso));
        listaequipos.add(new Equipos("Valencia CF", R.drawable.vcf));
        listaequipos.add(new Equipos("Alavés", R.drawable.ala));
        listaequipos.add(new Equipos("Athletic Club", R.drawable.ath));
        listaequipos.add(new Equipos("Real Betis", R.drawable.bet));
        listaequipos.add(new Equipos("Celta de Vigo", R.drawable.cel));
        listaequipos.add(new Equipos("Eibar", R.drawable.eib));
        listaequipos.add(new Equipos("Espanyol", R.drawable.esp));
        listaequipos.add(new Equipos("Granada CF", R.drawable.gra));
        listaequipos.add(new Equipos("Getafe", R.drawable.gtf));
        listaequipos.add(new Equipos("Leganés", R.drawable.leg));
        listaequipos.add(new Equipos("Levante", R.drawable.lev));
        listaequipos.add(new Equipos("Mallorca", R.drawable.mal));
        listaequipos.add(new Equipos("Osasuna", R.drawable.osa));
        listaequipos.add(new Equipos("Sevilla FC", R.drawable.sfc));
        listaequipos.add(new Equipos("Villareal", R.drawable.vil));
        listaequipos.add(new Equipos("Valladolid", R.drawable.val));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater() ;
        inflater.inflate(R.menu.menu_principal, menu) ;
        //
        return true ;
    }

    /**
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.desconectar :

            Intent irAPrincipio = new Intent(BienvenidoActivity.this, MainActivity.class);
            startActivity(irAPrincipio);

            return true ;

            case R.id.creatuliga :

                Intent irALiga = new Intent(BienvenidoActivity.this, CreaTuEquipoActivity.class);
                startActivity(irALiga);

                return true ;

            case R.id.profile :

            Intent irAProfile = new Intent(BienvenidoActivity.this, ProfileActivity.class);
            startActivity(irAProfile);

            return true ;
        }

        return super.onOptionsItemSelected(item);
    }

}
