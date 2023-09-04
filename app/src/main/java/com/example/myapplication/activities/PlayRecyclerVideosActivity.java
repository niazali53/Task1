package com.example.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

import com.example.myapplication.R;

public class PlayRecyclerVideosActivity extends AppCompatActivity {

    VideoView play_videos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_recycler_videos);
        initialization();

        Intent intent = getIntent();
        String uri = intent.getStringExtra("uris");
        Uri uriss = Uri.parse(uri);
        play_videos.setVideoURI(uriss);
        play_videos.requestFocus();
        play_videos.start();


    }

    private void initialization() {
        play_videos = findViewById(R.id.play_videos);
    }
}