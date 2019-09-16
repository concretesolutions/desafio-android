package com.danielmaia.desafioandroid.util;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.danielmaia.desafioandroid.App;

public class AppPreferences {

    private final String PREFERENCE_TOTAL_ITEMS = "preference_total_items";
    private static AppPreferences instance;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    private AppPreferences() {
        prefs = PreferenceManager.getDefaultSharedPreferences(App.getInstance());
        editor = prefs.edit();
    }

    public synchronized static AppPreferences getInstance() {
        if (instance == null)
            instance = new AppPreferences();

        return instance;
    }

    public long getTotalItems() {
        return prefs.getLong(PREFERENCE_TOTAL_ITEMS, 0);
    }

    public void setTotalItems(long size) {
        editor.putLong(PREFERENCE_TOTAL_ITEMS, size);
        editor.commit();
    }
}
