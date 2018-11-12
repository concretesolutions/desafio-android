package com.jcjm.desafioandroid;


import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.jcjm.desafioandroid.di.AppComponent;
import com.jcjm.desafioandroid.di.DaggerAppComponent;
import com.jcjm.desafioandroid.di.UtilsModule;


public class MyApplication extends Application {
    AppComponent appComponent;
    Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        appComponent = DaggerAppComponent.builder().utilsModule(new UtilsModule()).build();
        Fresco.initialize(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }
}
