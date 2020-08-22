package com.example.dasrialisainung_152214;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
    SQLiteDatabase db;

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

        db = openOrCreateDatabase("siaka", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS tbl_mhs(nim integer, nama varchar, email varchar, telp varchar, jenkel varchar, hobi varchar);");

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
                Boolean ceks = txtNim.getText().toString().equalsIgnoreCase("") || txtNama.getText().toString().equalsIgnoreCase("") || txtEmail.getText().toString().equalsIgnoreCase("") || txtTelp.getText().toString().equalsIgnoreCase("");
                try {
                    if (ceks) {
                        Toast.makeText(getBaseContext(), "Masih Kosong!", Toast.LENGTH_LONG).show();
                    } else {
                        db.execSQL("INSERT INTO tbl_mhs VALUES ('"+txtNim.getText() + "','"+txtNama.getText() + "','"+txtEmail.getText() + "','"+txtTelp.getText() + "', '"+jenkel.getText().toString()+"','"+cek+"');");
                        Toast.makeText(getBaseContext(),"Berhasil Tersimpan", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception ioe) {
                    ioe.printStackTrace();
                    Toast.makeText(getBaseContext(), "Gagal menyimpan", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Cursor c = db.rawQuery("SELECT * FROM tbl_mhs WHERE nama = '"+txtNama.getText()+"'", null);
                    if (c.moveToFirst()) {
                        Toast.makeText(getApplicationContext(), "NIM: " + c.getString(0) + "\nNama: " + c.getString(1) + "\nEmail: " + c.getString(2) + "\nTelp: " + c.getString(3) + "\nJenis Kelamin: " + c.getString(4) + "\nHobi: " + c.getString(5), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Tidak ditemukan!", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception ioe) {
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