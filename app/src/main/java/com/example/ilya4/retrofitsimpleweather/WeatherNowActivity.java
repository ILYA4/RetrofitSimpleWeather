package com.example.ilya4.retrofitsimpleweather;


import android.arch.lifecycle.Observer;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;



import com.example.ilya4.retrofitsimpleweather.databinding.ActivityWeatherNowBinding;

import com.example.ilya4.retrofitsimpleweather.pojo.WeatherPojo;

import com.example.ilya4.retrofitsimpleweather.viewmodel.WeatherNowViewModel;




public class WeatherNowActivity extends AppCompatActivity {


    private static final String TAG = "WeatherNowActivity";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityWeatherNowBinding binding = DataBindingUtil.setContentView(this,
                R.layout.activity_weather_now);

        Log.v(TAG, "in onCreate method");

        WeatherNowViewModel viewModel = ViewModelProviders.of(this).get(WeatherNowViewModel.class);
        viewModel.getWeatherPojo().observe(this, new Observer<WeatherPojo>() {
            @Override
            public void onChanged(@Nullable WeatherPojo weatherPojo) {
                Log.v(TAG, "in onChanged method of .observe");

                binding.setWeatherPojo(weatherPojo);
                binding.setWeather(weatherPojo.getWeather().get(0));//update UI here?
            }
        });





    }
}
