package com.example.collegemate;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FloorPlansRecyclerViewAdapter extends RecyclerView.Adapter<FloorPlansRecyclerViewAdapter.ImageViewHolder > {

    List<FloorPlans> AdapterFloorPlans;

    public FloorPlansRecyclerViewAdapter(List<FloorPlans> adapterFloorPlans) {
        AdapterFloorPlans = adapterFloorPlans;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_floorplans,parent,false);
        ImageViewHolder holder = new ImageViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        holder.imgViewItem.setImageResource(AdapterFloorPlans.get(position).getFloorPlanPic());
        holder.txtViewItem.setText(AdapterFloorPlans.get(position).getFloorPlanName());
        holder.txtViewItem.setGravity(Gravity.CENTER);
    }

    @Override
    public int getItemCount() {
        return AdapterFloorPlans.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imgViewItem;
        TextView txtViewItem;
        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imgViewItem = itemView.findViewById(R.id.imgViewExternalItem);
            txtViewItem = itemView.findViewById(R.id.txtViewExternalItem);
        }
    }
}
