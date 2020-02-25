package app.nishar.weatherpredict.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CDWRRequest implements Serializable {

    @SerializedName ( "type" )
    private String type;
    @SerializedName ( "query" )
    private String query;
    @SerializedName ( "language" )
    private String language;
    @SerializedName ( "unit" )
    private String unit;
    public String getType ( ) {
        return type;
    }
    public String getQuery ( ) {
        return query;
    }
    public String getLanguage ( ) {
        return language;
    }
    public String getUnit ( ) {
        return unit;
    }
}
