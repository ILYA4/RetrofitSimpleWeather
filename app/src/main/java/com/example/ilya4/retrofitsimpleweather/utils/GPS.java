package com.example.ilya4.retrofitsimpleweather.utils;

import android.location.Location;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.ilya4.retrofitsimpleweather.App;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class GPS {
    private final static String LOG = ".GPS";

    public static void getLocation() throws SecurityException{
        FusedLocationProviderClient locationClient = LocationServices.getFusedLocationProviderClient(App.getContext());

        locationClient.getLastLocation()
                .addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location !=null){
                            Log.v(LOG, location.getLatitude()+" ");
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(LOG, "Location is null");
                    }
                });

    }
}
