package com.gd70.android.a17bce2183.uis.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gd70.android.a17bce2183.uis.presenterlisteners.BasePresenterListener;
import com.gd70.android.a17bce2183.uis.presenters.BasePresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

public abstract class BaseActivity<Presenter extends BasePresenter> extends AppCompatActivity implements BasePresenterListener {
    private Presenter presenter;
    private Unbinder activityUnBinder;

    public abstract Presenter bindPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(presenter==null)
            presenter=bindPresenter();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        if(activityUnBinder!=null)
        {
            activityUnBinder.unbind();
            activityUnBinder=null;
        }
        activityUnBinder= ButterKnife.bind(this);
    }

    /*
    *
    * getters
    *
    * */

    public Presenter getPresenter() {
        return presenter;
    }

    /*
    *
    * base presenter listener methods
    *
    * */

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showToast(final String toast) {
        Observable.just(toast)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> Toast.makeText(getContext(),toast,Toast.LENGTH_LONG).show());
    }

    /*
    *
    * transition functions
    *
    * */
    protected void transitionIntent(Class<?> c)
    {
        Intent intent = new Intent(getContext(),c);
        startActivity(intent);
    }
}