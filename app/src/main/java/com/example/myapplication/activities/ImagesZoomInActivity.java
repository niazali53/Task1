package com.example.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.myapplication.R;

public class ImagesZoomInActivity extends AppCompatActivity {

    ImageView imageView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images_zoom_in);

        Intent intent = getIntent();
        imageView = findViewById(R.id.imageZoomView);
        imageView.setImageURI(Uri.parse(intent.getStringExtra("imageuri")));


    }
}