package com.example.myapplication.activities;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.Toast;
import android.os.Build;
import android.provider.MediaStore;

import com.example.myapplication.R;
import com.example.myapplication.adapters.ImagesAdapter;
import com.example.myapplication.adapters.PdfsAdapter;
import com.example.myapplication.models.PDFFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PdfsActivity extends AppCompatActivity {

    private static final int REQUEST_PERMISSION = 101;
    private RecyclerView recyclerView;
    private PdfsAdapter pdfAdapter;
    private List<PDFFile> pdfFiles;
    ArrayList<String> pdfList;

    ArrayList<String> listofdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfs);
        initialization();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (checkPermission()) {

            PdfsAdapter pdfAdapter = new PdfsAdapter(this,getPDFFiles());
            recyclerView.setAdapter(pdfAdapter);

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
               // getPdfList(); // Fetch images when permission is granted
                // Fetch videos when permission is granted
            } else {
                Toast.makeText(this, "Permission denied. Cannot fetch media.", Toast.LENGTH_SHORT).show();
            }
        }

    }


    private List<PDFFile> getPDFFiles() {
        List<PDFFile> pdfFiles = new ArrayList<>();
        String directoryPath = Environment.getExternalStorageDirectory().toString();

        File directory = new File(directoryPath);
        File[] files = directory.listFiles();

        if (files!=null) {
            for (File file:files){
                if (file.isFile() && file.getName().endsWith(".pdf")){
                    Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
                }

            }
        }

        return pdfFiles;
    }


    private void initialization() {
        recyclerView = findViewById(R.id.pdfs_RecyclerView);
        //pdfAdapter = new PdfsAdapter(this,pdfFiles);
        recyclerView.setAdapter(pdfAdapter);
        pdfFiles = new ArrayList<PDFFile>();
    }
}