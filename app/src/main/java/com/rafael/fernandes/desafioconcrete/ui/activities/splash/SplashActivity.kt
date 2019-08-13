package com.rafael.fernandes.desafioconcrete.ui.activities.splash

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.rafael.fernandes.desafioconcrete.R
import com.rafael.fernandes.desafioconcrete.databinding.SplashActivityBinding
import com.rafael.fernandes.desafioconcrete.ui.activities.main.MainActivity
import com.rafael.fernandes.desafioconcrete.ui.base.BaseActivity

class SplashActivity : BaseActivity<SplashActivityBinding, SplashViewModel>() {

    override fun layoutId(): Int = R.layout.splash_activity

    override fun beforeSetContentView() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
    }

    override fun myOnCreate(savedInstanceState: Bundle?) {
        mViewModel.getLiveData().observe(this, Observer {
            gotoNextScreen(MainActivity::class.java)
        })
    }
}