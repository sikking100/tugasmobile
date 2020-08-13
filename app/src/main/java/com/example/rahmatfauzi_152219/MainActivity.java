package com.example.rahmatfauzi_152219;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtNama, txtPass;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNama = (EditText) findViewById(R.id.txt_user);
        txtPass = (EditText) findViewById(R.id.txt_pass);
        btnLogin = (Button) findViewById(R.id.btn_login);
        final Intent intent = new Intent(this, FrmDaftar.class);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtNama.getText().toString().equalsIgnoreCase("Rahmat Fauzi") && txtPass.getText().toString().equalsIgnoreCase("152219")) {
                    Toast.makeText(getBaseContext(), "Berhasil Login", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    return;
                }
                Toast.makeText(getBaseContext(), "Nama pengguna atau kata sandi tidak sesuai", Toast.LENGTH_SHORT).show();
            }
        });


    }
}