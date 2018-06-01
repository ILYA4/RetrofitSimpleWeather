package com.example.ilya4.retrofitsimpleweather.utils;

import android.util.Log;

import com.example.ilya4.retrofitsimpleweather.App;
import com.example.ilya4.retrofitsimpleweather.R;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Locale;


public class Formuls {

    public static final String TAG = "Formuls-deb";

    public static String getTempCelsius(double kelvin){
        return new DecimalFormat("##.##").format(kelvin - 273.15) + " °C";
    }

    public static String getTempCelsiusMin(double kelvin){
        return "T min: "+ getTempCelsius(kelvin);
    }

    public static String getTempCelsiusMax(double kelvin){
        return "T max: "+ getTempCelsius(kelvin);
    }

    public static String getTimeFromMs(long seconds){
        Date date = new Date(seconds*1000L);
        DateFormat df = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
        Log.v(TAG, date.toString());
        return df.format(date);
    }

    public static String getPressureInMmHg(double hPa){
        double mmHg = hPa/1.33322;
        String result = new DecimalFormat("##").format(mmHg) + " "
                + App.getContext().getResources().getString(R.string.mmHg);
        return result;
    }

    public static String getHumidity(double humidity){
        return (int) humidity +"%";
    }

    public static String getWind(double speed, double deg){
        String directionOfWind = "";
        if (deg >= 337.6 || deg<= 22.5) directionOfWind = App.getContext().getResources().getStringArray(R.array.direction_of_wind)[0];
        else if (22.6 <= deg && deg <= 67.5) directionOfWind = App.getContext().getResources().getStringArray(R.array.direction_of_wind)[1];
        else if (67.6 <= deg && deg <= 112.5) directionOfWind = App.getContext().getResources().getStringArray(R.array.direction_of_wind)[2];
        else if (112.6 <= deg && deg <= 157.5) directionOfWind = App.getContext().getResources().getStringArray(R.array.direction_of_wind)[3];
        else if (157.6 <= deg && deg <202.5) directionOfWind = App.getContext().getResources().getStringArray(R.array.direction_of_wind)[4];
        else if (202.6 <= deg && deg <247.5) directionOfWind = App.getContext().getResources().getStringArray(R.array.direction_of_wind)[5];
        else if (247.6 <= deg && deg <292.5) directionOfWind = App.getContext().getResources().getStringArray(R.array.direction_of_wind)[6];
        else if (292.6 <= deg && deg <337.5) directionOfWind = App.getContext().getResources().getStringArray(R.array.direction_of_wind)[7];
        String result = (int) speed + App.getContext().getResources().getString(R.string.speed_of_wind) + ", "
                + directionOfWind;
        return result;
    }

}
