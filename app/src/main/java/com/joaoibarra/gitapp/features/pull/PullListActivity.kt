package com.joaoibarra.gitapp.features.repository

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.joaoibarra.gitapp.R
import com.joaoibarra.gitapp.features.pull.PullViewModelFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_pull_list.*

class PullListActivity : AppCompatActivity() {

    private var recyclerState: Parcelable? = null

    private val viewModel: PullViewModel by lazy {
        val repo  = intent.getStringExtra("REPO")
        val user = intent.getStringExtra("USER")
        ViewModelProviders.of(this, PullViewModelFactory(user, repo)).get(PullViewModel::class.java)
    }

    private val adapter: PullAdapter by lazy {

        PullAdapter {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it?.htmlUrl)))
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_list)

        val actionBar = supportActionBar

        actionBar!!.title =  intent.getStringExtra("REPO")
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        val llm = LinearLayoutManager(this)
        recyclerPulls.layoutManager = llm
        recyclerPulls.adapter = adapter
        subscribeToList()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putParcelable("lmState", recyclerPulls.layoutManager?.onSaveInstanceState())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        recyclerState = savedInstanceState?.getParcelable("lmState")
    }

    private fun subscribeToList() {
        val disposable = viewModel.pulls
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { list ->
                    adapter.submitList(list)
                    if (recyclerState != null) {
                        recyclerPulls.layoutManager?.onRestoreInstanceState(recyclerState)
                        recyclerState = null
                    }
                },
                { e ->
                    Log.e("Error On Load", "Error", e)
                }
            )
    }

}