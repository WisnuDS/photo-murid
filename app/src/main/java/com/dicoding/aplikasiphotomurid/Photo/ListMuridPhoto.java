package com.dicoding.aplikasiphotomurid.Photo;

import android.app.ActionBar;
import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.dicoding.aplikasiphotomurid.Adapter.MuridAdapter;
import com.dicoding.aplikasiphotomurid.Dataset.DataModel;
import com.dicoding.aplikasiphotomurid.Dataset.DbHelper;
import com.dicoding.aplikasiphotomurid.R;

import org.jetbrains.annotations.TestOnly;

import java.util.ArrayList;

public class ListMuridPhoto extends AppCompatActivity {
    RecyclerView rvMurid;
    ArrayList list = new ArrayList();
    final DbHelper dbHelper = new DbHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_murid_photo);
        final String category = getIntent().getStringExtra("EXTRA_CATEGORY");
//        ActionBar actionBar = getActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
        rvMurid = findViewById(R.id.rv_murid_photo);
        rvMurid.setHasFixedSize(true);
        list.addAll(dbHelper.getAllData());
        Log.d("data", "onCreate: afteraddAll");
        rvMurid.setLayoutManager(new LinearLayoutManager(this));
        final MuridAdapter listMuridAdapter = new MuridAdapter(list);
        rvMurid.setAdapter(listMuridAdapter);
        listMuridAdapter.setOnItemClickCallback(new MuridAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(DataModel dataModel) {
                Intent intent = new Intent(ListMuridPhoto.this,Photo.class);
                intent.putExtra("EXTRA_CATEGORY",category);
                intent.putExtra("EXTRA_NAME",dataModel.getNama());
                startActivityForResult(intent,3);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==3){
            if(data.getBooleanExtra("SAVED",false)){
                Toast.makeText(this,"Foto berhasil disimpan",Toast.LENGTH_SHORT).show();
            }
        }
    }


}
