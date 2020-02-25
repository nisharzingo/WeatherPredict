package app.nishar.weatherpredict.adapter;

import android.graphics.Color;
import android.graphics.Movie;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import app.nishar.weatherpredict.R;
import app.nishar.weatherpredict.model.DayForecast;

public class ForecastListAdapter extends RecyclerView.Adapter<ForecastListAdapter.MyViewHolder> {

    private ArrayList < DayForecast > dayForecastArrayList;

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView date, temp;
        ImageView weathe_icon;

        MyViewHolder ( View view ) {
            super(view);
            date =  view.findViewById( R.id.date);
            temp = view.findViewById(R.id.temp);
            weathe_icon = view.findViewById(R.id.weathe_icon);
        }
    }


    public ForecastListAdapter( ArrayList < DayForecast > dayForecastArrayList) {
        this.dayForecastArrayList = dayForecastArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.forecast_list_adapter, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder( @NonNull MyViewHolder holder, int position) {

        DayForecast dayForecast = dayForecastArrayList.get ( position );
        SimpleDateFormat dateFormat = new SimpleDateFormat ( "EEE, dd MMM", Locale.US );
        if(dayForecast!=null){

            holder.date.setText ( dateFormat.format ( new Date ( dayForecast.getDt ()*1000 ) ) );
            if(dayForecast.getWeather ()!=null&&dayForecast.getWeather ().size ()!=0){
                String image = "https://openweathermap.org/img/w/"+dayForecast.getWeather ().get ( 0 ).getIcon ()+".png";
                Picasso.get().load(image).error(R.drawable.app_logo).into(holder.weathe_icon);
            }

            if(dayForecast.getTemp()!=null){
                String min_tem = "/"+dayForecast.getTemp ().getMin ()+"°";
                String max_tem = dayForecast.getTemp ().getMax ()+"°";
                Spannable spannable = new SpannableString (max_tem+min_tem);
                spannable.setSpan(new ForegroundColorSpan ( Color.WHITE ), 0, max_tem.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannable.setSpan(new ForegroundColorSpan(Color.GRAY), max_tem.length(), max_tem.length()+min_tem.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                holder.temp.setText( spannable );
            }


        }

    }

    @Override
    public int getItemCount() {
        return dayForecastArrayList.size();
    }
    @Override
    public long getItemId ( int position ) {
        return super.getItemId ( position );
    }
    @Override
    public int getItemViewType ( int position ) {
        return super.getItemViewType ( position );
    }
}