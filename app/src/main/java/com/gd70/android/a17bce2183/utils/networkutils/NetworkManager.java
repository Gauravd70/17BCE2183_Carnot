package com.gd70.android.a17bce2183.utils.networkutils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.gd70.android.a17bce2183.utils.Constants.BASE_URL;

public class NetworkManager {
    private final String TAG="Main_Retrofit";
    private String baseUrl;
    private Retrofit retrofit;
    private static NetworkManager networkManager;

    public static NetworkManager getNetworkManager() {
        if(networkManager==null)
            networkManager= new NetworkManager(BASE_URL);
        return networkManager;
    }

    /*
    *
    * constructor
    *
    * */
    private NetworkManager(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    /*
    *
    * get gson instance
    *
    * */
    private Gson getGsonInstance()
    {
        return new GsonBuilder().setLenient().create();
    }

    /*
    *
    * get http logger for debugging
    *
    * */
    private HttpLoggingInterceptor getInterceptor()
    {
        return new HttpLoggingInterceptor(s -> Log.d(TAG, "log: "+s)).setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    /*
    *
    * get http client
    *
    * */
    private OkHttpClient getOkHttpClient(HttpLoggingInterceptor interceptor)
    {
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .readTimeout(120, TimeUnit.SECONDS)
                .connectTimeout(120, TimeUnit.SECONDS)
                .connectionSpecs(Arrays.asList(ConnectionSpec.MODERN_TLS,ConnectionSpec.CLEARTEXT))
                .build();
    }

    /*
    *
    * get retrofit instance
    *
    * */
    public Retrofit getRetrofitInstance()
    {
        if(retrofit==null){
            retrofit=new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(getGsonInstance()))
                    .client(getOkHttpClient(getInterceptor()))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl(baseUrl)
                    .build();
        }
        return retrofit;
    }
}
