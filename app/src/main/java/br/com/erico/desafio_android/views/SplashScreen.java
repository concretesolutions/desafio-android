package br.com.erico.desafio_android.views;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import br.com.erico.desafio_android.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                repositoryList();
            }
        }, 2000);
    }

    public void repositoryList() {
        Intent intent = new Intent(SplashScreen.this, Repositories.class);
        startActivity(intent);
        finish();
    }
}
