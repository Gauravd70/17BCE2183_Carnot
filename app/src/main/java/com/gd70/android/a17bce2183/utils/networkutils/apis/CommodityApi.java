package com.gd70.android.a17bce2183.utils.networkutils.apis;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CommodityApi {

    @GET("9ef84268-d588-465a-a308-a864a43d0070?api-key=579b464db66ec23bdd000001cdd3946e44ce4aad7209ff7b23ac571b&format=json&limit=20")
    Call<JsonObject> getCommodities();

    @GET("9ef84268-d588-465a-a308-a864a43d0070?api-key=579b464db66ec23bdd000001cdd3946e44ce4aad7209ff7b23ac571b&format=json&limit=1")
    Call<JsonObject> getUpdateDate();
}
