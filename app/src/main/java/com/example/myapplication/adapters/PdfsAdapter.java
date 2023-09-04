package com.example.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.PDFFile;

import java.util.List;

public class PdfsAdapter extends RecyclerView.Adapter<PdfsAdapter.PdfsAdapterViewHolder> {

    private List<PDFFile> pdfList;
    private Context context;
    public PdfsAdapter(Context context,List<PDFFile> pdfList) {
        this.context = context;
        this.pdfList = pdfList;
    }


    @NonNull
    @Override
    public PdfsAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.pdfs_recyclerview_layout,parent,false);
        return new PdfsAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PdfsAdapterViewHolder holder, int position) {

        PDFFile pdfFile = pdfList.get(position);
        holder.pdfs.setText(pdfFile.getName());
    }

    @Override
    public int getItemCount() {
        return pdfList.size();
    }

    public class PdfsAdapterViewHolder extends RecyclerView.ViewHolder {

        TextView pdfs;
        public PdfsAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            pdfs = itemView.findViewById(R.id.pdfs_recyclerview_layout_text);
        }
    }
}
