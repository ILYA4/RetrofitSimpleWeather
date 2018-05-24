package com.example.ilya4.retrofitsimpleweather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.ilya4.retrofitsimpleweather.network.APIInterface;
import com.example.ilya4.retrofitsimpleweather.network.ApiClient;
import com.example.ilya4.retrofitsimpleweather.pojo.WeatherPojo;
import com.example.ilya4.retrofitsimpleweather.utils.APIKey;
import com.example.ilya4.retrofitsimpleweather.utils.Formuls;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    APIInterface apiInterface;
    ApiClient apiClient = new ApiClient();
    TextView mainText;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainText = findViewById(R.id.main_text);

        apiInterface = apiClient.getRetrofit().create(APIInterface.class);

        Call<WeatherPojo> pojoCall = apiInterface.getWeather("Moscow", APIKey.API_KEY);

        pojoCall.enqueue(new Callback<WeatherPojo>() {
            @Override
            public void onResponse(Call<WeatherPojo> call, Response<WeatherPojo> response) {
                Log.e(TAG, "inOnResponse");
                WeatherPojo data = response.body();
                Log.e(TAG, response.toString());
                mainText.setText(data.getName() + " " + Formuls.getTempCelsius(data.getMain().getTemp()) +"°С");
            }

            @Override
            public void onFailure(Call<WeatherPojo> call, Throwable t) {
                Log.e(TAG, "Error, onFailure");
            }
        });
    }


}
