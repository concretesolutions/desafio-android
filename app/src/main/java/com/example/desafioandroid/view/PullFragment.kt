package com.example.desafioandroid.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.desafioandroid.R
import com.example.desafioandroid.databinding.FragmentPullBinding
import com.example.desafioandroid.viewModel.PullViewModel
import com.example.desafioandroid.viewModel.RepositoryViewModel
import java.util.*

class PullFragment : Fragment(), Observer {
    val TAG = javaClass.name

    private lateinit var binding: FragmentPullBinding
    private lateinit var pullViewModel : PullViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val creator = arguments!!.getString(getString(R.string.bundle_id_creator))
        val nameRepository = arguments!!.getString(getString(R.string.bundle_name_repository))

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pull, container, false)
        pullViewModel = PullViewModel(this.activity,creator,nameRepository)
        binding.fragmentPullViewModel = pullViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListRepositoryView(binding.recyclerPull)
        setupObserver(pullViewModel)
        pullViewModel.initializeViews()
    }

    private fun setupObserver(observable: Observable) {
        observable.addObserver(this)
    }

    private fun setupListRepositoryView(listPeople: RecyclerView) {
        val adapter = PullAdapter()
        listPeople.adapter = adapter
        listPeople.layoutManager = LinearLayoutManager(context)
    }

    override fun update(observable: Observable, data: Any) {
        if (observable is PullViewModel) {
            val pullAdapter = binding.recyclerPull.adapter as PullAdapter
            pullAdapter.setRepositoryList(observable.getPullList())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        pullViewModel.reset()
    }
}