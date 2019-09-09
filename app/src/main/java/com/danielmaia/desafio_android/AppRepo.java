package com.danielmaia.desafio_android;

import com.orm.SugarApp;

public class AppRepo extends SugarApp {

    public static final String LOG_TAG = "APPREPO";

    private static AppRepo instance;

    public AppRepo() {
        instance = this;
    }

    public synchronized static AppRepo getInstance() {
        if (instance == null)
            new AppRepo();

        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
