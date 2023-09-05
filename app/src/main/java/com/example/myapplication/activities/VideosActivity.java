package com.example.myapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
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
import com.example.myapplication.adapters.VideosAdapter;

import java.util.ArrayList;

public class VideosActivity extends AppCompatActivity {

    RecyclerView videos_RecyclerView;
    private ArrayList<Uri> videosUris;
    private static final int REQUEST_PERMISSION = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);
        initialization();

        videos_RecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        fetchVideos();
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(videos_RecyclerView.getContext(), 1);
        videos_RecyclerView.addItemDecoration(dividerItemDecoration);
        videos_RecyclerView.setAdapter(new VideosAdapter(getApplicationContext(), videosUris));


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                fetchVideos(); // Fetch images when permission is granted
                // Fetch videos when permission is granted
            } else {
                Toast.makeText(this, "Permission denied. Cannot fetch media.", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void fetchVideos() {
        videosUris.clear();
        Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Video.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String videoPath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA));
                videosUris.add(Uri.parse("file://" + videoPath));
            }
            cursor.close();
            //VideosAdapter.notifyDataSetChanged();
        }

    }

    private void initialization() {
        videos_RecyclerView = findViewById(R.id.videos_RecyclerView);
        videosUris = new ArrayList<>();
    }
}