package app.nishar.weatherpredict.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FCCity implements Serializable {

    @SerializedName ( "id" )
    private int id;
    @SerializedName ( "name" )
    private String name;
    @SerializedName ( "coord" )
    private FRCoord coord;
    @SerializedName ( "country" )
    private String country;
    @SerializedName ( "population" )
    private int population;
    @SerializedName ( "timezone" )
    private int timezone;
    public int getId ( ) {
        return id;
    }
    public String getName ( ) {
        return name;
    }
    public FRCoord getCoord ( ) {
        return coord;
    }
    public String getCountry ( ) {
        return country;
    }
    public int getPopulation ( ) {
        return population;
    }
    public int getTimezone ( ) {
        return timezone;
    }
}
