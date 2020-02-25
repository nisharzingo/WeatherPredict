package app.nishar.weatherpredict.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CurrentDateWeatherResponse implements Serializable {

    @SerializedName ( "request" )
    private CDWRRequest request;
    @SerializedName ( "location" )
    private CDWRLocation location;
    @SerializedName ( "current" )
    private CDWRCurrent current;
    public CDWRRequest getRequest ( ) {
        return request;
    }
    public CDWRLocation getLocation ( ) {
        return location;
    }
    public CDWRCurrent getCurrent ( ) {
        return current;
    }
}
