package com.example.collegemate;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerMainAdapter extends RecyclerView.Adapter<RecyclerMainAdapter.ImageViewHolder>{
    List<GalleryMainActivity> AdapterImages2;

    int SelectedInd;
    RecyclerMainAdapter.OnItemClickListener onItemClickListener;

    public RecyclerMainAdapter(List<GalleryMainActivity> adapterImages2,
                                    RecyclerMainAdapter.OnItemClickListener onItemClickListener) {
        AdapterImages2 = adapterImages2;
        SelectedInd = -1;
        this.onItemClickListener = (OnItemClickListener) onItemClickListener;
    }

    public RecyclerMainAdapter(View itemView) {

    }


    public List<GalleryMainActivity> getAdapterImages() {
        return AdapterImages2;
    }

    public void setAdapterImages(List<GalleryMainActivity> adapterImages) {
        AdapterImages2 = adapterImages;
        SelectedInd = -1;
    }
    public int getSelectedInd() {
        return SelectedInd;
    }

    public void setSelectedInd(int selectedInd) {
        SelectedInd = selectedInd;
    }

    @NonNull
    @Override
    public RecyclerMainAdapter.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_item_main, parent, false);
        RecyclerMainAdapter.ImageViewHolder holder = new ImageViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerMainAdapter.ImageViewHolder holder, int position) {
             holder.txtViewItem.setText(AdapterImages2.get(position).getInfo2());
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
        return AdapterImages2.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int i);
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imgViewItem;
        TextView txtViewItem;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            txtViewItem = itemView.findViewById(R.id.textViewMatch);

        }
    }
}




