package app.nishar.weatherpredict.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import app.nishar.weatherpredict.R;
import app.nishar.weatherpredict.adapter.ForecastListAdapter;
import app.nishar.weatherpredict.custom.SeekArc;
import app.nishar.weatherpredict.custom.Windmill;
import app.nishar.weatherpredict.model.CDWRCurrent;
import app.nishar.weatherpredict.model.CDWRLocation;
import app.nishar.weatherpredict.model.CurrentDateWeatherResponse;
import app.nishar.weatherpredict.model.DayForecast;
import app.nishar.weatherpredict.model.ForecastResponse;
import app.nishar.weatherpredict.utilpresenter.APIClient;
import app.nishar.weatherpredict.utilpresenter.APIInterface;
import app.nishar.weatherpredict.utilpresenter.ConstantString;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class WeatherDisplayScreen extends AppCompatActivity {

    private ProgressBar loader;
    private TextView error_text,place,updated_time,weather_state,minimum_temperature,maximum_temperature,
            temp,humidity_progress_text,feels_like,uv_index,wind_direction,wind_speed,sunrise,sunset,pressure;
    private LinearLayout weather_layout;
    private ImageView weathe_icon;
    private SeekArc humidity_progress;
    Windmill windmill_big,windmill_small;
    RecyclerView forecast_list;

    SimpleDateFormat timeFormat = new SimpleDateFormat ( "hh:mm a", Locale.US );
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_weather_display_screen );

        loader = findViewById ( R.id.loader );
        error_text = findViewById ( R.id.error_text );
        weather_layout = findViewById ( R.id.weather_layout );
        place = findViewById ( R.id.place );
        updated_time = findViewById ( R.id.updated_time );
        minimum_temperature = findViewById ( R.id.minimum_temperature );
        maximum_temperature = findViewById ( R.id.maximum_temperature );
        weather_state = findViewById ( R.id.weather_state );
        weathe_icon = findViewById ( R.id.weathe_icon );
        temp = findViewById ( R.id.temp );
        humidity_progress = findViewById ( R.id.humidity_progress );
        humidity_progress_text = findViewById ( R.id.humidity_progress_text );
        feels_like = findViewById ( R.id.feels_like );
        uv_index = findViewById ( R.id.uv_index );
        windmill_big = findViewById ( R.id.windmill_big );
        windmill_small = findViewById ( R.id.windmill_small );
        wind_direction = findViewById ( R.id.wind_direction );
        wind_speed = findViewById ( R.id.wind_speed );
        sunrise = findViewById ( R.id.sunrise );
        sunset = findViewById ( R.id.sunset );
        pressure = findViewById ( R.id.pressure );
        forecast_list = findViewById ( R.id.forecast_list );
        forecast_list.setItemAnimator(new DefaultItemAnimator ());
        windmill_big.setWindSpeed ( 6 );
        windmill_small.setWindSpeed ( 6 );

        weather_layout.setVisibility ( View.GONE );
        loader.setVisibility ( View.VISIBLE );
        callcurrentWeather();
        callcurrentForecast();
    }

    @SuppressLint ("CheckResult")
    private void callcurrentWeather(){
        APIInterface apiInterface = APIClient.getClient ( ConstantString.apixu_url_current ).create ( APIInterface.class );


        Observable < CurrentDateWeatherResponse > responseObservable = apiInterface.getCurrentWeather (ConstantString.apixu_url_accesskey,ConstantString.apixu_url_city);
        //noinspection ResultOfMethodCallIgnored
        responseObservable.subscribeOn( Schedulers.io())
                .observeOn( AndroidSchedulers.mainThread())
                .map(result -> result)
                .subscribe(this::handleResultsCurrent, this::handleErrorCurrent);

    }

    @SuppressLint ("CheckResult")
    private void callcurrentForecast(){
        APIInterface apiInterface = APIClient.getClient ( ConstantString.open_weather_url_forecast ).create ( APIInterface.class );


        Observable < ForecastResponse > responseObservable = apiInterface.getForecastWeather (ConstantString.open_weather_host,ConstantString.open_weather_accesskey,ConstantString.open_weather_city,7,"metric");
        //noinspection ResultOfMethodCallIgnored
        responseObservable.subscribeOn( Schedulers.io())
                .observeOn( AndroidSchedulers.mainThread())
                .map(result -> result)
                .subscribe(this::handleResultsForecast, this::handleErrorForecast);

    }

    private void handleResultsCurrent(CurrentDateWeatherResponse response) {
        if (response != null ) {
            weather_layout.setVisibility ( View.VISIBLE );
            loader.setVisibility ( View.GONE );
            CDWRLocation cdwrLocation = response.getLocation ();

            if(cdwrLocation!=null){

                if(cdwrLocation.getLocaltime ()!=null&&!cdwrLocation.getLocaltime ().isEmpty ()){
                    String updatedTime = "Last updated:"+cdwrLocation.getLocaltime ();
                    updated_time.setText ( updatedTime);
                }

                if(cdwrLocation.getName ()!=null&&!cdwrLocation.getName ().isEmpty ()){
                    place.setText ( cdwrLocation.getName () );
                }
            }
            CDWRCurrent cdwrCurrent = response.getCurrent ();

            if(cdwrCurrent!=null){

                String temps = cdwrCurrent.getTemperature ()+"°C";
                temp.setText ( temps );
                if(cdwrCurrent.getWeather_descriptions ()!=null&&cdwrCurrent.getWeather_descriptions ().size ()!=0){
                    weather_state.setText ( cdwrCurrent.getWeather_descriptions ().get ( 0 ) );
                }

                if(cdwrCurrent.getWeather_icons ()!=null&&cdwrCurrent.getWeather_icons ().size ()!=0){

                    String image = cdwrCurrent.getWeather_icons ().get ( 0 );
                    if(image!=null&&!image.isEmpty()){
                        if(image.contains("http:")){
                            image = image.replace("http:","https:");
                        }
                    }
                   // Picasso.get().load(image).error(R.drawable.app_logo).into(weathe_icon);
                }

                int humidityValue = cdwrCurrent.getHumidity ();
                humidity_progress.setProgress ( humidityValue );
                String humidity_per = humidityValue+" %";
                humidity_progress_text.setText ( humidity_per );
                String feels_like_text = "Feels like  "+cdwrCurrent.getFeelslike ()+"°C";
                String uv_index_text = "UV index  "+cdwrCurrent.getUv_index ();
                feels_like.setText ( feels_like_text );
                uv_index.setText ( uv_index_text );
                pressure.setText ( String.valueOf ( cdwrCurrent.getPressure () ));

                int windSpeed = cdwrCurrent.getWind_speed ();
                windmill_big.setWindSpeed ( windSpeed );
                windmill_small.setWindSpeed ( windSpeed );
                String speedText = "Speed < "+windSpeed+" km/h";
                wind_speed.setText (  speedText);
                String direction = "Direction "+cdwrCurrent.getWind_dir ();
                wind_direction.setText ( direction );
            }


        } else {
            Toast.makeText(this, "NO RESULTS FOUND", Toast.LENGTH_LONG).show();
        }
    }

    private void handleErrorCurrent(Throwable t) {
        loader.setVisibility ( View.GONE );
        error_text.setVisibility ( View.VISIBLE );
        Toast.makeText(this, "ERROR IN FETCHING API RESPONSE. Try again"+t.getMessage (), Toast.LENGTH_LONG).show();
    }

    private void handleResultsForecast(ForecastResponse response) {

        if(response!=null){

            if(response.getList ()!=null&&response.getList ().size ()!=0){
                ArrayList< DayForecast > dayForecastArrayList = response.getList ();
                ForecastListAdapter adapter = new ForecastListAdapter ( dayForecastArrayList );
                forecast_list.setAdapter ( adapter );

                if(dayForecastArrayList.get ( 0 ).getTemp()!=null){
                    String min_tem = "/"+dayForecastArrayList.get ( 0 ).getTemp ().getMin ()+"°C";
                    String max_tem = dayForecastArrayList.get ( 0 ).getTemp ().getMax ()+"°C";
                    minimum_temperature.setText ( min_tem );
                    maximum_temperature.setText ( max_tem );
                }


                if(dayForecastArrayList.get ( 0 ).getWeather ()!=null&&dayForecastArrayList.get ( 0 ).getWeather ().size ()!=0){
                    String image = "https://openweathermap.org/img/w/"+dayForecastArrayList.get ( 0 ).getWeather ().get ( 0 ).getIcon ()+".png";
                    Picasso.get().load(image).error(R.drawable.app_logo).into(weathe_icon);
                }

                String sunrise_text = timeFormat.format ( new Date ( dayForecastArrayList.get ( 0 ).getSunrise ()*1000 ) );
                String sunset_text = timeFormat.format ( new Date ( dayForecastArrayList.get ( 0 ).getSunset ()*1000 ) );

                sunrise.setText ( sunrise_text );
                sunset.setText ( sunset_text );


            }
        }
    }

    private void handleErrorForecast(Throwable t) {

        Toast.makeText(this, "ERROR IN FETCHING API RESPONSE. Try again"+t.getMessage (), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        windmill_big.startAnimation();
        windmill_small.startAnimation();
    }

    @Override
    protected void onPause() {
        super.onPause();
        windmill_big.clearAnimation();
        windmill_small.clearAnimation();
    }
}
