package com.example.myapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapters.ImagesAdapter;

import java.util.ArrayList;

public class ImagesActivity extends AppCompatActivity {

    RecyclerView images_RecyclerView;
    private ArrayList<Uri> imageUris;
    private ImagesAdapter imagesAdapter;
    private static final int REQUEST_PERMISSION = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);

        initialization();
        images_RecyclerView.setLayoutManager(new GridLayoutManager(this,2));

        if (checkPermission()) {
            fetchImages();
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(images_RecyclerView.getContext(),1);
            images_RecyclerView.addItemDecoration(dividerItemDecoration);
            images_RecyclerView.setAdapter(new ImagesAdapter(this,imageUris));

        } else {
            requestPermission();
        }


    }


    private boolean checkPermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSION);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                fetchImages(); // Fetch images when permission is granted
                // Fetch videos when permission is granted
            } else {
                Toast.makeText(this, "Permission denied. Cannot fetch media.", Toast.LENGTH_SHORT).show();
            }
        }

    }



    private void fetchImages() {
        imageUris.clear();
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Images.ImageColumns.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String imagePath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
                imageUris.add(Uri.parse("file://" + imagePath));
            }
            cursor.close();
           // imagesAdapter.notifyDataSetChanged();
        }

    }

    private void initialization() {
        images_RecyclerView = findViewById(R.id.images_RecyclerView);
        imageUris = new ArrayList<>();
        imagesAdapter = new ImagesAdapter();

    }
}