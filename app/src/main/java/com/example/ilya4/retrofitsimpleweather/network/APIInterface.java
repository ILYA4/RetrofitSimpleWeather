package com.example.ilya4.retrofitsimpleweather.network;


import com.example.ilya4.retrofitsimpleweather.pojo.WeatherPojo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface APIInterface {

   @GET("/data/2.5/weather?")
    Call<WeatherPojo> getWeather(@Query("q") String q, @Query("appid") String key);

}
