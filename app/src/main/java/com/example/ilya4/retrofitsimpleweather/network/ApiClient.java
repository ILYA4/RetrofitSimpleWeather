package com.example.ilya4.retrofitsimpleweather.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private final static String BASE_URL = "https://api.openweathermap.org";

    private static  Retrofit retrofit;

    public static Retrofit getRestrofitInstance() {

        if (retrofit==null){
            retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        }
        return retrofit;
    }
}
