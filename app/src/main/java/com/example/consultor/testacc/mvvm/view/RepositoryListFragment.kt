package com.example.consultor.testacc.mvvm.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.util.DiffUtil
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.consultor.testacc.R
import com.example.consultor.testacc.data.pojos.Repository
import com.example.consultor.testacc.mvvm.viewmodel.RepositoryListViewModel
import com.example.consultor.testacc.presentation.adapters.RepositoriesAdapter
import kotlinx.android.synthetic.main.repository_list_fragment.*

class RepositoryListFragment : Fragment() {

    companion object {
        fun newInstance() = RepositoryListFragment()
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        viewModel = ViewModelProviders.of(this).get(RepositoryListViewModel::class.java)

    }
    private lateinit var viewModel: RepositoryListViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.repository_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rv_repositories.layoutManager = LinearLayoutManager(context)
        rv_repositories.setHasFixedSize(true)
        val adapter = RepositoriesAdapter(context!!, diffCallback)
        viewModel.itemPagedList.observe(this, Observer {
            adapter.submitList(it)
                tv_no_results.visibility = View.GONE
                rv_repositories.visibility=View.VISIBLE

        })
        rv_repositories.adapter = adapter


    }

    val diffCallback: DiffUtil.ItemCallback<Repository> = object : DiffUtil.ItemCallback<Repository>() {
        override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean {
            return oldItem.id === newItem.id
        }

        override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean {
            return oldItem.equals(newItem)
        }
    }


}
