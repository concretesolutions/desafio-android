package com.accenture.desafioandroid.presentation.fragment

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.util.DiffUtil
import com.accenture.desafioandroid.R
import com.accenture.desafioandroid.mvvm.model.Item
import com.accenture.desafioandroid.mvvm.viewmodel.HomeViewModel
import com.accenture.desafioandroid.presentation.activities.PullRequestActivity
import com.accenture.desafioandroid.presentation.adapter.HomeAdapter
import com.accenture.desafioandroid.presentation.listerner.HomeListener
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {
    private lateinit var mView: View

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(HomeViewModel::class.java)

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("LIST", mView.rv_repository.layoutManager.onSaveInstanceState())
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState != null) {
        } else {

            val prsAdapter = HomeAdapter(context!!, diffCallback, homeListener)
            viewModel.itemPagedList.observe(this, Observer {
                prsAdapter.submitList(it!!)
            })
            mView.rv_repository.adapter = prsAdapter
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_home, container, false)

        return mView
    }

    private var homeListener: HomeListener = object :
        HomeListener {
        override fun goDetails(owner: String, repo: String) {
            startActivity(
                Intent(context, PullRequestActivity::class.java).putExtra("owner", owner).putExtra(
                    "repo",
                    repo
                )
            )
        }
    }

    private val diffCallback: DiffUtil.ItemCallback<Item> = object : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id === newItem.id
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }

}
