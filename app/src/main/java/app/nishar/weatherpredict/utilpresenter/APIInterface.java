package app.nishar.weatherpredict.utilpresenter;

import app.nishar.weatherpredict.model.CurrentDateWeatherResponse;
import app.nishar.weatherpredict.model.ForecastResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface APIInterface {

    @GET ("current")
    Observable < CurrentDateWeatherResponse > getCurrentWeather( @Query ("access_key") String access_key,@Query ("query") String query);

    @GET ("forecast/daily")
    Observable < ForecastResponse > getForecastWeather( @Header  ( "x-rapidapi-host" ) String host,@Header ( "x-rapidapi-key" )String access_key,@Query ("q") String q, @Query ("cnt") int cnt,@Query ( "units" ) String units);
}
