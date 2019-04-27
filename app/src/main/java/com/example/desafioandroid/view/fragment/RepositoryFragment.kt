package com.example.desafioandroid.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.core.view.isVisible
import androidx.databinding.adapters.ViewGroupBindingAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafioandroid.R
import com.example.desafioandroid.view.adapter.RepositoryAdapter
import com.example.desafioandroid.viewModel.fragment.RepositoryViewModel
import kotlinx.android.synthetic.main.fragment_repository.*

/**
 * A placeholder fragment containing a simple view.
 */
class RepositoryFragment : Fragment() {

    val TAG = javaClass.name
    private var adapter: RepositoryAdapter? = null

    private val repositoryViewModel: RepositoryViewModel by lazy {
        ViewModelProviders.of(this).get(RepositoryViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_repository, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListRepositoryView(recycler_repository)

        if (repositoryViewModel.listRepository != null) {
            adapter!!.submitList(repositoryViewModel.listRepository)
            recycler_repository.visibility = View.VISIBLE
            progress.visibility = View.GONE
        }else{
            repositoryViewModel.initializeViews()
            observeLiveData()
        }

    }

    private fun setupListRepositoryView(listPeople: RecyclerView) {
        adapter = RepositoryAdapter(activity!!.supportFragmentManager)
        recycler_repository.adapter = adapter
        listPeople.layoutManager = LinearLayoutManager(context)
    }


    private fun observeLiveData() {
        //observe live data emitted by view model
        repositoryViewModel.getRepositoryList().observe(this, Observer {
            adapter!!.submitList(it)

            recycler_repository.addOnChildAttachStateChangeListener(object : RecyclerView.OnChildAttachStateChangeListener{
                override fun onChildViewDetachedFromWindow(view: View) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onChildViewAttachedToWindow(view: View) {
                    if (!recycler_repository.isVisible) {
                        recycler_repository.visibility = View.VISIBLE
                        progress.visibility = View.GONE
                    }
                }

            })
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        repositoryViewModel.listRepository = adapter!!.currentList
    }
}
