package com.jcjm.desafioandroid.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class Constant {

    public final static String LOADING = "Loading";
    public final static String LOADED = "Loaded";
    public final static String CHECK_NETWORK_ERROR = "Verifique su conexion de internet";

    public static final String Q_PARAMETER = "language:Java";
    public static final String SORT_PARAMETER = "stars";
    public static final int ITEMS_PER_PAGE = 10;

    public static boolean checkInternetConnection(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            return false;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (NetworkInfo anInfo : info) {
                    if (anInfo.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public static ProgressDialog getProgressDialog(Context context, String msg) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(msg);
        progressDialog.setCancelable(false);
        return progressDialog;
    }

}
