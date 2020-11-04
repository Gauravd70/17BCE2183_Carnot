package com.gd70.android.a17bce2183.uis.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.gd70.android.a17bce2183.R;
import com.gd70.android.a17bce2183.utils.dbutils.CommodityDatabase;
import com.gd70.android.a17bce2183.utils.dbutils.daos.CommodityDao;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.schedulers.Schedulers;

public class FilterDialog extends DialogFragment {
    @BindView(R.id.state_spinner)
    Spinner stateSpinner;
    @BindView(R.id.district_spinner)
    Spinner districtSpinner;
    @BindView(R.id.market_spinner)
    Spinner marketSpinner;

    private View view;
    private CommodityDao commodityDao;

    private List<String> states,districts,markets;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init(){
        commodityDao= CommodityDatabase.getCommodityDatabase(getContext()).commodityDao();
        states=new ArrayList<>();
        districts=new ArrayList<>();
        markets=new ArrayList<>();
        Observable.just(1)
                .subscribeOn(Schedulers.newThread())
                .subscribe(integer -> states.addAll(commodityDao.getStates()));
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_fragment_filter,null,false);
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext())
                .setView(view);
        return builder.show();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ButterKnife.bind(this,view);
    }


}
