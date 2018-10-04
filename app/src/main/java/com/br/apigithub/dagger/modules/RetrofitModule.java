package com.br.apigithub.dagger.modules;

import com.br.apigithub.impl.RetrofitServiceImpl;
import com.br.apigithub.interfaces.RetrofitServiceContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rlima on 30/05/18.
 */

@Module
public class RetrofitModule {

    @Provides
    public RetrofitServiceContract provideRetrofitService() {
        return new RetrofitServiceImpl();
    }
}
