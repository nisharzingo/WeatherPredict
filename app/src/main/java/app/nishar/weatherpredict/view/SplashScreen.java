package app.nishar.weatherpredict.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import app.nishar.weatherpredict.R;

public class SplashScreen extends AppCompatActivity {

    private Animation animation;
    private ImageView logo;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_splash_screen );

        logo = findViewById(R.id.app_logo);

        if (savedInstanceState == null) {
            logoanimation();
        }

        new Handler ().postDelayed( new Runnable() {

            @Override
            public void run() {
                nextScren();
            }
        }, 3000);
    }

    private void logoanimation() {
        animation = AnimationUtils.loadAnimation(this, R.anim.logo_entry);
        logo.startAnimation(animation);

    }

    private void nextScren() {
        animation = AnimationUtils.loadAnimation(this, R.anim.logo_exit);
        logo.startAnimation(animation);



        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation arg0) {

                Intent dataScreen = new Intent(SplashScreen.this,WeatherDisplayScreen.class);
                startActivity(dataScreen);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationStart(Animation arg0) {
            }
        });

    }
}
