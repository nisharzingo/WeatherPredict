package app.nishar.weatherpredict.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FRCoord implements Serializable {

    @SerializedName ( "lon" )
    private double lon;
    @SerializedName ( "lat" )
    private double lat;
    public double getLon ( ) {
        return lon;
    }
    public double getLat ( ) {
        return lat;
    }
}
