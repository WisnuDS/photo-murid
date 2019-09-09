package com.dicoding.aplikasiphotomurid.Photo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dicoding.aplikasiphotomurid.R;

public class CategoryPhotos extends AppCompatActivity implements View.OnClickListener {
    Button btnNam,btnSe,btnBahasa,btnKognitif,btnMotorikHls,btnMotorikKsr,btnSeni;
    String type;
    private static final String CATEGORY_NAM = "NAM";
    private static final String CATEGORY_SE = "SE";
    private static final String CATEGORY_BAHASA = "BAHASA";
    private static final String CATEGORY_KOGNITIF = "KOGNITIF";
    private static final String CATEGORY_MTRKHLS = "MOTORIK_HALUS";
    private static final String CATEGORY_MTRKKSR = "MOTORIK_KASAR";
    private static final String CATEGORY_SENI = "SENI";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_photos);
        btnNam = findViewById(R.id.btn_nam);
        btnSe = findViewById(R.id.btn_se);
        btnBahasa = findViewById(R.id.btn_bahasa);
        btnKognitif = findViewById(R.id.btn_kognitif);
        btnMotorikHls = findViewById(R.id.btn_motorik_halus);
        btnMotorikKsr = findViewById(R.id.btn_motorik_kasar);
        btnSeni = findViewById(R.id.btn_seni);
        btnSeni.setOnClickListener(this);
        btnSe.setOnClickListener(this);
        btnMotorikHls.setOnClickListener(this);
        btnMotorikKsr.setOnClickListener(this);
        btnKognitif.setOnClickListener(this);
        btnBahasa.setOnClickListener(this);
        btnNam.setOnClickListener(this);
        type = getIntent().getStringExtra("EXTRA_TYPE");

    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btn_nam){
            intentToList(CATEGORY_NAM);
        }else if (v.getId() == R.id.btn_se){
            intentToList(CATEGORY_SE);
        }else if (v.getId() == R.id.btn_bahasa){
            intentToList(CATEGORY_BAHASA);
        }else if (v.getId() == R.id.btn_kognitif){
            intentToList(CATEGORY_KOGNITIF);
        }else if (v.getId() == R.id.btn_motorik_halus){
            intentToList(CATEGORY_MTRKHLS);
        }else if (v.getId() == R.id.btn_motorik_kasar){
            intentToList(CATEGORY_MTRKKSR);
        }else if (v.getId() == R.id.btn_seni){
            intentToList(CATEGORY_SENI);
        }
    }

    public void intentToList(String category){
        Intent toList = new Intent(CategoryPhotos.this,ListMuridPhoto.class);
        toList.putExtra("EXTRA_CATEGORY",category);
        startActivity(toList);
    }
}
