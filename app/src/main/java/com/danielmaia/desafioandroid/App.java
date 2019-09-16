package com.danielmaia.desafioandroid;

import android.app.Application;

public class App extends Application {
    private static App instance;

    public App() {
        instance = this;
    }

    public synchronized static App getInstance() {
        if (instance == null)
            new App();

        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
