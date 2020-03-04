package com.example.infoleaguenuevo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ContentResolver;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.infoleaguenuevo.model.AdaptadorCreaEquipos;
import com.example.infoleaguenuevo.model.Adaptadorequipos;
import com.example.infoleaguenuevo.model.CreaEquipo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.firestore.FirebaseFirestore;


import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class CreaTuEquipoActivity extends AppCompatActivity implements Observer {

    EditText nomT, colT, numT, punT;

    ArrayList<CreaEquipo> listaEquiposCreados;
    RecyclerView DatosEquipos;

    private NotificationManager nm;
    private final String CHANNEL_ID = "Channel2";
    private NotificationCompat.Builder n;
    private final int NOTIFICATION_ID = 666;
    private NotificationChannel ch;
    private final String CHANNEL_NAME = "Notificaciones";
    private Uri uri;

    String msg = "Equipo añadido";

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crea_tu_equipo);

        nomT = findViewById(R.id.TxtNameTeam);
        colT = findViewById(R.id.TxtColorTeam);
        numT = findViewById(R.id.TxtTeamPlayers);
        punT = findViewById(R.id.TxtTeamPoints);
        
        uri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + getPackageName() + "/" + R.raw.bike_horn);
        
        inicializarFirebase();

        listaEquiposCreados = new ArrayList<>();
        DatosEquipos = findViewById(R.id.DatosEquipos);
        DatosEquipos.setLayoutManager(new LinearLayoutManager(this));
        llenarequiposcreados();
        AdaptadorCreaEquipos adapter = new AdaptadorCreaEquipos(listaEquiposCreados);
        DatosEquipos.setAdapter(adapter);

    }

    private void llenarequiposcreados() {
    
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {


            AudioAttributes att = new AudioAttributes.Builder()
                    .setContentType((AudioAttributes.CONTENT_TYPE_SONIFICATION))
                    .setUsage((AudioAttributes.USAGE_NOTIFICATION))
                    .build();


            ch = new NotificationChannel(CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_HIGH );

            ch.setDescription(CHANNEL_NAME);


            ch.setSound(uri,att);


            ch.enableVibration(true);
            ch.setVibrationPattern(new long [] {10000l} );


            ch.enableLights(true);


            nm.createNotificationChannel(ch);


            ch.setLockscreenVisibility(NotificationCompat.VISIBILITY_PUBLIC);
        }
    }

    private void inicializarFirebase() {
        
        databaseReference = FirebaseDatabase.getInstance().getReference();
        //databaseReference = databaseReference.getRef();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_equipos,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.add: {
                nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

                createNotificationChannel();

                n = new NotificationCompat.Builder(CreaTuEquipoActivity.this, CHANNEL_ID);
                n.setSmallIcon(R.drawable.ic_bell);
                n.setContentTitle("InfoLeague");
                n.setContentText("¡Nuevo equipo añadido!");
                n.setSound(uri);
                n.setPriority(NotificationCompat.PRIORITY_HIGH);

                nm.notify(NOTIFICATION_ID, n.build());

                String id = databaseReference.push().getKey();
                String nombre  = nomT.getText().toString();
                String color = colT.getText().toString();
                String num = numT.getText().toString();
                String punt = punT.getText().toString();

                if (nombre.isEmpty()|color.isEmpty()|num.isEmpty()|punt.isEmpty()) {
                    validacion();
                } else {
                    CreaEquipo creaEquipo = new CreaEquipo(nombre, color, num, punt);

                    databaseReference.child("Equipos").child(id).setValue(creaEquipo);
                    Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                    limpiarCajas();
                }
                Toast.makeText(CreaTuEquipoActivity.this, "Agregado", Toast.LENGTH_LONG).show();
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

    /**
     * This method is called whenever the observed object is changed. An
     * application calls an <tt>Observable</tt> object's
     * <code>notifyObservers</code> method to have all the object's
     * observers notified of the change.
     *
     * @param o   the observable object.
     * @param arg an argument passed to the <code>notifyObservers</code>
     */
    @Override
    public void update(Observable o, Object arg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}
