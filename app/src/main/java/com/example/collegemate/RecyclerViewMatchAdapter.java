package com.example.collegemate;

import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewMatchAdapter
        extends RecyclerView.Adapter<RecyclerViewMatchAdapter.ImageViewHolder> {

    List<GalleryImageMatchActivity> AdapterImages;

    int SelectedInd;
    OnItemClickListener onItemClickListener;

    public RecyclerViewMatchAdapter(List<GalleryImageMatchActivity> adapterImages,
                                    OnItemClickListener onItemClickListener) {
        AdapterImages = adapterImages;
        SelectedInd = -1;
        this.onItemClickListener = onItemClickListener;
    } //

    public List<GalleryImageMatchActivity> getAdapterImages() {
        return AdapterImages;
    } //

    public void setAdapterImages(List<GalleryImageMatchActivity> adapterImages) {
        AdapterImages = adapterImages;
        SelectedInd = -1;
    } //

    public int getSelectedInd() {
        return SelectedInd;
    } //

    public void setSelectedInd(int selectedInd) {
        SelectedInd = selectedInd;
    } //

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_item_match, parent, false);
        ImageViewHolder holder = new ImageViewHolder(itemView);
        return holder;
    } //

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {

        holder.imgViewItem
                .setImageResource(AdapterImages.get(position).getImgPic());
        holder.txtViewItem.setText(AdapterImages.get(position).getImgName());
        holder.itemView.setBackgroundColor(Color.parseColor("#1C1B1A"));
        if (position == SelectedInd){
            holder.itemView.setBackgroundColor(Color.parseColor("#303234"));
        } else {
            holder.itemView.setBackgroundColor(
                    Color.parseColor("#1C1B1A"));
        }

    }

    @Override
    public int getItemCount() {
        return AdapterImages.size();

    } //
    public class ImageViewHolder extends RecyclerView.ViewHolder{
        ImageView imgViewItem;
        TextView txtViewItem;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imgViewItem = itemView.findViewById(R.id.imageViewItem);
            txtViewItem = itemView.findViewById(R.id.textViewMatch);
            imgViewItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(getAdapterPosition());
                    SelectedInd = getAdapterPosition();
                    notifyDataSetChanged();
                }
            });
        }
    } //

    public interface OnItemClickListener {
        public void onItemClick(int i);
    } //
}

