package com.example.dasrialisainung_152214;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FrmDaftar extends AppCompatActivity {

    EditText txtNim, txtNama, txtEmail, txtTelp;
    RadioGroup _jenkel;
    CheckBox cbBaca, cbMakan, cbTravel;
    Button btnSave, btnClear, btnView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm_daftar);

        txtNim = (EditText) findViewById(R.id.txt_nim);
        txtNama = (EditText) findViewById(R.id.txt_nama);
        txtEmail = (EditText) findViewById(R.id.txt_email);
        txtTelp = (EditText) findViewById(R.id.txt_telepon);
        _jenkel = (RadioGroup) findViewById(R.id.rd_group);
        cbBaca = (CheckBox) findViewById(R.id.membaca);
        cbMakan = (CheckBox) findViewById(R.id.makan);
        cbTravel = (CheckBox) findViewById(R.id.travel);
        btnSave = (Button) findViewById(R.id.btn_save);
        btnClear = (Button) findViewById(R.id.btn_clear);
        btnView = (Button) findViewById(R.id.btn_view);

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtNim.getText().clear();
                txtNama.getText().clear();
                txtEmail.getText().clear();
                txtTelp.getText().clear();
                _jenkel.clearCheck();
                cbBaca.setChecked(false);
                cbMakan.setChecked(false);
                cbTravel.setChecked(false);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int idJenkel = _jenkel.getCheckedRadioButtonId();
                RadioButton jenkel = (RadioButton) findViewById(idJenkel);
                String cek = "";
                cek += check(cbBaca);
                cek += check(cbMakan);
                cek += check(cbTravel);
                String text = "Nim: " + txtNim.getText().toString() + "\nNama: " + txtNama.getText().toString() + "\nTelepon: " + txtTelp.getText().toString() + "\nEmail: " + txtEmail.getText().toString() + "\nJenis Kelamin: " + jenkel.getText().toString() + "\nHobby: " + cek;
                try {
                    FileOutputStream fileOutputStream = openFileOutput("DasrialiSainung_152214.txt", MODE_PRIVATE);
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
                    outputStreamWriter.write(text);
                    outputStreamWriter.flush();
                    outputStreamWriter.close();
                    Toast.makeText(getBaseContext(), "Sukses menyimpan", Toast.LENGTH_LONG).show();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                    Toast.makeText(getBaseContext(), "Gagal menyimpan", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileInputStream fileInputStream = openFileInput("DasrialiSainung_152214.txt");
                    InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                    char[] inputBuffer = new char[100];
                    String s = "";
                    int charRead;
                    while ((charRead = inputStreamReader.read(inputBuffer)) > 0) {
                        String readString = String.copyValueOf(inputBuffer,0, charRead);
                        s+=readString;
                        inputBuffer = new char[100];
                    }
                    Toast.makeText(getBaseContext(), s, Toast.LENGTH_LONG).show();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        });
    }

    String check(CheckBox checkBox) {
        String cek = "";
        if (checkBox.isChecked()) {
            cek += checkBox.getText().toString() + ",";
        }
        return cek;
    }
}