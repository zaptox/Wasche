package com.wasche.www.wasche.restcalls;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Vksoni on 7/11/2018.
 */

public class ApiClient {
    public static final String BASE_URL="http://zaptox.com/wasche_app/";
    public static Retrofit retrofit=null;

    public static Retrofit getApiClient(){
//
//        Gson gson=new GsonBuilder()
//                .setDateFormat("yyyy-MM-dd 'T'HH:mm:ssZ")
//                .create();
        if(retrofit==null){


        retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        }
        return retrofit;

    }

}
