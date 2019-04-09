package com.example.desafioandroid.view

import android.databinding.DataBindingUtil
import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.desafioandroid.R
import java.util.*
import com.example.desafioandroid.databinding.FragmentRepositoryBinding
import com.example.desafioandroid.viewModel.RepositoryViewModel

/**
 * A placeholder fragment containing a simple view.
 */
class RepositoryFragment : Fragment(), Observer {
    val TAG = javaClass.name

    lateinit var binding: FragmentRepositoryBinding
    lateinit var repositoryViewModel : RepositoryViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_repository, container, false)
        repositoryViewModel = RepositoryViewModel(this.activity)
        binding.fragmentRepositoryViewModel = repositoryViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListRepositoryView(binding.recyclerRepository)
        setupObserver(repositoryViewModel)
        repositoryViewModel.initializeViews()
    }

    private fun setupObserver(observable: Observable) {
        observable.addObserver(this)
    }

    private fun setupListRepositoryView(listPeople: RecyclerView) {
        val adapter = RepositoryAdapter(activity!!.supportFragmentManager)
        listPeople.adapter = adapter
        listPeople.layoutManager = LinearLayoutManager(context)
    }

    override fun update(observable: Observable, data: Any) {
        if (observable is RepositoryViewModel) {
            val peopleAdapter = binding.recyclerRepository.adapter as RepositoryAdapter
            peopleAdapter.setRepositoryList(observable.getPeopleList())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        repositoryViewModel.reset()
    }
}
