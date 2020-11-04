package com.gd70.android.a17bce2183.uis.presenters;

import android.util.Log;

import androidx.annotation.NonNull;

import com.gd70.android.a17bce2183.models.CommodityModel;
import com.gd70.android.a17bce2183.uis.presenterlisteners.MainActivityPresenterListener;
import com.gd70.android.a17bce2183.uis.recycleradapters.CommodityRecyclerAdapter;
import com.gd70.android.a17bce2183.utils.CommodityConverter;
import com.gd70.android.a17bce2183.utils.networkutils.apis.CommodityApi;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.gd70.android.a17bce2183.utils.Constants.Network_Call_Failed;
import static com.gd70.android.a17bce2183.utils.Constants.UpdateSPKey;
import static com.gd70.android.a17bce2183.utils.Constants.UpdateSharedPreference;
import static com.gd70.android.a17bce2183.utils.Constants.recordsKey;
import static com.gd70.android.a17bce2183.utils.Constants.updatedDateKey;

public class MainActivityPresenter extends BasePresenter<MainActivityPresenterListener> {
    private CommodityRecyclerAdapter adapter;

    public MainActivityPresenter(MainActivityPresenterListener presenterListener) {
        super(presenterListener,MainActivityPresenter.class.getSimpleName());
        adapter=new CommodityRecyclerAdapter();
    }

    public CommodityRecyclerAdapter getAdapter() {
        return adapter;
    }

    public void getCommodities(){
        String date=getFromSharedPreferences(UpdateSharedPreference,UpdateSPKey);
        if(date==null) {
            syncDatabase();
            return;
        }
        
        getRetrofit().create(CommodityApi.class).getUpdateDate().enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(@NonNull Call<JsonObject> call,@NonNull Response<JsonObject> response) {
                if(response.isSuccessful()) {
                    if(response.body()!=null) {
                        String temp = response.body().get(updatedDateKey).toString();
                        if (!date.equals(temp)) {
                            syncDatabase();
                            return;
                        }
                    }
                }
                getCommoditiesFromDB();
            }

            @Override
            public void onFailure(@NonNull Call<JsonObject> call,@NonNull Throwable t) {
                Log.d(TAG, "onFailure: "+t);
                getPresenterListener().showToast(Network_Call_Failed);
            }
        });
    }

    private void syncDatabase(){
        getRetrofit().create(CommodityApi.class).getCommodities().enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(@NonNull Call<JsonObject> call, @NonNull Response<JsonObject> response) {
                if(response.isSuccessful()){
                    if(response.body()!=null) {
                        List<CommodityModel> models=new CommodityConverter(response.body().get(recordsKey)).convert();
                        adapter.addAllCommodities(models);
                        addCommoditiesToDB(models);
                        addToSharedPreferences(UpdateSharedPreference,UpdateSPKey,response.body().get(updatedDateKey).toString());
                        return;
                    }
                }
                getCommoditiesFromDB();
            }

            @Override
            public void onFailure(@NonNull Call<JsonObject> call,@NonNull Throwable t) {
                Log.d(TAG, "onFailure: "+t);
                getPresenterListener().showToast(Network_Call_Failed);
            }
        });
    }

    private void addCommoditiesToDB(List<CommodityModel> commodityModels){
        Observable.from(commodityModels)
                .subscribeOn(Schedulers.newThread())
                .subscribe(commodityModel -> getCommodityDao().insertCommodity(commodityModel));
    }

    private void getCommoditiesFromDB(){
        List<CommodityModel> models=new ArrayList<>();
        Observable.just(models)
                .subscribeOn(Schedulers.newThread())
                .doOnCompleted(() -> Observable.just(1)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(integer -> adapter.addAllCommodities(models)))
                .subscribe(commodityModels -> commodityModels.addAll(getCommodityDao().getCommodities()));
    }
}
