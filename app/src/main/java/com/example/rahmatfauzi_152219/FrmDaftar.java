package com.example.rahmatfauzi_152219;

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
    CheckBox cbMakan, cbTrav, cbBaca;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button btnSave, btnClear, btnView;
    String hobi = "";

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
                try {
                    FileOutputStream fileOutputStream = openFileOutput("RahmatFauzi_152219.txt", MODE_PRIVATE);
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
                    outputStreamWriter.write(tampilan);
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
                    FileInputStream fileInputStream = openFileInput("RahmatFauzi_152219.txt");
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

    private void checked(CheckBox checkBox) {
        if(checkBox.isChecked()) {
            hobi += checkBox.getText().toString() + ",";
        }
        return;
    }
}