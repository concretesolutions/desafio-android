package com.hotmail.fignunes.desafioconcretesolution.presentation.splash

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.hotmail.fignunes.desafioconcretesolution.R
import com.hotmail.fignunes.desafioconcretesolution.common.BaseActivity
import com.hotmail.fignunes.desafioconcretesolution.databinding.ActivitySplashBinding
import com.hotmail.fignunes.desafioconcretesolution.presentation.gitrepository.GitRepositoryActivity
import org.jetbrains.anko.startActivity
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class SplashActivity : BaseActivity(), SplashContract {

    private val presenter: SplashPresenter by inject{ parametersOf(this) }
    private lateinit var binding : ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        binding.presenter = presenter
        presenter.onCreate()
    }

    override fun displayMovies() {
        startActivity<GitRepositoryActivity>()
        finish()
    }
}