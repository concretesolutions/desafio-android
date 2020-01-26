package br.com.victoramaral.githubdive.view.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

import br.com.victoramaral.githubdive.R;

public class SplashActivity extends AppCompatActivity {

    private Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                jump();
            }
        }, 2000);
    }

    private void jump() {
        timer.cancel();
        startActivity(new Intent(SplashActivity.this,
                LoginActivity.class));
        finish();
    }
}
