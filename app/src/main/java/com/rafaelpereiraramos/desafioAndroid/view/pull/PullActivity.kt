package com.rafaelpereiraramos.desafioAndroid.view.pull

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders
import com.rafaelpereiraramos.desafioAndroid.R
import com.rafaelpereiraramos.desafioAndroid.core.ViewModelFactory
import com.rafaelpereiraramos.desafioAndroid.database.model.RepoTO

import kotlinx.android.synthetic.main.activity_pull.*
import javax.inject.Inject

class PullActivity : AppCompatActivity() {

    @Inject lateinit var viewModelFactory: ViewModelFactory

    lateinit var viewModel: PullViewModel

    private lateinit var repo: RepoTO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull)

        this.repo = intent.getParcelableExtra(REP)

        this.viewModel = ViewModelProviders.of(this, viewModelFactory).get(PullViewModel::class.java)

        setUI()
    }

    private fun setUI() {
        toolbar.title = "Pulls of @${repo.name}"
        toolbar.setTitleTextColor(Color.WHITE)

        setSupportActionBar(toolbar)
    }

    companion object {
        const val REP = "repo"
    }
}
