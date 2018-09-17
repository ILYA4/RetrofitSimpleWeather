package com.example.ilya4.retrofitsimpleweather.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.ilya4.retrofitsimpleweather.App;
import com.example.ilya4.retrofitsimpleweather.network.APIInterface;
import com.example.ilya4.retrofitsimpleweather.network.ApiClient;
import com.example.ilya4.retrofitsimpleweather.pojo.WeatherPojo;
import com.example.ilya4.retrofitsimpleweather.utils.APIKey;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherNowViewModel extends ViewModel {

    private MutableLiveData<WeatherPojo> weatherPojo;
    private static final String TAG = "WeatherNowViewModel";
    private APIInterface apiInterface;
    private SharedPreferences sp;

    public LiveData<WeatherPojo> getWeatherPojo() {
        if (weatherPojo==null){
            weatherPojo = new MutableLiveData<WeatherPojo>();
            loadWeatherPojo();
        }
        return weatherPojo;
    }

    private void loadWeatherPojo(){
        apiInterface = ApiClient.getRestrofitInstance().create(APIInterface.class);
        sp = PreferenceManager.getDefaultSharedPreferences(App.getContext());
        String city = sp.getString("city", "Saint Petersburg");
        Call<WeatherPojo> pojoCall = apiInterface.getWeather(city, APIKey.API_KEY);


        pojoCall.enqueue(new Callback<WeatherPojo>() {
            @Override
            public void onResponse(Call<WeatherPojo> call, Response<WeatherPojo> response) {
                Log.v(TAG, "onResponseMethod");
                Log.v(TAG, response.toString());
                WeatherPojo pojo = response.body();
                weatherPojo.postValue(pojo);
            }

            @Override
            public void onFailure(Call<WeatherPojo> call, Throwable t) {
                Log.e(TAG, "Error, onFailure");
            }
        });
    }

}
