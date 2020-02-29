package com.example.infoleaguenuevo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //defining view objects
    private EditText TextEmail;
    private EditText TextPassword;
    private Button btnLogin;
    private Button btnRegister;
    private ProgressDialog progressDialog;


    //Declaramos un objeto firebaseAuth
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inicializamos el objeto firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance();

        //Referenciamos los views
        TextEmail = (EditText) findViewById(R.id.TxtEmail);
        TextPassword = (EditText) findViewById(R.id.TxtPassword);

        btnLogin = (Button) findViewById(R.id.botonLogin);
        btnRegister = (Button) findViewById(R.id.goSignIn);

        progressDialog = new ProgressDialog(this);

        //attaching listener to button
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MainActivity.this, RegisterActivity.class);
                finish();
                startActivity(intent);
            }
        });


    }

    private void loguearUsuario() {

        //Obtenemos el email y la contraseña desde las cajas de texto
        final String email = TextEmail.getText().toString().trim();
        String password  = TextPassword.getText().toString().trim();

        //Verificamos que las cajas de texto no esten vacías
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Se debe ingresar un email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Falta ingresar la contraseña",Toast.LENGTH_LONG).show();
            return;
        }


        progressDialog.setMessage("Iniciando sesión en linea...");
        progressDialog.show();

        //Loguear usuario
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if(task.isSuccessful()){
                            int pos = email.indexOf("@"); // me devuelve donde está el @ para saber donde esta y poder quitar lo demás
                            String user = email.substring(0,pos);
                            Toast.makeText(MainActivity.this,"Bienvenido: "+ TextEmail.getText(),Toast.LENGTH_LONG).show();
                            String email = TextEmail.getText().toString().trim();

                            Intent intencion = new Intent(getApplication(), BienvenidoActivity.class);
                            intencion.putExtra(BienvenidoActivity.user, user);
                            startActivity(intencion);

                            Intent intent = new Intent(getApplication(), ProfileActivity.class);
                            intent.putExtra(ProfileActivity.user, user);
                            intent.putExtra(ProfileActivity.email, email);
                            startActivity(intent);


                        }else{
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(MainActivity.this,"Comprueba que tu email es correcto. ",Toast.LENGTH_LONG).show();
                            }
                            Toast.makeText(MainActivity.this,"Error al iniciar sesión ",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });

    }

    @Override
    public void onClick(View view) {
        loguearUsuario();
    }
}
