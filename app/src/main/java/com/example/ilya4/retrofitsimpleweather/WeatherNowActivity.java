package com.example.ilya4.retrofitsimpleweather;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import com.example.ilya4.retrofitsimpleweather.databinding.ActivityWeatherNowBinding;

import com.example.ilya4.retrofitsimpleweather.pojo.WeatherPojo;

import com.example.ilya4.retrofitsimpleweather.utils.GPS;
import com.example.ilya4.retrofitsimpleweather.viewmodel.PrefActivity;
import com.example.ilya4.retrofitsimpleweather.viewmodel.WeatherNowViewModel;

public class WeatherNowActivity extends AppCompatActivity {

    private static final String TAG = "WeatherNowActivity";

    private WeatherNowViewModel viewModel;
    private LiveData<WeatherPojo> weatherPojoLiveData;
    private  ActivityWeatherNowBinding binding;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_settings: startActivity(new Intent(WeatherNowActivity.this, PrefActivity.class));
            case R.id.action_location: GPS.getLocation();
            default: return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,
                R.layout.activity_weather_now);
        viewModel = ViewModelProviders.of(this).get(WeatherNowViewModel.class);

        Log.v(TAG, "in onCreate method");
        refresh();

    }
    private void refresh (){
        weatherPojoLiveData = viewModel.getWeatherPojo();
        weatherPojoLiveData.observe(this, new Observer<WeatherPojo>() {
            @Override
            public void onChanged(@Nullable WeatherPojo weatherPojo) {
                Log.v(TAG, "in onChanged method of .observe");
                if (weatherPojo!= null){
                    binding.setWeatherPojo(weatherPojo);
                }else binding.cityName.setText(R.string.city_not_found);
                //  binding.setWeather(weatherPojo.getWeather().get(0));//update UI here?
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        refresh();
    }
}
