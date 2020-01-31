package com.dicoding.aplikasiphotomurid.Photo;


import android.annotation.SuppressLint;
import android.content.Intent;
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
import com.bumptech.glide.Glide;
import com.dicoding.aplikasiphotomurid.R;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Photo extends AppCompatActivity {
    private static final int CAMERA_REQUEST = 1;
    @SuppressLint("StaticFieldLeak")
    public static ImageView imageView;
    Button photoButton, saveButton;
    Intent intent = new Intent();
    private Uri photoURI;
    private String name, category;
    private Intent takePictureIntent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent.putExtra("SAVED", false);
        setResult(3,intent);
        setContentView(R.layout.activity_photo);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        imageView = findViewById(R.id.img_photo);
        photoButton = findViewById(R.id.btn_photo);
        saveButton = findViewById(R.id.btn_save);
        name = getIntent().getStringExtra("EXTRA_NAME");
        category = getIntent().getStringExtra("EXTRA_CATEGORY");
        if(savedInstanceState != null){
            String uri = savedInstanceState.getString("URI");
            if(uri != null){
                photoURI = Uri.parse(uri);
                Glide.with(this)
                        .load(photoURI)
                        .centerCrop()
                        .into(imageView);
                intent.putExtra("SAVED", true);
                setResult(3,intent);
                photoButton.setVisibility(View.GONE);
                saveButton.setVisibility(View.VISIBLE);
            }
        }
        photoButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                dispatchTakePictureIntent();
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            photoButton.setVisibility(View.GONE);
            saveButton.setVisibility(View.VISIBLE);
            Glide.with(this)
                    .load(photoURI)
                    .centerCrop()
                    .into(imageView);
            intent.putExtra("SAVED", true);
            setResult(3,intent);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("URI", String.valueOf(photoURI));
    }

    private File createImageFile() throws IOException {
        @SuppressLint("SimpleDateFormat") String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES+"/"+name+"/"+category);

        // Save a file: path for use with ACTION_VIEW intents
        return File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
    }

    private void dispatchTakePictureIntent() {
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ignored) {
            }
            if (photoFile != null) {
                photoURI = FileProvider.getUriForFile(this,
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
