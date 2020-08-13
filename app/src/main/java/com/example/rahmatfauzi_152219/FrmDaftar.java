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

public class FrmDaftar extends AppCompatActivity {

    EditText txtNim, txtNama, txtEmail, txtTelp;
    CheckBox cbMakan, cbTrav, cbBaca;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button btnSave, btnClear;
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
                Toast.makeText(getBaseContext(), tampilan, Toast.LENGTH_LONG).show();
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