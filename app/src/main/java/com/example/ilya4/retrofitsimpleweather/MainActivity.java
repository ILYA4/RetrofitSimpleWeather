package com.example.ilya4.retrofitsimpleweather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.ilya4.retrofitsimpleweather.R;
import com.example.ilya4.retrofitsimpleweather.network.APIInterface;
import com.example.ilya4.retrofitsimpleweather.network.ApiClient;
import com.example.ilya4.retrofitsimpleweather.pojo.WeatherPojo;
import com.example.ilya4.retrofitsimpleweather.utils.APIKey;
import com.example.ilya4.retrofitsimpleweather.utils.Formuls;
import com.example.ilya4.retrofitsimpleweather.utils.GPS;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    APIInterface apiInterface;

    TextView mainText;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainText = findViewById(R.id.main_text);

        apiInterface = ApiClient.getRestrofitInstance().create(APIInterface.class);
        String city = GPS.getCurrentCity();

        Call<WeatherPojo> pojoCall = apiInterface.getWeather(city, APIKey.API_KEY, App.getContext().getString(R.string.language));

        pojoCall.enqueue(new Callback<WeatherPojo>() {
            @Override
            public void onResponse(Call<WeatherPojo> call, Response<WeatherPojo> response) {
                Log.e(TAG, "inOnResponse");
                WeatherPojo data = response.body();
                Log.e(TAG, response.toString());
                String s = data.getName() + " " + Formuls.getTempCelsius(data.getMain().getTemp()) +"°С";
                mainText.setText(s);
            }

            @Override
            public void onFailure(Call<WeatherPojo> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }
}
