package br.fiap.android.trabalhofinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import br.fiap.android.trabalhofinal.dao.UsuarioDAO;
import br.fiap.android.trabalhofinal.model.Usuario;
import br.fiap.android.trabalhofinal.utils.SincronizaUsuario;

public class SplashScreen extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 5000;
    private final String KEY_LOGIN = "login";
    private ImageView ivLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new SincronizaUsuario(this).execute();

        ivLogo = (ImageView) findViewById(R.id.ivLogo);

        Animation anim = AnimationUtils.loadAnimation(this, R.anim.animacao_splashscreen);
        anim.reset();

        if (ivLogo != null) {
            ivLogo.clearAnimation();
            ivLogo.startAnimation(anim);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                if (isConectado()){
                    intent = new Intent(SplashScreen.this, MainActivity.class);
                }else{
                    intent = new Intent(SplashScreen.this, LoginActivity.class);
                }
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                SplashScreen.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    private boolean isConectado() {
        SharedPreferences shared = getSharedPreferences("info",MODE_PRIVATE);
        String login = shared.getString(KEY_LOGIN, "");
        if(login.equals(""))
            return false;
        else
            return true;
    }
}
