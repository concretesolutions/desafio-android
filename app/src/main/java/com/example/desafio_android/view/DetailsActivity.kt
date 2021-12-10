package com.example.desafio_android.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.example.desafio_android.R
import com.example.desafio_android.data.model.Item
import com.example.desafio_android.data.model.repositoriesInfo
import com.example.desafio_android.data.pullmodel.RepositoryPullsItem
import com.example.desafio_android.view.adapter.PullListAdapter
import com.example.desafio_android.view.utils.action
import com.example.desafio_android.view.utils.snack
import com.example.desafio_android.viewmodel.AppResource
import com.example.desafio_android.viewmodel.DetailsViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.concrete_activity_details.*
import kotlinx.android.synthetic.main.concrete_activity_home.*
import kotlinx.android.synthetic.main.concrete_activity_home.container
import kotlinx.android.synthetic.main.concrete_activity_home.homeProgressBar
import kotlinx.android.synthetic.main.concrete_activity_home.recyclerview
import java.io.Serializable

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private val detailsViewModel : DetailsViewModel by viewModels()
    private val rvAdapter : PullListAdapter = PullListAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.concrete_activity_details)

        initRecycler()
        initData()

    }

    private fun initRecycler(){
        recyclerview.adapter = rvAdapter
    }

    private fun initData() {
        val  ownerName = intent.getSerializableExtra("ownerName") as String
        val  repoName = intent.getSerializableExtra("repoName") as String
        detailsViewModel.getAllRepositoryPulls(ownerName,repoName).observe(this,{onResponseP(it)})
    }
    private fun onResponseP(resource: AppResource<List<RepositoryPullsItem>?>){
        when (resource){
            is AppResource.Error -> showMessage(resource.message)
            is AppResource.Loading -> showLoading()
            is AppResource.Success -> showRepositories(resource.data)
        }
    }

    private fun showRepositories(data : List<RepositoryPullsItem>?){
        hideLoading()
        data.also {
            rvAdapter.submitList(it)
        }
    }

    private fun showLoading() {
        homeProgressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        homeProgressBar.visibility = View.GONE
    }

    private fun showMessage(message: String?) {
        hideLoading()
        message?.also {
            container.snack(it, Snackbar.LENGTH_INDEFINITE) {
                action(getString(R.string.app_ok)) {
                }
            }
        }
    }
}