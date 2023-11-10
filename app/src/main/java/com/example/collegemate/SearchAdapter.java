package com.example.collegemate;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class SearchAdapter extends BaseAdapter {

    List<Search> adapterSearch;
    int SelectedInd = -1;

    public SearchAdapter(List<Search> adapterSearch, int selectedInd) {
        this.adapterSearch = adapterSearch;
        SelectedInd = selectedInd;
    }

    public List<Search> getAdapterSearch() {
        return adapterSearch;
    }

    public void setAdapterSearch(List<Search> adapterSearch) {
        this.adapterSearch = adapterSearch;
        notifyDataSetChanged();
    }

    public int getSelectedInd() {
        return SelectedInd;
    }

    public void setSelectedInd(int selectedInd) {
        SelectedInd = selectedInd;
        notifyDataSetChanged();
    }

    public SearchAdapter(List<Search> adapterSearch) {
        this.adapterSearch = adapterSearch;
    }

    @Override
    public int getCount() {
        return adapterSearch.size();
    }

    @Override
    public Object getItem(int i) {
        return adapterSearch.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            view = LayoutInflater.from(view.getContext()).inflate(R.layout.layout_searchitems, viewGroup, false);
        }
        TextView txtViewSearchItems = view.findViewById(R.id.txtViewSearchItems);
        txtViewSearchItems.setText(adapterSearch.get(i).getSearchItem());
        txtViewSearchItems.setGravity(Gravity.CENTER_VERTICAL);
        return view;
    }
}
