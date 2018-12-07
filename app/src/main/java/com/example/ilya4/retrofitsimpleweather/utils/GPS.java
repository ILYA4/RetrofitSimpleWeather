package com.example.ilya4.retrofitsimpleweather.utils;

import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;


import com.example.ilya4.retrofitsimpleweather.App;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.Locale;

public class GPS {
    private final static String TAG = ".GPS";
    private static String currentCity = "";

    public static void getLocation(FusedLocationProviderClient locationClient) throws SecurityException{

        locationClient.getLastLocation()
                .addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location !=null){
                            Log.v(TAG, location.getLatitude()+" " + location.getLongitude());
                            Geocoder geocoder = new Geocoder(App.getContext(), Locale.getDefault());
                            String city = null;
                            try {
                                city = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1)
                                        .get(0).getLocality();
                            }catch (IOException e) {e.printStackTrace();}
                            Log.v(TAG, "city: " +city);
                            currentCity = city;
                            Toast toast = Toast.makeText(App.getContext(), "Location success", Toast.LENGTH_SHORT);
                            toast.show();
                        }else Log.e(TAG, "Location is null");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, e.getLocalizedMessage());
                        Toast toast = Toast.makeText(App.getContext(), "Location Failure", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });

    }

    public static String getCurrentCity(){
        return currentCity;
    }
}
