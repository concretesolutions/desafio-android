package com.germanofilho.pullrequest.presentation.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.germanofilho.core.helper.observeResource
import com.germanofilho.core.ui.BaseFragment
import com.germanofilho.network.feature.pullrequestlist.model.entity.GitPullRequestResponse
import com.germanofilho.pullrequest.R
import com.germanofilho.pullrequest.di.PullRequestModule
import com.germanofilho.pullrequest.presentation.ui.adapter.PullRequestAdapter
import com.germanofilho.pullrequest.presentation.viewmodel.PullRequestViewModel
import kotlinx.android.synthetic.main.fragment_pull_request.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class PullRequestFragment : BaseFragment(R.layout.fragment_pull_request){

    private val args: PullRequestFragmentArgs by navArgs()

    private lateinit var user : String
    private lateinit var repo : String

    private val pullRequestAdapter by lazy { PullRequestAdapter(
        onItemClickListener = {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
           context?.startActivity(intent)
        })
    }

    private val viewModel by viewModel<PullRequestViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        PullRequestModule.inject()
        setupRecyclerView()
        setupObservers()
        setArgsVariable()
        setupToolbar()
        viewModel.getPullRequestList(user, repo)
    }

    private fun setupToolbar(){
        val toolbar = toolbarLayout as Toolbar
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        toolbar.title = repo
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupObservers() {
        viewModel.pullRequestList.observeResource(this,
            onSuccess = {
                loadData(it)
            },
            onError = {
                showError()
            },
            onLoading = {
                showLoading(it)
            }
        )
    }

    private fun loadData(pullRequestList: List<GitPullRequestResponse>) {
        if(pullRequestList.isNotEmpty()){
            rv_pull_request.visibility = View.VISIBLE
            pullRequestAdapter.addItem(pullRequestList)
        } else {
            empty_layout.visibility = View.VISIBLE
            rv_pull_request.visibility = View.GONE
        }

    }

    private fun showError(){
        error_layout.visibility = View.VISIBLE
        rv_pull_request.visibility = View.GONE

        showSnackBar(cl_content,
            com.germanofilho.core.R.string.erro_snack,
            com.germanofilho.core.R.string.try_again
        ) { viewModel.getPullRequestList(user, repo) }
    }

    private fun showLoading(isLoading: Boolean){
        if(isLoading){
            progress_bar.visibility = View.VISIBLE
            error_layout.visibility = View.GONE
        } else {
            progress_bar.visibility = View.GONE
        }
    }

    private fun setupRecyclerView() {
        with(rv_pull_request){
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = pullRequestAdapter
        }
    }

    private fun setArgsVariable(){
        val argsSplit = args.fullName.split("/")
        user = argsSplit.first()
        repo = argsSplit.last()
    }
}