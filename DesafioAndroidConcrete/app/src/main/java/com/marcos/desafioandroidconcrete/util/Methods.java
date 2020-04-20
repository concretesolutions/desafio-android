package com.marcos.desafioandroidconcrete.util;

import android.app.Activity;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ProgressBar;

import com.marcos.desafioandroidconcrete.R;
import com.marcos.desafioandroidconcrete.view.MainActivity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by marco on 08,Setembro,2019
 */
public abstract class Methods {
    private static String token = "";
    private static String code = "";

    public static final String TOKEN = "TOKEN";
    public static final String CODE = "CODE";

    static ProgressBar progressBar;


    public static void saveAccount(Activity activity, String token, String code) {
        SharedPreferences mPrefs = activity.getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putString(TOKEN, token);
        prefsEditor.putString(CODE, code);
        prefsEditor.apply();
        setToken(token);
    }

    public static String fetchToken(Activity activity) {
        if (token.contentEquals("")) {
            SharedPreferences mPrefs = activity.getPreferences(MODE_PRIVATE);
            SharedPreferences.Editor prefsEditor = mPrefs.edit();
            setToken(mPrefs.getString(TOKEN, ""));
        }
        return token;
    }

    public static void setToken(String token) {
        Methods.token = token;
    }


    public static String fetchCode(Activity activity) {
        if (code.contentEquals("")) {
            SharedPreferences mPrefs = activity.getPreferences(MODE_PRIVATE);
            SharedPreferences.Editor prefsEditor = mPrefs.edit();
            code = mPrefs.getString(CODE, "");
        }
        return code;
    }

    public static String formatDate(String created) {
        DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        Date d = null;
        try {
            d = f.parse(created);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return f.format(d);
    }

    public static void showProgress(MainActivity activity, boolean show) {
        if (progressBar == null)
            progressBar = activity.findViewById(R.id.progressBar);
        if (show) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

}