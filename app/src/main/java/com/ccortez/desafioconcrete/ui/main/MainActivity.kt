package com.ccortez.desafioconcrete.ui.main

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ccortez.desafioconcrete.R
import com.ccortez.desafioconcrete.databinding.ActivityMainBinding
import com.ccortez.desafioconcrete.model.ItemEntity
import com.ccortez.desafioconcrete.ui.base.BaseActivity
import com.ccortez.desafioconcrete.ui.callback.RepositoryClickCallback
import com.ccortez.desafioconcrete.utils.viewModelOf

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    private val repositoryPagedListAdapter by lazy {
        RepositoryPagedListAdapter(repositoryClickCallback)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)

        mViewBinding.apply {
            repoList.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = repositoryPagedListAdapter
            }
        }
        mViewBinding!!.setIsLoading(true)
        mViewBinding.loadingRepositories.visibility = VISIBLE

        initObservers()

    }

    override fun getViewModel(): MainViewModel = viewModelOf<MainViewModel>(mViewModelProvider)

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    private fun initObservers() {
        mViewModel.repositoryList.observe(this, Observer {
            mViewBinding.progressBar.visibility = GONE
            mViewBinding.repoList.visibility = VISIBLE
            mViewBinding!!.setIsLoading(false)
            mViewBinding.loadingRepositories.visibility = GONE
            val list = it
            repositoryPagedListAdapter.submitList(list)
        })
    }

    private val repositoryClickCallback: RepositoryClickCallback = object : RepositoryClickCallback {
        override fun onClick(item: ItemEntity) {
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                show(item)
            }
        }
    }

    fun show(car: ItemEntity) {
        val carFragment = RepositoryFragment.forRepository(car.owner!!.login+"/"+car.name.toString())
        supportFragmentManager
            .beginTransaction()
            .addToBackStack("item")
            .replace(
                R.id.fragment_container,
                carFragment, RepositoryFragment.TAG
            ).commit()
    }

    fun showHome() {
/*        val carListFragment = RepositoryListFragment()
        supportFragmentManager
            .beginTransaction()
            .addToBackStack("repositorylist")
            .replace(
                R.id.fragment_container,
                carListFragment, RepositoryListFragment.TAG
            ).commit()*/
        supportFragmentManager.popBackStack()
    }

}