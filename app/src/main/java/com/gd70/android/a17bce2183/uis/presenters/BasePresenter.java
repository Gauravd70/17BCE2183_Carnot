package com.gd70.android.a17bce2183.uis.presenters;

import android.content.Context;
import android.content.SharedPreferences;

import com.gd70.android.a17bce2183.uis.presenterlisteners.BasePresenterListener;
import com.gd70.android.a17bce2183.utils.dbutils.CommodityDatabase;
import com.gd70.android.a17bce2183.utils.dbutils.daos.CommodityDao;
import com.gd70.android.a17bce2183.utils.networkutils.NetworkManager;

import retrofit2.Retrofit;

public abstract class BasePresenter<PresenterListener extends BasePresenterListener> {
    private PresenterListener presenterListener;
    private NetworkManager networkManager;
    protected String TAG;
    private CommodityDatabase commodityDatabase;

    public BasePresenter(PresenterListener presenterListener,String className) {
        TAG="Main_".concat(className);
        this.presenterListener = presenterListener;
        networkManager=NetworkManager.getNetworkManager();
        commodityDatabase=CommodityDatabase.getCommodityDatabase(presenterListener.getContext());
    }

    /*
    *
    * getters
    *
    * */
    protected PresenterListener getPresenterListener() {
        return presenterListener;
    }

    protected Retrofit getRetrofit(){
        return networkManager.getRetrofitInstance();
    }

    public CommodityDao getCommodityDao() {
        return commodityDatabase.commodityDao();
    }

    /*
     *
     * add/retrieve values from shared preferences
     *
     * */

    private SharedPreferences getSharedPreference(String sharedPreferenceName)
    {
        return presenterListener.getContext().getSharedPreferences(sharedPreferenceName, Context.MODE_PRIVATE);
    }

    private SharedPreferences.Editor getSharedPreferenceEditor(String sharedPreferenceName)
    {
        return getSharedPreference(sharedPreferenceName).edit();
    }

    protected void addToSharedPreferences(String sharedPreferenceName,String key,String value)
    {
        SharedPreferences.Editor editor=getSharedPreferenceEditor(sharedPreferenceName);
        editor.putString(key,value);
        editor.apply();
    }

    protected String getFromSharedPreferences(String sharedPreferenceName,String key)
    {
        SharedPreferences sharedPreferences=getSharedPreference(sharedPreferenceName);
        return sharedPreferences.getString(key,null);
    }
}
