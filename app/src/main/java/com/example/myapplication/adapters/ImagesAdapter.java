package com.example.myapplication.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.activities.ImagesZoomInActivity;

import java.util.ArrayList;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ImagesViewHolder> {

    private Context context;
    private ArrayList<Uri> imageUris;
    public ImagesAdapter(Context context, ArrayList<Uri> imageUris) {
        this.context = context;
        this.imageUris = imageUris;
    }

    public ImagesAdapter() {
    }

    @NonNull
    @Override
    public ImagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.images_recyclerview_layout,parent,false);
        return new ImagesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImagesViewHolder holder, int position) {

        Uri uris = imageUris.get(position);
        holder.imageView.setImageURI(uris);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.imageView.getContext(), ImagesZoomInActivity.class);
                intent.putExtra("imageuri",uris.toString());
                holder.itemView.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return imageUris.size();
    }

    public class ImagesViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        public ImagesViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.images_RecyclerView_layout_image);
        }
    }
}
