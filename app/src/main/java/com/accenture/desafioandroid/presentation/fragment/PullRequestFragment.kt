package com.accenture.desafioandroid.presentation.fragment

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.accenture.desafioandroid.R
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.net.Uri
import android.support.v7.util.DiffUtil
import com.accenture.desafioandroid.mvvm.model.PullRequest
import com.accenture.desafioandroid.mvvm.viewmodel.PullRequestViewModel
import com.accenture.desafioandroid.presentation.adapter.PullRequestAdapter
import com.accenture.desafioandroid.presentation.listerner.PullRsListener
import com.accenture.desafioandroid.utils.MySharedPreferences
import kotlinx.android.synthetic.main.fragment_pull_request.view.*


class PullRequestFragment : Fragment() {

    private lateinit var mView: View
    private lateinit var myPreference: MySharedPreferences

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(PullRequestViewModel::class.java)
    }

    companion object {

        fun newInstance(owner: String, repo: String): PullRequestFragment {

            val dialogFragment = PullRequestFragment()
            val bundle = Bundle()
            bundle.putString("owner", owner)
            bundle.putString("repo", repo)
            dialogFragment.arguments = bundle
            return dialogFragment
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val prsAdapter = PullRequestAdapter(context!!, diffCallback, pullRsListener)
        val owner = arguments!!.getString("owner")
        val repo = arguments!!.getString("repo")

        myPreference = MySharedPreferences(context)

        myPreference.setOwner(owner)
        myPreference.setRepo(repo)

        viewModel.itemPagedList.observe(this, Observer {
            prsAdapter.submitList(it!!)
        })
        mView.rv_pull_request.adapter = prsAdapter

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_pull_request, container, false)
        return mView
    }

    private var pullRsListener: PullRsListener = object :
        PullRsListener {
        override fun goDetails(url: String) {
            activity!!.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        }

    }

    private val diffCallback: DiffUtil.ItemCallback<PullRequest> = object : DiffUtil.ItemCallback<PullRequest>() {
        override fun areItemsTheSame(oldItem: PullRequest, newItem: PullRequest): Boolean {
            return oldItem.id === newItem.id
        }

        override fun areContentsTheSame(oldItem: PullRequest, newItem: PullRequest): Boolean {
            return oldItem == newItem
        }
    }

}
