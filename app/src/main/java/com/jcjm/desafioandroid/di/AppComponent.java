package com.jcjm.desafioandroid.di;


import com.jcjm.desafioandroid.MainActivity;
import com.jcjm.desafioandroid.activities.PullRequestsActivity;

import javax.inject.Singleton;

import dagger.Component;


@Component(modules = {UtilsModule.class})
@Singleton
public interface AppComponent {

    void doInjection(MainActivity mainActivity);

    void doInjection(PullRequestsActivity pullRequestsActivity);

}
