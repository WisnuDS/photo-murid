package com.dicoding.aplikasiphotomurid.EditData;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.dicoding.aplikasiphotomurid.Adapter.MuridAdapter;
import com.dicoding.aplikasiphotomurid.Dataset.DataModel;
import com.dicoding.aplikasiphotomurid.Dataset.DbHelper;
import com.dicoding.aplikasiphotomurid.R;

import java.util.ArrayList;

public class DataSiswa extends AppCompatActivity {
    RecyclerView rvMurid;
    Button refresh;
    ArrayList<DataModel> list = new ArrayList<>();
    final DbHelper dbHelper = new DbHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_siswa);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        refresh = findViewById(R.id.btn_refresh);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addIntent = new Intent(DataSiswa.this, EditData.class);
                startActivity(addIntent);
            }
        });

        rvMurid = findViewById(R.id.rv_murid);
        rvMurid.setHasFixedSize(true);
        list.addAll(dbHelper.getAllData());
        Log.d("data", "onCreate: afteraddAll");
        rvMurid.setLayoutManager(new LinearLayoutManager(this));
        final MuridAdapter listMuridAdapter = new MuridAdapter(list);
        rvMurid.setAdapter(listMuridAdapter);
        listMuridAdapter.setOnItemClickCallback(new MuridAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(DataModel dataModel) {
                Intent editDelete = new Intent(DataSiswa.this,EditAndDelete.class);
                editDelete.putExtra("EXTRA_ID_DATA_SISWA", dataModel.getId());
                editDelete.putExtra("EXTRA_NAMA_DATA_SISWA",dataModel.getNama());
                startActivity(editDelete);
            }
        });
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                list.addAll(dbHelper.getAllData());
                listMuridAdapter.notifyDataSetChanged();
                rvMurid.invalidate();
                rvMurid.refreshDrawableState();
            }
        });
    }
}
