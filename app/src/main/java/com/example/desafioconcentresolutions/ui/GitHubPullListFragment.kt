package com.example.desafioconcentresolutions.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.desafioconcentresolutions.R
import com.example.desafioconcentresolutions.adapters.GitHubPullAdapter
import com.example.desafioconcentresolutions.models.Operation
import com.example.desafioconcentresolutions.viewmodels.MainViewModel
import com.google.android.material.internal.NavigationMenuItemView
import kotlinx.android.synthetic.main.fragment_git_hub_pull_list.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class GitHubPullListFragment : Fragment(), GitHubPullAdapter.GitHubPullListener {

    private var mListener: GitHubPullFragmentListener? = null

    private lateinit var mGitHubPullAdapter: GitHubPullAdapter

    private val mMainViewModel by sharedViewModel<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_git_hub_pull_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupObservable()
        setupListener()
    }

    private fun setupRecyclerView() {
        activity?.let {
            mGitHubPullAdapter = GitHubPullAdapter(it)
            recyclerView_gitHubPullFragment.adapter = mGitHubPullAdapter
            recyclerView_gitHubPullFragment.layoutManager = LinearLayoutManager(it)
            recyclerView_gitHubPullFragment.setHasFixedSize(true)

            mGitHubPullAdapter.setListener(this)
        }

    }

    private fun setupObservable() {
        mMainViewModel.getGitHubPullList().observe(viewLifecycleOwner, Observer {
            mGitHubPullAdapter.submitList(it)
        })

        mMainViewModel.getGitPullLoadingOperation().observe(viewLifecycleOwner, Observer {
            when(it){
                Operation.LOADING -> setLoadingState()
                Operation.SUCESS -> setDefaultState()
                else -> setLoadingState()
            }
        })
    }

    private fun setLoadingState(){
        container_gitHubPullListFragment_default.visibility = View.GONE
        container_gitHubPullListFragment_loading.visibility = View.VISIBLE
    }

    private fun setDefaultState(){
        container_gitHubPullListFragment_default.visibility = View.VISIBLE
        container_gitHubPullListFragment_loading.visibility = View.GONE
    }

    private fun setupListener() {
        toolBar_gitHubPullListFragment.setNavigationOnClickListener {
           activity?.onBackPressed()
        }
    }

    override fun onGitPullClicked(html_url: String) {
        val viewIntent = Intent(
            "android.intent.action.VIEW",
            Uri.parse(html_url)
        )
        startActivity(viewIntent)
    }

    fun setListener(listener: GitHubPullFragmentListener) {
        mListener = listener
    }

    interface GitHubPullFragmentListener {
        fun onGitPullClicked(html_url: String)
    }

}
