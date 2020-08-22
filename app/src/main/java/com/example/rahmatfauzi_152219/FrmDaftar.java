package com.example.rahmatfauzi_152219;

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
    CheckBox cbMakan, cbTrav, cbBaca;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button btnSave, btnClear, btnView;
    String hobi = "";
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm_daftar);

        txtNim = (EditText) findViewById(R.id.txt_nim);
        txtNama = (EditText) findViewById(R.id.txt_nama);
        txtEmail = (EditText) findViewById(R.id.txt_email);
        txtTelp = (EditText) findViewById(R.id.txt_telp);

        cbMakan = (CheckBox) findViewById(R.id.cb_makan);
        cbTrav = (CheckBox) findViewById(R.id.cb_trav);
        cbBaca = (CheckBox) findViewById(R.id.cb_baca);

        radioGroup = (RadioGroup) findViewById(R.id.rd_jenkel);

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
                cbMakan.setChecked(false);
                cbTrav.setChecked(false);
                cbBaca.setChecked(false);
                radioGroup.clearCheck();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checked(cbMakan);
                checked(cbBaca);
                checked(cbTrav);
                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);
                String tampilan = "NIM : " + txtNim.getText().toString() + "\nNama : " + txtNama.getText().toString() + "\nTelepon : " + txtTelp.getText().toString() + "\nEmail : " + txtEmail.getText().toString() + "\nJenis Kelamin : " + radioButton.getText().toString() + "\nHobby : " + hobi;
//                Toast.makeText(getBaseContext(), tampilan, Toast.LENGTH_LONG).show();
                Boolean cek = txtNim.getText().toString().equalsIgnoreCase("") || txtNama.getText().toString().equalsIgnoreCase("") || txtEmail.getText().toString().equalsIgnoreCase("") || txtTelp.getText().toString().equalsIgnoreCase("");
                try {
                    if (cek) {
                        Toast.makeText(getBaseContext(), "Masih Kosong!", Toast.LENGTH_LONG).show();
                    } else {
                        db.execSQL("INSERT INTO tbl_mhs VALUES ('"+txtNim.getText() + "','"+txtNama.getText() + "','"+txtEmail.getText() + "','"+txtTelp.getText() + "', '"+radioButton.getText().toString()+"','"+hobi+"');");
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

    private void checked(CheckBox checkBox) {
        if(checkBox.isChecked()) {
            hobi += checkBox.getText().toString() + ",";
        }
        return;
    }
}