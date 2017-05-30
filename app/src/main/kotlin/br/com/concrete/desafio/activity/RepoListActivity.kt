package br.com.concrete.desafio.activity

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.Observer
import android.os.Bundle
import br.com.concrete.desafio.R
import br.com.concrete.desafio.initViewModel
import br.com.concrete.desafio.toast
import br.com.concrete.desafio.viewmodel.RepoListViewModel

class RepoListActivity : LifecycleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModel = initViewModel(RepoListViewModel::class.java)
        viewModel.searchRepos(0).observe(this, Observer { toast("${it?.totalCount}") })
    }

}