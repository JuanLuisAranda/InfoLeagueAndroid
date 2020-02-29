package com.example.infoleaguenuevo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.infoleaguenuevo.model.CreaEquipo;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.Tag;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.proto.TargetGlobal;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CreaTuEquipoActivity extends AppCompatActivity {

    EditText nomT, colT, numT, punT;
    ListView ListV_equipos;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crea_tu_equipo);

        nomT = findViewById(R.id.TxtNameTeam);
        colT = findViewById(R.id.TxtColorTeam);
        numT = findViewById(R.id.TxtTeamPlayers);
        punT = findViewById(R.id.TxtTeamPoints);

        ListV_equipos = findViewById(R.id.lv_datosEquipos);

        inicializarFirebase();
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseFirestore = FirebaseFirestore.getInstance();
        //databaseReference = databaseReference.getRef();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_equipos,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String nombre  = nomT.getText().toString();
        String color = colT.getText().toString();
        String num = numT.getText().toString();
        String punt = punT.getText().toString();

        switch (item.getItemId()) {
            case R.id.add: {
                if (nombre.isEmpty()|color.isEmpty()|num.isEmpty()|punt.isEmpty()) {
                    validacion();
                } else {
                    Map<String,String>equipo=new HashMap<>( );
                    equipo.put( "Nombre",nombre );
                    equipo.put( "Color", color );
                    equipo.put( "Número de jugadores", num);
                    equipo.put( "Puntos", punt);
                    //CreaEquipo equipo = new CreaEquipo();
                    //equipo.setUid(UUID.randomUUID().toString());
                    //equipo.setNombre(nombre);
                    //equipo.setColorPrincipal(color);
                    //equipo.setNumJugadores(num);
                    firebaseFirestore.collection("Equipo").add(equipo).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {


                        }
                    });

                    Toast.makeText(CreaTuEquipoActivity.this, "Agregado", Toast.LENGTH_LONG).show();
                    limpiarCajas();
                }
                break;
            }

            case R.id.delete: {
                Toast.makeText(this, "Borrado", Toast.LENGTH_LONG).show();
                break;
            }

            case R.id.save: {
                Toast.makeText(this, "Actualizado", Toast.LENGTH_LONG).show();
                break;
            }
            default:break;
        }
        return true;
    }

    private void limpiarCajas() {
     nomT.setText("");
     colT.setText("");
     numT.setText("");
     punT.setText("");
    }

    private void validacion() {
        String nombre = nomT.getText().toString();
        String color = colT.getText().toString();
        String num = numT.getText().toString();
        String punt = punT.getText().toString();

        if (nombre.isEmpty()){
            nomT.setError("Agregue un nombre");
        } else if (color.isEmpty()) {
            colT.setError("Agregue un color principal");
        } else if (num.isEmpty()) {
            numT.setError("Agregue un número de jugadores");
        } else if (punt.isEmpty()) {
            punT.setError("Agregue los puntos del equipo");
        }

    }

}
