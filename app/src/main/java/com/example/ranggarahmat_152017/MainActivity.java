package com.example.ranggarahmat_152017;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText txtUser, txtPass;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUser = (EditText) findViewById(R.id.txt_user);
        txtPass = (EditText) findViewById(R.id.txt_pass);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtUser.getText().toString().equalsIgnoreCase("Rangga Rahmat") && txtPass.getText().toString().equalsIgnoreCase("152017")) {
                    Toast.makeText(getBaseContext(), "Berhasil login", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(getBaseContext(), "Username atau password tidak sesuai", Toast.LENGTH_SHORT).show();
            }
        });
    }
}