package com.dicoding.aplikasiphotomurid.Photo;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.dicoding.aplikasiphotomurid.R;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;

public class
Photo extends AppCompatActivity {
    private static final int CAMERA_REQUEST = 1888;
    public static String str_SaveFolderName;
    @SuppressLint("StaticFieldLeak")
    public static ImageView imageView;
    static String str_Camera_Photo_ImagePath = "";
    static String str_Camera_Photo_ImageName = "";
    private static int Take_Photo = 2;

    String name, category;
    String currentPhotoPath;
    private Intent takePictureIntent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        imageView = findViewById(R.id.img_photo);
        Button photoButton = findViewById(R.id.btn_photo);
        name = getIntent().getStringExtra("EXTRA_NAME");
        category = getIntent().getStringExtra("EXTRA_CATEGORY");
        photoButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(takePictureIntent);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, Take_Photo);
                }
            }
        });
    }

    public String nextSessionId() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Take_Photo) {
            Bundle extras = data.getExtras();
            assert extras != null;
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            dispatchTakePictureIntent();
            imageView.setImageBitmap(imageBitmap);
        }
    }

    private File createImageFile() throws IOException {
        str_SaveFolderName = Environment
                .getExternalStorageDirectory()
                .getAbsolutePath()
                + "/PhotoTK/" + name + "/" + category + "/";
        String str_randomnumber = String.valueOf(nextSessionId());
        File wallpaperDirectory = new File(str_SaveFolderName);
        if (!wallpaperDirectory.exists())
            wallpaperDirectory.mkdirs();
        str_Camera_Photo_ImageName = str_randomnumber + ".jpg";
        str_Camera_Photo_ImagePath = str_SaveFolderName + "/" + str_randomnumber + ".jpg";
        File storageDir = getExternalFilesDir(str_SaveFolderName);
        File image = File.createTempFile(str_Camera_Photo_ImageName, ".jpg", storageDir);
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void dispatchTakePictureIntent() {
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ignored) {
            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.dicoding.aplikasiphotomurid.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, CAMERA_REQUEST);
            }
        }
    }


    @SuppressLint("Registered")
    public class GenericFileProvider extends FileProvider {
    }
}
