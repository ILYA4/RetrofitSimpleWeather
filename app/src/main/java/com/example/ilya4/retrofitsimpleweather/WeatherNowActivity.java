package com.example.ilya4.retrofitsimpleweather;


import android.Manifest;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

public class WeatherNowActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "WeatherNowActivity";

    private WeatherNowViewModel viewModel;
    private LiveData<WeatherPojo> weatherPojoLiveData;
    private ActivityWeatherNowBinding binding;
    private FusedLocationProviderClient mFusedLocationProviderClient;

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
                break;
                case R.id.action_location: GPS.getLocation(mFusedLocationProviderClient);
                break;
            }
        return super.onOptionsItemSelected(item);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

                checkPermission();
       // mSwipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
       //     mSwipeRefreshLayout.setOnRefreshListener(this);
            mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        binding = DataBindingUtil.setContentView(this,
                R.layout.activity_weather_now);
        viewModel = ViewModelProviders.of(this).get(WeatherNowViewModel.class);
        binding.swipeRefreshLayout.setOnRefreshListener(this);
        Log.v(TAG, "in onCreate method");
        refresh();

    }
    private void refresh (){
        weatherPojoLiveData = viewModel.getWeatherPojo(mFusedLocationProviderClient);
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

    public void checkPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                ){//Can add more as per requirement

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},
                    123);
        }
    }

    @Override
    public void onRefresh() {
        Log.v(TAG, "in onRefresh Method, updating data");
        refresh();
        binding.swipeRefreshLayout.setRefreshing(false);
    }

    public FusedLocationProviderClient getFusedLocationProviderClient() {
        return mFusedLocationProviderClient;
    }
}
