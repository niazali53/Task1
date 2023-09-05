package com.example.myapplication.activities;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;
import android.media.audiofx.Equalizer;
import com.example.myapplication.R;
import com.example.myapplication.adapters.PdfsAdapter;
import java.io.File;
import java.util.ArrayList;

public class PdfsActivity extends AppCompatActivity {

    private static final int REQUEST_PERMISSION = 101;
    private RecyclerView recyclerView;
    ArrayList<File> pdfFiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfs);
        initialization();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        retrievePDFs();
        PdfsAdapter pdfAdapter = new PdfsAdapter(this, pdfFiles);
        recyclerView.setAdapter(pdfAdapter);




    }

    private void retrievePDFs() {
        File root = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
        findPDFs(root);
    }

    private void findPDFs(File dir) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    findPDFs(file);
                } else if (file.isFile() && file.getPath().endsWith(".pdf")) {
                    pdfFiles.add(file);
                }
            }
        }

    }

    
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                retrievePDFs(); // Fetch images when permission is granted
                // Fetch videos when permission is granted
            } else {
                Toast.makeText(this, "Permission denied. Cannot fetch media.", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void initialization() {
        recyclerView = findViewById(R.id.pdfs_RecyclerView);
        pdfFiles = new ArrayList<>();
    }
}