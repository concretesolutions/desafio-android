package com.example.desafioandroid.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desafioandroid.R
import com.example.desafioandroid.view.adapter.PullAdapter
import com.example.desafioandroid.viewModel.fragment.PullViewModel
import kotlinx.android.synthetic.main.fragment_pull.*

class PullFragment : Fragment() {
    private var adapter = PullAdapter()
    val TAG = javaClass.name
    private lateinit var pullViewModel : PullViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_pull, container, false)
        arguments!!.getString(getString(R.string.bundle_id_creator))
        arguments!!.getString(getString(R.string.bundle_name_repository))

        pullViewModel = ViewModelProviders.of(this).get(PullViewModel::class.java)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListPullView()
        observeLiveData()
        pullViewModel.initializeViews()
    }

    private fun setupListPullView() {
        recycler_pull.adapter = adapter
        recycler_pull.layoutManager = LinearLayoutManager(context)
    }

    private fun observeLiveData() {
        //observe live data emitted by view model
        pullViewModel.fetchPullList().observe(this, Observer {
            adapter.setPullList(it)
            pullViewModel.goneView()

        })

    }
}