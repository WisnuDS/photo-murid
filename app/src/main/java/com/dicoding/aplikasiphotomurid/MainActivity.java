package com.dicoding.aplikasiphotomurid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dicoding.aplikasiphotomurid.EditData.DataSiswa;
import com.dicoding.aplikasiphotomurid.Photo.CategoryPhotos;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnPhoto,btnEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnEdit = findViewById(R.id.btn_edit);
        btnPhoto = findViewById(R.id.btn_photo);
        btnEdit.setOnClickListener(this);
        btnPhoto.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_edit){
            Intent editIntent = new Intent(MainActivity.this , DataSiswa.class);
            editIntent.putExtra("EXTRA_TYPE","EDIT_DATA");
            startActivity(editIntent);
        } else {
            Intent editIntent = new Intent(MainActivity.this , CategoryPhotos.class);
            editIntent.putExtra("EXTRA_TYPE","TAKE_PHOTO");
            startActivity(editIntent);
        }

    }
}
