package com.gd70.android.a17bce2183.uis.activities;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gd70.android.a17bce2183.R;
import com.gd70.android.a17bce2183.uis.fragments.FilterDialog;
import com.gd70.android.a17bce2183.uis.presenterlisteners.MainActivityPresenterListener;
import com.gd70.android.a17bce2183.uis.presenters.MainActivityPresenter;

import butterknife.BindView;
import butterknife.OnClick;

import static com.gd70.android.a17bce2183.utils.Constants.filterTag;

public class MainActivity extends BaseActivity<MainActivityPresenter> implements MainActivityPresenterListener {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(getPresenter().getAdapter());
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPresenter().getCommodities();
    }

    @Override
    public MainActivityPresenter bindPresenter() {
        return new MainActivityPresenter(this);
    }

    @OnClick(R.id.filter_button)
    void onFilter(){
        FilterDialog filterDialog=new FilterDialog();
        filterDialog.show(getSupportFragmentManager(),filterTag);
    }

}