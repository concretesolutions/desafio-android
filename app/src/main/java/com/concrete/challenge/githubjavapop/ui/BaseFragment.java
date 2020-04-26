package com.concrete.challenge.githubjavapop.ui;

import androidx.fragment.app.Fragment;
import androidx.test.espresso.IdlingResource;

public class BaseFragment extends Fragment implements IdlingResource {

    protected boolean isIdle;
    protected ResourceCallback resourceCallback;

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public boolean isIdleNow() {
        if(isIdle) resourceCallback.onTransitionToIdle();
        return isIdle;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        resourceCallback = callback;
    }
}
