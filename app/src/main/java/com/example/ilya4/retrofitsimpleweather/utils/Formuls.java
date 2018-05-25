package com.example.ilya4.retrofitsimpleweather.utils;


import java.text.DecimalFormat;

public class Formuls {

    public static String getTempCelsius(double kelvin){
        return new DecimalFormat("##.##").format(kelvin - 273.15);
    }
}
