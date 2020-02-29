package com.example.infoleaguenuevo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    public static final String user="names";
    public static final String email="emails";
    private TextView txtUser;
    private TextView txtEmail;
    private Button btnAceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        txtUser = (TextView) findViewById(R.id.textUserProfile);
        String user = getIntent().getStringExtra("names");
        txtUser.setText(user);

        txtEmail = (TextView) findViewById(R.id.emailUserProfile) ;
        String email = getIntent().getStringExtra("emails");
        txtEmail.setText(email);

        btnAceptar = (Button) findViewById(R.id.accept) ;
        btnAceptar.setOnClickListener(v -> {
            Intent intent = new Intent (ProfileActivity.this, BienvenidoActivity.class);
            finish();
            startActivity(intent);
        });
    }
}
