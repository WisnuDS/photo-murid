package com.dicoding.aplikasiphotomurid.EditData;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.dicoding.aplikasiphotomurid.Dataset.DbHelper;
import com.dicoding.aplikasiphotomurid.R;

public class EditAndDelete extends AppCompatActivity implements View.OnClickListener {
    Button btnEdit, btnDelete;
    int idData;
    String nama;
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
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_edit_data){
            Intent edit = new Intent(EditAndDelete.this,EditData.class);
            edit.putExtra("EXTRA_ID_DATA_SISWA",idData);
            edit.putExtra("EXTRA_NAMA_DATA_SISWA",nama);
            startActivity(edit);
        }else if (v.getId() == R.id.btn_edit_delete){
            DbHelper dbHelper = new DbHelper(this);
            dbHelper.delete(idData);
            finish();
        }
    }
}
