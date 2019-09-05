package com.example.desafio_android.view

import android.graphics.drawable.ClipDrawable.HORIZONTAL
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desafio_android.R
import com.example.desafio_android.adapter.AdapterListPullRequests
import com.example.desafio_android.api.GitHubAPIinterface
import com.example.desafio_android.api.GitHubAPIservice
import com.example.desafio_android.model.PullRequestModel
import com.example.desafio_android.model.RepositorieModel
import com.example.desafio_android.pojo.pullrequests.PullRequest
import kotlinx.android.synthetic.main.fragment_list_pull_requests.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoriePullRequestFragment: Fragment(){
    lateinit var mRepositorie: RepositorieModel
    var itensPullRequests = ArrayList<PullRequestModel>()
    lateinit var mAdapter: AdapterListPullRequests

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mRepositorie = arguments!!.getSerializable(REPOSITORIE) as RepositorieModel
        mAdapter = AdapterListPullRequests(itensPullRequests)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_list_pull_requests, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity!!.title = mRepositorie.name
        txtOpened.text = "0 opened"
        txtClosed.text = "/${mRepositorie.forksCount} closed"

        listPullRequests.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = mAdapter
        }
        listPullRequests.addItemDecoration(DividerItemDecoration(context, HORIZONTAL))

        getPullRequests()
    }

    private fun getPullRequests() {
        val apiInterface = GitHubAPIservice.getClient().create(GitHubAPIinterface::class.java)

        val callws = apiInterface.getPullRequests(mRepositorie.login!!, mRepositorie.name!!)

        callws.enqueue(object : Callback<List<PullRequest>> {
            override fun onResponse(call: Call<List<PullRequest>>?, response: Response<List<PullRequest>>?) {
                val resource = response?.body()
                if (resource != null) {
                    val itens = resource!!.flatMap { listOf(PullRequestModel(it.title, it.body, it.createdAt, it.user!!.login, it.user!!.avatarUrl))}
                    mAdapter.setItens(itens)
                    mAdapter.notifyDataSetChanged()
                    txtOpened.text = "${itens.size} opened"
                    txtClosed.text = "/${mRepositorie.forksCount!! - itens.size} closed"
                }
            }

            override fun onFailure(call: Call<List<PullRequest>>?, t: Throwable?) {
                //
            }

        })
    }

    companion object {

        private const val REPOSITORIE = "REPOSITORIE"

        fun newInstance(repositorie: RepositorieModel): RepositoriePullRequestFragment {
            val args = Bundle()
            args.putSerializable(REPOSITORIE, repositorie)
            val fragment = RepositoriePullRequestFragment()
            fragment.arguments = args
            return fragment
        }
    }
}