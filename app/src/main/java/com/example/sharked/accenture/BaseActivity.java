package com.example.sharked.accenture;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.sharked.accenture.webservices.SearchRepositories;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

@EActivity(R.layout.activity_main)
public class BaseActivity extends AppCompatActivity {
    public void exchangeVisibility(View toShow,View toHide){
        toShow.setVisibility(View.VISIBLE);
        toHide.setVisibility(View.GONE);
    }



    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

}
