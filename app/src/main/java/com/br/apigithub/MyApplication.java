package com.br.apigithub;

import android.app.Application;

import com.br.apigithub.dagger.components.DaggerRetrofitComponent;
import com.br.apigithub.dagger.components.RetrofitComponent;
import com.br.apigithub.dagger.modules.GithubModule;
import com.br.apigithub.dagger.modules.RetrofitModule;

/**
 * Created by rlima on 04/10/18.
 */

public class MyApplication extends Application {
    private static RetrofitComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerRetrofitComponent
                .builder()
                .retrofitModule(new RetrofitModule())
                .githubModule(new GithubModule())
                .build();
    }

    public static RetrofitComponent getComponent() {
        return component;
    }
}
