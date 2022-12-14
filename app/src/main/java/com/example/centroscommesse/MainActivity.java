package com.example.centroscommesse;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
                    EditText editTextPorta = findViewById(R.id.porta);
                    String ip = editTextIp.getText().toString();
                    int porta = Integer.parseInt(editTextPorta.getText().toString());
                    String masterKey = "123456789";

                    EditText inputEditTextField = new EditText(MainActivity.this);
                    AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Master Key")
                            .setMessage("Se sei un Master, inserisci la Master Key (altrimenti lascia vuoto)")
                            .setView(inputEditTextField)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    String input = inputEditTextField.getText().toString();

                                    if (input.equals("")) {
                                        // start regular client activity
                                        Socket socket = null;
                                        Client client = null;
                                        try {
                                            socket = new Socket("192.168.244.85", 8080);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        try {
                                            client = new Client(socket);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        startActivity(new Intent(MainActivity.this, Scommesse.class));
                                    }
                                    else if (input.equals(masterKey)) {
                                        // FIXME: this branch should not exist, client doesn't know the masterKey a priori
                                    }
                                }
                            })
                            .setNegativeButton("Cancel", null)
                            .create();
                    dialog.show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"Errore durante la connessione al server! Riprovare.",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
    }
}