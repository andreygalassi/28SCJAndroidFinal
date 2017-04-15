package br.fiap.android.trabalhofinal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import br.fiap.android.trabalhofinal.utils.DownloadDados;
import br.fiap.android.trabalhofinal.utils.NetworkUtils;

public class SplashScreen extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 5000;
    private ImageView ivLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ivLogo = (ImageView) findViewById(R.id.ivLogo);

        Animation anim = AnimationUtils.loadAnimation(this, R.anim.animacao_splashscreen);
        anim.reset();

        if (ivLogo != null) {
            ivLogo.clearAnimation();
            ivLogo.startAnimation(anim);
        }

        //NetworkUtils.getJSONFromAPI("http://www.mocky.io/v2/58b9b1740f0000b614f09d2f");
        new DownloadDados().execute();

//        URL url = new URL("http://www.mocky.io/v2/58b9b1740f0000b614f09d2f");
//        try {
//            HttpURLConnection con = (HttpURLConnection)url.openConnection();
//            con.
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                SplashScreen.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
