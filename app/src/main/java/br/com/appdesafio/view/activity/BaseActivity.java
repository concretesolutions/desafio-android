package br.com.appdesafio.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;


import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerAppCompatActivity;

/**
 * activity base, responsible for injecting the activity.
 */
public abstract class BaseActivity extends DaggerAppCompatActivity {

    @Inject
    public DispatchingAndroidInjector<Fragment> dispatchingFragmentInjector;


    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
    }


    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingFragmentInjector;
    }

}

