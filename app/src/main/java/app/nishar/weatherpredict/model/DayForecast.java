package app.nishar.weatherpredict.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class DayForecast implements Serializable {

    @SerializedName ( "dt" )
    private long dt;
    @SerializedName ( "sunrise" )
    private long sunrise;
    @SerializedName ( "sunset" )
    private long sunset;
    @SerializedName ( "temp" )
    private Temp temp;
   /* @SerializedName ( "feels_like" )
    private long feels_like;*/
    @SerializedName ( "pressure" )
    private int pressure;
    @SerializedName ( "humidity" )
    private int humidity;
    @SerializedName ( "weather" )
    private ArrayList<Weather> weather;
    @SerializedName ( "speed" )
    private double speed;
    @SerializedName ( "deg" )
    private int deg;
    @SerializedName ( "clouds" )
    private int clouds;
    public long getDt ( ) {
        return dt;
    }
    public long getSunrise ( ) {
        return sunrise;
    }
    public long getSunset ( ) {
        return sunset;
    }
    public Temp getTemp ( ) {
        return temp;
    }
    public int getPressure ( ) {
        return pressure;
    }
    public int getHumidity ( ) {
        return humidity;
    }
    public ArrayList < Weather > getWeather ( ) {
        return weather;
    }
    public double getSpeed ( ) {
        return speed;
    }
    public int getDeg ( ) {
        return deg;
    }
    public int getClouds ( ) {
        return clouds;
    }
}
