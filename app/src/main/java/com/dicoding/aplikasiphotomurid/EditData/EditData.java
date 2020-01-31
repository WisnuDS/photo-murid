package com.dicoding.aplikasiphotomurid.EditData;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dicoding.aplikasiphotomurid.Dataset.DbHelper;
import com.dicoding.aplikasiphotomurid.R;

public class EditData extends AppCompatActivity {
    EditText edtName;
    Button btnSave;
    DbHelper SQLite = new DbHelper(this);
    int idData;
    String name;
    Boolean isUpdate = false;
    Intent intent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);
        idData = getIntent().getIntExtra("EXTRA_ID_DATA_SISWA",0);
        name = getIntent().getStringExtra("EXTRA_NAMA_DATA_SISWA");
        edtName = findViewById(R.id.edt_name);
        intent.putExtra("SUCCES",false);
        setResult(2,intent);
        if (name!=null){
            edtName.setText(name);
            isUpdate = true;
        }
        btnSave = findViewById(R.id.btn_save_edit);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isUpdate){
                    update();
                }else{
                    save();
                }
            }
        });

    }

    private void save() {
        if (String.valueOf(edtName.getText()).equals(null) || String.valueOf(edtName.getText()).trim().equals("")) {
            Toast.makeText(getApplicationContext(),
                    "Please input name or address ...", Toast.LENGTH_SHORT).show();
        } else {
            SQLite.insert(edtName.getText().toString().trim());
            intent.putExtra("SUCCES",true);
            intent.putExtra("ADD",true);
            setResult(1,intent);
            finish();
        }
    }

    private void update() {
        if (String.valueOf(edtName.getText()).equals(null) || String.valueOf(edtName.getText()).equals("")) {
            Toast.makeText(getApplicationContext(),
                    "Please input name or address ...", Toast.LENGTH_SHORT).show();
        } else {
            SQLite.update(idData, edtName.getText().toString().trim());
            intent.putExtra("SUCCES",true);
            intent.putExtra("UP",true);
            setResult(2,intent);
            finish();
        }
    }
}
