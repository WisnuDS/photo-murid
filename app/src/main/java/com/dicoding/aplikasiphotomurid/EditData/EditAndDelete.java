package com.dicoding.aplikasiphotomurid.EditData;

import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.dicoding.aplikasiphotomurid.Dataset.DbHelper;
import com.dicoding.aplikasiphotomurid.R;

public class EditAndDelete extends AppCompatActivity implements View.OnClickListener {
    Button btnEdit, btnDelete;
    int idData;
    String nama;
    Intent intent = new Intent();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_and_delete);
        btnEdit = findViewById(R.id.btn_edit_data);
        btnDelete = findViewById(R.id.btn_edit_delete);
        btnEdit.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        idData = getIntent().getIntExtra("EXTRA_ID_DATA_SISWA",0);
        nama = getIntent().getStringExtra("EXTRA_NAMA_DATA_SISWA");
        intent.putExtra("SUCCES",false);
        intent.putExtra("UP",false);
        setResult(1,intent);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_edit_data){
            Intent edit = new Intent(EditAndDelete.this,EditData.class);
            edit.putExtra("EXTRA_ID_DATA_SISWA",idData);
            edit.putExtra("EXTRA_NAMA_DATA_SISWA",nama);
            startActivityForResult(edit,2);
        }else if (v.getId() == R.id.btn_edit_delete){
            DbHelper dbHelper = new DbHelper(this);
            dbHelper.delete(idData);
            intent.putExtra("SUCCES",true);
            intent.putExtra("DEL",true);
            setResult(1,intent);
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==2){
            if(data.getBooleanExtra("SUCCES",true)){
                if(data.getBooleanExtra("UP",false)){
                    intent.putExtra("SUCCES",true);
                    intent.putExtra("UP",true);
                    setResult(1,intent);
                    finish();
                }
            }
        }
    }
}
