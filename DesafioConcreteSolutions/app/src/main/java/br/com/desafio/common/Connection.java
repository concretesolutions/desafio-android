package br.com.desafio.common;

import android.content.Context;
import android.net.ConnectivityManager;

public class Connection {
	public static boolean isOnline(Context context) { 
	    ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);	 
	    return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnectedOrConnecting();
	} 
}