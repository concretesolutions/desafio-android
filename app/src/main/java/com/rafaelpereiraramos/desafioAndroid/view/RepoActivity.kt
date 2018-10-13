package com.rafaelpereiraramos.desafioAndroid.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.rafaelpereiraramos.desafioAndroid.R
import com.rafaelpereiraramos.desafioAndroid.core.ViewModelFactory
import kotlinx.android.synthetic.main.activity_repo.*
import javax.inject.Inject

/**
 * Created by Rafael P. Ramos on 12/10/2018.
 */
class RepoActivity : AppCompatActivity() {

    @Inject lateinit var viewModelFactory: ViewModelFactory

    var viewModel: RepoViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(RepoViewModel::class.java)

        setUI()
    }

    private fun setUI() {
        toolbar.setTitle(R.string.app_name)

        setSupportActionBar(toolbar)
    }
}
