package app.nishar.weatherpredict.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CDWRLocation implements Serializable {

    @SerializedName ("name")
    private String name;
    @SerializedName ("country")
    private String country;
    @SerializedName ("region")
    private String region;
    @SerializedName ("lat")
    private String lat;
    @SerializedName ("lon")
    private String lon;
    @SerializedName ("timezone_id")
    private String timezone_id;
    @SerializedName ("localtime")
    private String localtime;
    @SerializedName ("localtime_epoch")
    private String localtime_epoch;
    @SerializedName ("utc_offset")
    private String utc_offset;
    public String getName ( ) {
        return name;
    }
    public String getCountry ( ) {
        return country;
    }
    public String getRegion ( ) {
        return region;
    }
    public String getLat ( ) {
        return lat;
    }
    public String getLon ( ) {
        return lon;
    }
    public String getTimezone_id ( ) {
        return timezone_id;
    }
    public String getLocaltime ( ) {
        return localtime;
    }
    public String getLocaltime_epoch ( ) {
        return localtime_epoch;
    }
    public String getUtc_offset ( ) {
        return utc_offset;
    }
}
