package com.example.collegemate;

import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewMatchAdapter extends RecyclerView.Adapter<RecyclerViewMatchAdapter.ImageViewHolder> {

    List<GalleryImageMatchActivity> AdapterImages;

    public RecyclerViewMatchAdapter(List<GalleryImageMatchActivity> adapterImages) {
        AdapterImages = adapterImages;
    }
    @NonNull
    @Override
    public RecyclerViewMatchAdapter.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_item_match, parent, false);
        ImageViewHolder holder = new ImageViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewMatchAdapter.ImageViewHolder holder, int position) {

        holder.imgViewItem.setImageResource(AdapterImages.get(position).getImgPic());
        holder.itemView.setBackgroundColor(Color.parseColor("#3F704D"));

    }

    @Override
    public int getItemCount() {
        return AdapterImages.size();

    }
    public class ImageViewHolder extends RecyclerView.ViewHolder{
        ImageView imgViewItem;
        TextView textViewItem;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imgViewItem = itemView.findViewById(R.id.imageViewItem);
        }
    }
}
