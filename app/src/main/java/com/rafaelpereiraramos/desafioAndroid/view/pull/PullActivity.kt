package com.rafaelpereiraramos.desafioAndroid.view.pull

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer
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

    private val adapter = PullPagedListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull)

        this.repo = if (intent == null) {
            savedInstanceState!!.getParcelable(REP)!!
        } else {
            intent.getParcelableExtra(REP)
        }

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PullViewModel::class.java)

        setUI()
        subscribe()

        viewModel.getPulls(repo)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState!!.putParcelable(REP, repo)
        super.onSaveInstanceState(outState)
    }

    private fun setUI() {
        toolbar.title = "Pulls of ${repo.name}"
        toolbar.setTitleTextColor(Color.WHITE)

        pull_list.adapter = adapter

        setSupportActionBar(toolbar)
    }

    private fun subscribe() {
        viewModel.pulls.observe(this, Observer {
            Log.d("Activity", "list: ${it?.size}")
            adapter.submitList(it)
        })

        viewModel.networkError.observe(this, Observer {
            Toast.makeText(this, "\uD83D\uDE28 Wooops ${it}", Toast.LENGTH_LONG).show()
        })
    }

    companion object {
        const val REP = "repo"
    }
}
