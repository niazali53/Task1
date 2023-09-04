package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.activities.AudiosActivity;
import com.example.myapplication.activities.ImagesActivity;
import com.example.myapplication.activities.PdfsActivity;
import com.example.myapplication.activities.VideosActivity;

public class MainActivity extends AppCompatActivity {

    Button images_Btn;
    Button videos_Btn;
    Button audio_Btn;
    Button pdfs_Btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialization();

        images_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ImagesActivity.class);
                startActivity(intent);

            }
        });

        videos_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), VideosActivity.class);
                startActivity(intent);

            }
        });

        audio_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AudiosActivity.class);
                startActivity(intent);

            }
        });

        pdfs_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PdfsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initialization() {
        images_Btn = findViewById(R.id.images_btn);
        videos_Btn = findViewById(R.id.videos_btn);
        audio_Btn = findViewById(R.id.audio_btn);
        pdfs_Btn = findViewById(R.id.pdfs_btn);
    }
}