package com.concrete.challenge.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.concrete.challenge.BuildConfig
import com.concrete.challenge.data.PullRequestEntity
import com.concrete.challenge.databinding.FragmentPullRequestBinding
import com.concrete.challenge.presentation.viewmodel.PullRequestViewModel
import com.concrete.challenge.ui.adapters.PullRequestAdapter
import com.concrete.challenge.utils.Constants.TAG
import org.koin.androidx.viewmodel.ext.android.viewModel

const val pullRequestNumber = "{/number}"

class PullRequestFragment : Fragment() {

    private val adapter by lazy { PullRequestAdapter(manager = PullRequestManager()) }
    private val layoutManager by lazy { LinearLayoutManager(requireContext()) }
    private val pullRequestViewModel: PullRequestViewModel by viewModel()

    private lateinit var binding: FragmentPullRequestBinding

    private val rvPullRequest by lazy { binding.rvPullRequest }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPullRequestBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        initRecyclerView()
        initObservables()
        loadInfo()
    }

    private fun initRecyclerView() {
        rvPullRequest.layoutManager = layoutManager
        rvPullRequest.adapter = adapter
    }

    private fun initObservables() {
        pullRequestViewModel.pullRequestsList.observe(viewLifecycleOwner, ::addPullRequests)
    }

    private fun loadInfo() {
        parentFragmentManager.setFragmentResultListener("key", this,
            { _, result ->
                val urlPullRequests = result.getString("url")

                val splitUrlPullRequests = urlPullRequests?.split("${BuildConfig.BASE_URL}repos/", pullRequestNumber, "/")

                val owner = splitUrlPullRequests?.get(1)
                val repo = splitUrlPullRequests?.get(2)

                    owner?.let { it1 -> repo?.let { pullRequestViewModel.getPullRequests(it1, it) } }

            }
        )
    }

    private fun addPullRequests(pullRequestsList: List<PullRequestEntity>) {

        pullRequestsList.let {
            adapter.addItems(pullRequestsList)
        }
    }

    inner class PullRequestManager : PullRequestAdapter.AdapterManager {
        override fun onPullRequestClicked(pullRequestClicked: PullRequestEntity) {

            val uri: Uri = Uri.parse(pullRequestClicked.pullRequestUrl)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)

        }
    }

}