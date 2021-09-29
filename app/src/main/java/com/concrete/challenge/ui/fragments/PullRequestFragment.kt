package com.concrete.challenge.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.concrete.challenge.data.PullRequestEntity
import com.concrete.challenge.databinding.FragmentPullRequestBinding
import com.concrete.challenge.presentation.viewmodel.PullRequestViewModel
import com.concrete.challenge.ui.adapters.PullRequestAdapter
import com.concrete.challenge.utils.Constants.TAG

class PullRequestFragment : Fragment() {

    private val layoutManager by lazy { LinearLayoutManager(activity) }
    private val pullRequestViewModel by lazy {
        ViewModelProvider(this).get(PullRequestViewModel::class.java)
    }

    private lateinit var binding: FragmentPullRequestBinding

    private val rvPullRequest by lazy { binding.rvPullRequest }

    private val pullRequestsList = listOf(
        PullRequestEntity("Título pull request 1", "Body lorem ipsum dolor sit amet", "username", "User Name"),
        PullRequestEntity("Título pull request 2", "Body lorem ipsum dolor sit amet", "username", "User Name")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        parentFragmentManager.setFragmentResultListener("key", this,
            { _, result ->
                val open = result.getString("openMR")
                val closed = result.getString("closedMR")

                binding.txtOpenPullRequestAmount.text = open
                binding.txtClosedPullRequestAmount.text = closed
            }
        )

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPullRequestBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // cambiar por initView
        initRecyclerView()
        loadList()

        // agregar initObservables

        // guia: CardActivationListFragment | CardBlockListFragment
        /*
        with(pullRequestViewModel) {
            observe(viewLifecycleOwner, ::add)
        }
        */
    }

    private fun initRecyclerView() {
        rvPullRequest.layoutManager = layoutManager
        val adapter = PullRequestAdapter(pullRequestsList, PullRequestManager())
        rvPullRequest.adapter = adapter

    }

    private fun loadList() {
        pullRequestViewModel.pullRequestsList.observe(viewLifecycleOwner, ::add)
    }

    private fun add(pullRequestsList: List<String>) {
        Log.i(TAG, pullRequestsList.toString())
    }

    inner class PullRequestManager : PullRequestAdapter.AdapterManager {
        override fun onPullRequestClicked(pullRequestClicked: PullRequestEntity) {
            Toast.makeText(
                activity,
                "Abrir navegador con el pull request seleccionado",
                Toast.LENGTH_LONG
            ).show()
        }
    }

}