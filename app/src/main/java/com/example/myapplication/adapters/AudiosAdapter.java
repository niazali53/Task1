package com.example.myapplication.adapters;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.AudioFile;

import java.io.IOException;
import java.util.List;

public class AudiosAdapter extends RecyclerView.Adapter<AudiosAdapter.AudiosAdapterViewHolder> {

    private List<AudioFile> audioFiles;
    MediaPlayer mediaPlayer;
    boolean state = true;
    public AudiosAdapter(List<AudioFile> audioFiles) {
        this.audioFiles = audioFiles;
    }

    @NonNull
    @Override
    public AudiosAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.audios_recyclerview_layout,parent,false);
        return new AudiosAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AudiosAdapterViewHolder holder, @SuppressLint("RecyclerView") int position) {
        mediaPlayer = new MediaPlayer();
        AudioFile audioFile = audioFiles.get(position);
        holder.audios_recycler_layout_text.setText(audioFile.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.reset(); // Reset the MediaPlayer to its idle state
                try {

                    mediaPlayer.setDataSource(audioFiles.get(position).getPath()); // Set the data source to the audio file path
                    mediaPlayer.prepare(); // Prepare the MediaPlayer
                    mediaPlayer.start();

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });


    }


    @Override
    public int getItemCount() {
        return audioFiles.size();
    }

    public class AudiosAdapterViewHolder extends RecyclerView.ViewHolder {

        TextView audios_recycler_layout_text;
        public AudiosAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            audios_recycler_layout_text = itemView.findViewById(R.id.audios_recycler_layout_text);
        }
    }
}
