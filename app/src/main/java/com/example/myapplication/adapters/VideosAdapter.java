package com.example.myapplication.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.activities.PlayRecyclerVideosActivity;

import java.util.ArrayList;

public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.VideosAdapterViewHolder> {

    private Context context;
    private ArrayList<Uri> videoUris;

    public VideosAdapter(Context context, ArrayList<Uri> videoUris) {
        this.context = context;
        this.videoUris = videoUris;
    }

    @NonNull
    @Override
    public VideosAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.videos_recyclerview_layout, parent, false);
        return new VideosAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideosAdapterViewHolder holder, int position) {

        Uri uri = videoUris.get(position);
        //holder.videoImageView.setVideoURI(Glide.with(holder.itemView.getContext()).load(uri).into());

        Glide.with(holder.itemView.getContext())
                .load(uri)
                .into(holder.videoImageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), PlayRecyclerVideosActivity.class);
                intent.putExtra("uris", uri.toString());
                holder.itemView.getContext().startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return videoUris.size();
    }

    public class VideosAdapterViewHolder extends RecyclerView.ViewHolder {

        ImageView videoImageView;

        public VideosAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            videoImageView = itemView.findViewById(R.id.videos_RecyclerView_layout_image);
        }
    }
}
