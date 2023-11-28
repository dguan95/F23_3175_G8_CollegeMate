package com.example.collegemate;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class SearchAdapter2 extends BaseAdapter {

    List<Search> adapterSearch2;
    int SelectedInd = -1;

    public SearchAdapter2(List<Search> adapterSearch2, int selectedInd) {
        this.adapterSearch2 = adapterSearch2;
        SelectedInd = selectedInd;
    }

    public SearchAdapter2(List<Search> adapterSearch2) {
        this.adapterSearch2 = adapterSearch2;
    }

    public List<Search> getAdapterSearch2() {
        return adapterSearch2;
    }

    public void setAdapterSearch2(List<Search> adapterSearch2) {
        this.adapterSearch2 = adapterSearch2;
    }

    public int getSelectedInd() {
        return SelectedInd;
    }

    public void setSelectedInd(int selectedInd) {
        SelectedInd = selectedInd;
    }

    @Override
    public int getCount() {
        return adapterSearch2.size();
    }

    @Override
    public Object getItem(int i) {
        return adapterSearch2.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_searchitems, viewGroup, false);
            TextView txtViewSearchItems = view.findViewById(R.id.txtViewSearchItems);
            txtViewSearchItems.setText(adapterSearch2.get(i).getSearchItem());
            txtViewSearchItems.setGravity(Gravity.CENTER_VERTICAL);
        };
        return view;
    }
}
