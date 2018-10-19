package br.com.concrete.desafio_android.util;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import br.com.concrete.desafio_android.R;

public class Network {

    public boolean isConnected(Context context){
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()){
            return true;
        }else{
            Toast.makeText(context,
                    context.getResources().getString(R.string.verify_connection),
                    Toast.LENGTH_LONG).show();
            return false;
        }
    }
}
