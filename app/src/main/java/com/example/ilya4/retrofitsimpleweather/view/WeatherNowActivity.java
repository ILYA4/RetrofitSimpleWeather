package com.example.ilya4.retrofitsimpleweather.view;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import com.example.ilya4.retrofitsimpleweather.R;
import com.example.ilya4.retrofitsimpleweather.databinding.ActivityWeatherNowBinding;
import com.example.ilya4.retrofitsimpleweather.network.APIInterface;
import com.example.ilya4.retrofitsimpleweather.network.ApiClient;
import com.example.ilya4.retrofitsimpleweather.pojo.WeatherPojo;
import com.example.ilya4.retrofitsimpleweather.utils.APIKey;
import com.example.ilya4.retrofitsimpleweather.viewmodel.WeatherNowViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherNowActivity extends AppCompatActivity {


    private static final String TAG = "WeatherNowActivity";

    private WeatherPojo weatherPojo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityWeatherNowBinding binding = DataBindingUtil.setContentView(this,
                R.layout.activity_weather_now);

        WeatherNowViewModel viewModel = ViewModelProviders.of(this).get(WeatherNowViewModel.class);
        viewModel.getWeatherPojo().observe(this, new Observer<WeatherPojo>() {
            @Override
            public void onChanged(@Nullable WeatherPojo weatherPojo) {
                binding.setWeatherPojo(weatherPojo);
            }
        });





    }
}
