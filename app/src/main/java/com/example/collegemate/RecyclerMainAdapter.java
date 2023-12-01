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

public class RecyclerMainAdapter extends RecyclerView.Adapter<RecyclerMainAdapter.ImageViewHolder>{
    List<GalleryMainActivity> AdapterText;
    int SelectedInd2;

    OnItemClickListener onItemClickListener;

    public RecyclerMainAdapter(List<GalleryMainActivity> adapterText, OnItemClickListener onItemClickListener) {
        AdapterText = adapterText;
        SelectedInd2 = -1;
        this.onItemClickListener = onItemClickListener;
    }

    public List<GalleryMainActivity> getAdapterText() {
        return AdapterText;
    }

    public void setAdapterText(List<GalleryMainActivity> adapterText) {
        AdapterText = adapterText;
        SelectedInd2 = -1;
    }

    public int getSelectedInd2() {
        return SelectedInd2;
    }

    public void setSelectedInd2(int selectedInd2) {
        SelectedInd2 = selectedInd2;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView2 = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_item_main, parent, false);
        ImageViewHolder holder2 = new ImageViewHolder(itemView2);
        return holder2;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerMainAdapter.ImageViewHolder holder, int position) {
        holder.txtViewItemMainNew.setText(AdapterText.get(position).getInfo2());
      }

    @Override
    public int getItemCount() {
        return AdapterText.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        TextView txtViewItemMainNew;
        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            txtViewItemMainNew = itemView.findViewById(R.id.textViewItemMain);
            txtViewItemMainNew.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(getAdapterPosition());
                    SelectedInd2 = getAdapterPosition();
                    notifyDataSetChanged();
                }
            });
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(int i);}

}




