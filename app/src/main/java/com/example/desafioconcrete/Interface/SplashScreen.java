package com.example.desafioconcrete.Interface;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;
import android.widget.Toast;

import com.example.desafioconcrete.Objects.GithubApiResponse;
import com.example.desafioconcrete.R;
import com.example.desafioconcrete.WebConnections.RetrofitClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SplashScreen extends AppCompatActivity {

    private Retrofit retrofit;
    private String baseURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        baseURL = "https://api.github.com/search/";

        configRetrofitClient();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                getJavaRepositories();
            }
        }, 3000);
    }

    private void configRetrofitClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private void getJavaRepositories(){
        RetrofitClient retrofitClient = retrofit.create(RetrofitClient.class);
        Call<GithubApiResponse> githubApiResponseCall = retrofitClient.getRepositoryValues("language:Java", "stars", "1");

        githubApiResponseCall.enqueue(new Callback<GithubApiResponse>() {
            @Override
            public void onResponse(Call<GithubApiResponse> call, Response<GithubApiResponse> response) {
                GithubApiResponse githubApiResponse = response.body();

                if (githubApiResponse != null){
                    saveDataLoaded(githubApiResponse);

                    Gson gson = new Gson();
                    String json = gson.toJson(githubApiResponse);

                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    intent.putExtra("repositoryData", json);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<GithubApiResponse> call, Throwable t) {
                GithubApiResponse githubApiResponse = getDataLoaded();

                if (githubApiResponse == null){
                    new AlertDialog.Builder(SplashScreen.this)
                            .setTitle(getResources().getString(R.string.connection_failed))
                            .setMessage(getResources().getString(R.string.internet_needed))
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                }
                            }).show();

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            SplashScreen.this.finish();
                            System.exit(0);
                        }
                    }, 3000);
                } else {
                    Gson gson = new Gson();
                    String json = gson.toJson(githubApiResponse);

                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    intent.putExtra("repositoryData", json);
                    startActivity(intent);
                }
            }
        });
    }

    private void saveDataLoaded(GithubApiResponse githubApiResponse){
        SharedPreferences sharedPreferences = getSharedPreferences("repositoryData", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = gson.toJson(githubApiResponse);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("data", json);
        editor.apply();
    }

    private GithubApiResponse getDataLoaded(){
        SharedPreferences sharedPreferences = getSharedPreferences("repositoryData", MODE_PRIVATE);
        Gson gson = new Gson();
        String data = sharedPreferences.getString("data", "");

        if (!data.isEmpty()){
            return gson.fromJson(data, new TypeToken<GithubApiResponse>(){}.getType());
        } else {
            return null;
        }
    }
}
