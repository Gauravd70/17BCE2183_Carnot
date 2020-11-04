package com.gd70.android.a17bce2183.uis.recycleradapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gd70.android.a17bce2183.R;
import com.gd70.android.a17bce2183.models.CommodityModel;
import com.gd70.android.a17bce2183.uis.recycleradapters.viewholders.CommodityViewHolder;

import java.util.ArrayList;
import java.util.List;

public class CommodityRecyclerAdapter extends RecyclerView.Adapter<CommodityViewHolder> {
    private List<CommodityModel> models;

    public CommodityRecyclerAdapter() {
        models=new ArrayList<>();
    }

    public void addCommodity(CommodityModel commodityModel){
        models.add(commodityModel);
        notifyItemInserted(models.size());
    }

    public void removeCommodity(int position){
        models.remove(position);
        notifyItemRemoved(position);
    }

    public void addAllCommodities(List<CommodityModel> models){
        this.models.clear();
        this.models.addAll(models);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CommodityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_row_commodity_card,parent,false);
        return new CommodityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommodityViewHolder holder, int position) {
        CommodityModel model=models.get(position);
        holder.commodityTextView.setText(model.getCommodity());
        holder.varietyTextView.setText(model.getVariety());
        holder.stateTextView.setText(model.getState());
        holder.districtTextView.setText(model.getDistrict());
        holder.marketTextView.setText(model.getMarket());
        holder.priceTextView.setText(String.valueOf(model.getModalPrice()));
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
