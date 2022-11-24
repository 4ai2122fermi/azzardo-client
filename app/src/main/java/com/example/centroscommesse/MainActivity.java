package com.example.centroscommesse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.net.*;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.connetti);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    EditText editTextIp = findViewById(R.id.IP);
                    String ip = editTextIp.getText().toString();

                    EditText editTextPorta = findViewById(R.id.porta);
                    int porta = Integer.parseInt(editTextPorta.getText().toString());

                    //Socket socket = new Socket("192.168.244.85", 8080);
                    //Client client = new Client(socket);
                    startActivity(new Intent(MainActivity.this, Scommesse.class));
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"Errore durante la connessione al server! Riprovare.",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
    }
}