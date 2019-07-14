package com.felipe.palma.desafioconcrete.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Felipe Palma on 14/07/2019.
 */
public class Utils {
    private Context context;

    public Utils(Context context) {
        this.context = context;
    }

    public boolean hasNetwork() {
        return isNetworkAvailable();
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return (activeNetworkInfo != null) && activeNetworkInfo.isConnectedOrConnecting();
    }
}
