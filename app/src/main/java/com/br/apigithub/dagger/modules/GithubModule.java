package com.br.apigithub.dagger.modules;

import com.br.apigithub.impl.RetrofitServiceImpl;
import com.br.apigithub.interfaces.IGithubServiceProvider;
import com.br.apigithub.services.GithubServiceProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rlima on 30/05/18.
 */

@Module
public class GithubModule {

    @Provides
    public IGithubServiceProvider provideGithubService() {
        return new GithubServiceProvider(new RetrofitServiceImpl());
    }
}
