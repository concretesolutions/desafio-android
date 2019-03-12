package com.hako.githubapi.features.pullRequest

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hako.githubapi.R
import com.hako.githubapi.data.retrofit.NetworkStatus
import com.hako.githubapi.data.retrofit.RemoteDatasource
import com.hako.githubapi.domain.entities.PullRequest
import com.hako.githubapi.domain.requests.QueryPullRequest
import com.hako.githubapi.features.pullRequest.PullListActivity.Companion.ARG_PULL_REQUEST_AUTHOR
import com.hako.githubapi.features.pullRequest.PullListActivity.Companion.ARG_PULL_REQUEST_REPO
import com.hako.githubapi.features.pullRequest.adapter.PullAdapter
import com.hako.githubapi.features.pullRequest.exceptions.ArgumentMissingException
import kotlinx.android.synthetic.main.pull_list_fragment.*
import kotlinx.android.synthetic.main.repo_list_fragment.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class PullListFragment : Fragment() {

    private val githubApi: RemoteDatasource by inject()
    private val viewModel: PullListViewModel by viewModel()

    companion object {
        fun newInstance(author: String?, repoName: String?): PullListFragment {
            val fragment = PullListFragment()
            val args = Bundle().apply {
                putString(ARG_PULL_REQUEST_AUTHOR, author)
                putString(ARG_PULL_REQUEST_REPO, repoName)
            }
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.pull_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // Pull arguments
        val author =
            arguments!!.getString(ARG_PULL_REQUEST_AUTHOR) ?: throw ArgumentMissingException(ARG_PULL_REQUEST_AUTHOR)
        val repoName =
            arguments!!.getString(ARG_PULL_REQUEST_REPO) ?: throw ArgumentMissingException(ARG_PULL_REQUEST_REPO)
        // Request PR data from remote repo
        githubApi.getPullsRequests(QueryPullRequest(author, repoName))
        // Set view data
        pull_item_repo_name.text = repoName
        pull_item_repo_author.text = author
        initPullAdapter()
        viewModel.loadPullRequests(QueryPullRequest(author, repoName))
        viewModel.networkStatus.observe(this, Observer {
            when (it) {
                NetworkStatus.Ready -> repo_refresh.isRefreshing = false
                NetworkStatus.Errored -> {
                    Toast.makeText(context, "Error cargando datos", Toast.LENGTH_LONG).show()
                    repo_refresh.isRefreshing = false
                }
            }
        })
    }

    private fun initPullAdapter() {
        val adapter = PullAdapter {
            itemClicked(it)
        }
        pull_recycler_list.layoutManager = LinearLayoutManager(context).apply {
            orientation = RecyclerView.VERTICAL
        }
        pull_recycler_list.setHasFixedSize(true)
        pull_recycler_list.adapter = adapter
        viewModel.pullRequests.observe(this, Observer {
            adapter.submitList(it)
        })
    }

    private fun itemClicked(pull: PullRequest) {
        openUrlBrowser(pull.url)
    }

    private fun openUrlBrowser(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }
}
