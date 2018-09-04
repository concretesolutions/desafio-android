package com.example.rafaelanastacioalves.moby.application;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;

import com.example.rafaelanastacioalves.moby.BuildConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.squareup.picasso.Picasso;

import timber.log.Timber;


public class MainApplication extends Application {
    @Override
    public void onCreate() {
        setupLog();
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        setupDb();
        super.onCreate();
    }


    private void setupDb() {
        FlowManager.init(this);
    }

    private void setupLog() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            Picasso.get()
                    .setIndicatorsEnabled(true);
            Picasso.get()
                    .setLoggingEnabled(true);
        } else {
            Timber.plant(new CrashReportingTree());
        }
    }

    /**
     * A tree which logs important information for crash reporting.
     */
    private static class CrashReportingTree extends Timber.Tree {
        @Override
        protected void log(int priority, String tag, String message, Throwable t) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return;
            }

            FakeCrashLibrary.log(priority, tag, message);

            if (t != null) {
                if (priority == Log.ERROR) {
                    FakeCrashLibrary.logError(t);
                } else if (priority == Log.WARN) {
                    FakeCrashLibrary.logWarning(t);
                }
            }
        }
    }
}
