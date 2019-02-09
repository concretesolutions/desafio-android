package com.accenture.desafioandroid.presentation.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.accenture.desafioandroid.R;

public class WelcomeFragment extends Fragment
{
    public static WelcomeFragment newInstance()
    {
        return new WelcomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView( LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState )
    {
        View layout = inflater.inflate( R.layout.fragment_home, container );
        return layout;
    }
}