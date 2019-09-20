package br.com.concrete.application;

import android.app.Application;
import br.com.concrete.mock.MockServer;

public class ConcreteApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        startMockServer();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public void startMockServer(){
        new Thread(new Runnable() {
            public void run() {
                new MockServer();
            }
        }).start();
    }
}