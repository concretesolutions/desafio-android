package com.example.consultor.testacc.mvvm.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.consultor.testacc.R
import com.example.consultor.testacc.data.pojos.Repository
import com.example.consultor.testacc.mvvm.viewmodel.RepoDetailViewModel
import com.example.consultor.testacc.presentation.adapters.SimplePullAdapter
import kotlinx.android.synthetic.main.repo_detail_fragment.*
import kotlinx.android.synthetic.main.repo_detail_fragment.view.*
import kotlinx.android.synthetic.main.repository_list_fragment.*

class RepoDetailFragment : Fragment() {

    companion object {
        fun newInstance(repository: Repository): RepoDetailFragment {

            val fragment = RepoDetailFragment()
            val args = Bundle()
            args.putSerializable("repository", repository)
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var viewModel: RepoDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.repo_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


         if(savedInstanceState==null)
        viewModel.getPullRequests(repo!!)

        viewModel.myList.observe(this, Observer {
            rv_pulls.adapter = SimplePullAdapter(context!!, it!!)
            rv_pulls.adapter?.notifyDataSetChanged()
        })


        viewModel.issuesCount.observe(this, Observer {
             val openedStr=repo?.issuesOpen.toString()+" opened"
            val next = "<font color='#ffbb40'>$openedStr</font>"


            tv_issues.text=Html.fromHtml("$next / $it closed")

        })

    }

    private var repo: Repository? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            repo = arguments!!.getSerializable("repository") as? Repository
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_pulls.layoutManager = LinearLayoutManager(context)

        toolbar_detail.title = repo?.repoName
        toolbar_detail.navigationIcon = activity?.resources?.getDrawable(R.drawable.ic_arrow_back_black_24dp)
        toolbar_detail.setNavigationOnClickListener { activity?.onBackPressed() }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        viewModel = ViewModelProviders.of(this).get(RepoDetailViewModel::class.java)

    }


}
