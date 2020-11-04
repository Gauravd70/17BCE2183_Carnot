package com.gd70.android.a17bce2183.uis.recycleradapters.viewholders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gd70.android.a17bce2183.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommodityViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.commodity_edit_text)
    public TextView commodityTextView;
    @BindView(R.id.variety_edit_text)
    public TextView varietyTextView;
    @BindView(R.id.state_edit_text)
    public TextView stateTextView;
    @BindView(R.id.district_edit_text)
    public TextView districtTextView;
    @BindView(R.id.market_edit_text)
    public TextView marketTextView;
    @BindView(R.id.price_edit_text)
    public TextView priceTextView;

    public CommodityViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
