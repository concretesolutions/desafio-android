package br.com.desafioandroid;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.desafioandroid.views.HomeActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Handler action = new Handler();

        action.postDelayed(new Runnable() {
            @Override
            public void run() {
                verificaLogin();
            }
        }, 3000);
    }

    void verificaLogin() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();



    }

}
