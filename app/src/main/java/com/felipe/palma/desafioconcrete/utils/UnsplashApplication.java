package com.felipe.palma.desafioconcrete.utils;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Created by nmillward on 3/31/17.
 */

public class UnsplashApplication extends Application {

    private static UnsplashApplication instance;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        Log.d("ONCREATE", "deu certo");
    }

    public static UnsplashApplication getApplicationInstance() {
        if (instance == null) {
            instance = new UnsplashApplication();
        }

        Log.d("ONCREATE", instance.getCacheDir().toString());
        return instance;
    }

    public static boolean hasNetwork() {
        return instance.isNetworkAvailable();
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return (activeNetworkInfo != null) && activeNetworkInfo.isConnectedOrConnecting();
    }
}
