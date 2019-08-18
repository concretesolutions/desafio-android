package br.com.desafioandroid.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;

import com.google.gson.Gson;

import java.util.List;

import br.com.desafioandroid.model.ListRepositories;
import br.com.desafioandroid.model.Repository;
import br.com.desafioandroid.views.HomeActivity;
import br.com.desafioandroid.wsconsumer.RetrofitConfig;

public class Preferencias {
    Context context;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    public Preferencias(Context context) {
        this.context = context;
    }

    public String getObjOffline() {
        pref = context.getSharedPreferences(RetrofitConfig.preferencias, context.MODE_PRIVATE);
        return pref.getString(RetrofitConfig.objOffline, "");
    }

    public void setObjOffline(ListRepositories repositories) {
        Gson gson = new Gson();
        pref = context.getSharedPreferences(RetrofitConfig.preferencias, context.MODE_PRIVATE);
        editor = pref.edit();
        editor.putString(RetrofitConfig.objOffline, gson.toJson(repositories));
        editor.commit();
    }

    public boolean verifyObje() {
        pref = context.getSharedPreferences(RetrofitConfig.preferencias, context.MODE_PRIVATE);
        if (pref.getString(RetrofitConfig.objOffline, "")!=null &&
                !pref.getString(RetrofitConfig.objOffline, "").isEmpty()) {
            return true;
        }

        return false;
    }
}
