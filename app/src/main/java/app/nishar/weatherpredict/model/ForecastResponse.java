package app.nishar.weatherpredict.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class ForecastResponse implements Serializable {

    @SerializedName ("city")
    private FCCity city;
    @SerializedName ("cod")
    private String cod;
    @SerializedName ("message")
    private double message;
    @SerializedName ("cnt")
    private int cnt;
    @SerializedName ("list")
    private ArrayList<DayForecast> list;
    public FCCity getCity ( ) {
        return city;
    }
    public String getCod ( ) {
        return cod;
    }
    public double getMessage ( ) {
        return message;
    }
    public int getCnt ( ) {
        return cnt;
    }
    public ArrayList < DayForecast > getList ( ) {
        return list;
    }
}
