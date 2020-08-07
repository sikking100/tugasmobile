package com.example.ilman_152011;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText txtUser, txtPass;
        Button btnLogin;

        txtUser = (EditText) findViewById(R.id.txt_user);
        txtPass = (EditText) findViewById(R.id.txt_pass);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtUser.getText().toString().equalsIgnoreCase("Ilman") && txtPass.getText().toString().equalsIgnoreCase("152011")) {
                    Toast.makeText(getBaseContext(), "Berhasil Login", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(getBaseContext(), "Nama pengguna atau kata sandi tidak sesuai", Toast.LENGTH_SHORT).show();
            }
        });
    }
}