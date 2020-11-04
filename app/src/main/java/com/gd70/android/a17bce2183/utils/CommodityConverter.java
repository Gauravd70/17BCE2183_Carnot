package com.gd70.android.a17bce2183.utils;

import com.gd70.android.a17bce2183.models.CommodityModel;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;

public class CommodityConverter {
    private JsonElement jsonElement;

    public CommodityConverter(JsonElement jsonElement) {
        this.jsonElement=jsonElement;
    }

    public List<CommodityModel> convert(){
        List<CommodityModel> models=new ArrayList<>();
        Observable.from((JsonArray)jsonElement)
                .flatMap((Func1<JsonElement, Observable<CommodityModel>>) jsonElement -> Observable.just(new Gson().fromJson(jsonElement,CommodityModel.class)))
                .subscribe(models::add);
        return models;
    }
}
