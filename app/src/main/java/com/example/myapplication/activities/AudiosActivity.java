package com.example.myapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.adapters.AudiosAdapter;
import com.example.myapplication.models.AudioFile;

import java.util.ArrayList;
import java.util.List;

public class AudiosActivity extends AppCompatActivity {

    RecyclerView audio_RecyclerView;
    private static final int PERMISSION_REQUEST_CODE = 1;
    private Button buttonFetchAudio;
    private AudiosAdapter audiosAdapter;
    private List<AudioFile> audioFiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audios);
        initialization();

        audio_RecyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchAudioFiles();
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(audio_RecyclerView.getContext(), 1);
        audio_RecyclerView.addItemDecoration(dividerItemDecoration);
        audio_RecyclerView.setAdapter(new AudiosAdapter(audioFiles));

    }

    private void initialization() {
        audio_RecyclerView = findViewById(R.id.audio_RecyclerView);
        audioFiles = new ArrayList<>();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                fetchAudioFiles();
            }
        }
    }


    private void fetchAudioFiles() {
        audioFiles.clear();

        // Query audio files using MediaStore
        String[] projection = {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DISPLAY_NAME
        };

        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";

        try (Cursor cursor = getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                selection,
                null,
                null)) {

            if (cursor != null) {
                while (cursor.moveToNext()) {
                    @SuppressLint("Range") String filePath = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                    @SuppressLint("Range") String fileName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                    audioFiles.add(new AudioFile(fileName, filePath));
                }
                //audioAdapter.notifyDataSetChanged();
            }


        }
    }
}