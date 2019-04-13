package com.dobler.desafio_android.ui.pull

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dobler.desafio_android.R
import com.dobler.desafio_android.ui.pull.adapter.PullRequestListAdapter
import kotlinx.android.synthetic.main.fragment_pull_request_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PullRequestFragment : Fragment() {

    private val viewModel: PullRequestViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            val safeArgs = PullRequestFragmentArgs.fromBundle(it)
            viewModel.user = safeArgs.user
            viewModel.repositoryName = safeArgs.repositoryName
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pull_request_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecycleView()
        viewModel.loadList()
    }


    fun setUpRecycleView() {

        var adapter = PullRequestListAdapter()

        rvPullRequestList.apply {
            this.adapter = adapter
            this.layoutManager = LinearLayoutManager(context)
        }

        viewModel.pullRequest.observe(this, androidx.lifecycle.Observer {
            adapter.addPullRequestsList(it)
        })
    }


}
