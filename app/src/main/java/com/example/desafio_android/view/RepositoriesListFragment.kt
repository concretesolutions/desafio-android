package com.example.desafio_android.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio_android.R
import com.example.desafio_android.adapter.AdapterListRepositories
import com.example.desafio_android.api.GitHubAPIinterface
import com.example.desafio_android.api.GitHubAPIservice
import com.example.desafio_android.listeners.RepositoriesScrollListener
import com.example.desafio_android.model.RepositorieModel
import com.example.desafio_android.pojo.repositories.Repositories
import kotlinx.android.synthetic.main.fragment_list_repositories.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RepositoriesListFragment: Fragment(){
    var repositories: Repositories? = null
    var itensRepositories = ArrayList<RepositorieModel>()
    lateinit var mAdapter: AdapterListRepositories
    val linearLayoutManager = LinearLayoutManager(activity)
    private lateinit var listener: OnRepositorieSelected

    interface OnItemClickListener {
        fun onItemClicked(position: Int, view: View)
    }

    private fun RecyclerView.addOnItemClickListener(onClickListener: OnItemClickListener) {
        this.addOnChildAttachStateChangeListener(object: RecyclerView.OnChildAttachStateChangeListener {
            override fun onChildViewDetachedFromWindow(view: View) {
                view.setOnClickListener(null)
            }

            override fun onChildViewAttachedToWindow(view: View) {
                view.setOnClickListener {
                    val holder = getChildViewHolder(view)
                    onClickListener.onItemClicked(holder.adapterPosition, view)
                }
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAdapter = AdapterListRepositories(itensRepositories)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_list_repositories, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity!!.title = getString(R.string.app_name)

        listRepositories.apply {
            layoutManager = linearLayoutManager
            adapter = mAdapter
        }

        listRepositories.addOnScrollListener(object : RepositoriesScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int) {
                getRepositories(page)
            }
        })

        listRepositories.addOnItemClickListener(object: OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                val repositorie = mAdapter.getItem(position)
                listener.OnRepositorieSelected(repositorie)
            }
        })

        getRepositories(1)
    }

    private fun getRepositories(page: Int) {
        val apiInterface = GitHubAPIservice.getClient().create(GitHubAPIinterface::class.java)

        val callws = apiInterface.getRepositories(page)

        callws.enqueue(object : Callback<Repositories> {
            override fun onResponse(call: Call<Repositories>?, response: Response<Repositories>?) {
                val resource = response?.body()
                if (resource != null) {
                    repositories = resource
                    val itens = repositories!!.items!!.flatMap {
                        listOf(RepositorieModel(
                            it.name,
                            it.description,
                            it.fullName,
                            it.owner!!.login,
                            it.forksCount,
                            it.stargazersCount,
                            it.owner!!.avatarUrl)
                        )
                    }
                    mAdapter.setItens(itens)
                    mAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<Repositories>?, t: Throwable?) {
                //
            }

        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is OnRepositorieSelected) {
            listener = context
        } else {
            throw ClassCastException("$context must implement OnRepositorieSelected.")
        }
    }

    interface OnRepositorieSelected {
        fun OnRepositorieSelected(repositorie: RepositorieModel)
    }

    companion object {
        fun newInstance(): RepositoriesListFragment = RepositoriesListFragment()
    }
}