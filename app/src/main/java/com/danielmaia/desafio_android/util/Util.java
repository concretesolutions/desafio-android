package com.danielmaia.desafio_android.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.danielmaia.desafio_android.AppRepo;

public class Util {

    public static boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) AppRepo.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        boolean isAvailable = false;

        if (networkInfo != null && networkInfo.isConnected())
            isAvailable = true;

        return isAvailable;
    }
}
