package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
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

    private static final int PERMISSION_REQUEST_CODE = 1;
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

                if(checkPermission()){
                    Intent intent = new Intent(getApplicationContext(), ImagesActivity.class);
                    startActivity(intent);
                }
                else {
                    requestPermission();
                }

            }
        });

        videos_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (checkPermission()){
                    Intent intent = new Intent(getApplicationContext(), VideosActivity.class);
                    startActivity(intent);
                }
                else {requestPermission();}

            }
        });

        audio_Btn.setOnClickListener(new View.OnClickListener() {
            Intent intent = new Intent(getApplicationContext(), AudiosActivity.class);
            @Override
            public void onClick(View view) {
                if (checkPermission()){
                    startActivity(intent);
                }
                else {requestPermission();}
            }
        });

        pdfs_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermission()){
                    Intent intent = new Intent(getApplicationContext(), PdfsActivity.class);
                    startActivity(intent);
                }
                else {requestPermission();}
            }
        });
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == PERMISSION_REQUEST_CODE) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
////                fetchAudioFiles();
//            }
//        }
//    }

    private void initialization() {
        images_Btn = findViewById(R.id.images_btn);
        videos_Btn = findViewById(R.id.videos_btn);
        audio_Btn = findViewById(R.id.audio_btn);
        pdfs_Btn = findViewById(R.id.pdfs_btn);
    }
}