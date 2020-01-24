package com.felipemiranda.desafioconcrete;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;

import androidx.multidex.MultiDex;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.felipemiranda.desafioconcrete.network.InternetConnection;

/**
 * Created by felipemiranda
 */

public class Application extends android.app.Application implements InternetConnection {

    private static Application mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        Fresco.initialize(this);

        MultiDex.install(this);

        mInstance = this;
    }

    public static Application getInstance() {
        return mInstance;
    }

    @Override
    public boolean isOnline() {
        final ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null) {
            final Network network = connectivityManager.getActiveNetwork();

            if (network != null) {
                final NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(network);

                return (networkCapabilities != null && networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || networkCapabilities != null && networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI));
            }
        }

        return false;
    }
}
