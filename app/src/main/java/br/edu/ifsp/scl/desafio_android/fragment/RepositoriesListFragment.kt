package br.edu.ifsp.scl.desafio_android.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.edu.ifsp.scl.desafio_android.R
import br.edu.ifsp.scl.desafio_android.adapter.RepositoriesAdapter
import br.edu.ifsp.scl.desafio_android.api.RepositoriesService
import br.edu.ifsp.scl.desafio_android.api.RetrofitClient
import br.edu.ifsp.scl.desafio_android.model.Repositories
import br.edu.ifsp.scl.desafio_android.model.Repository
import br.edu.ifsp.scl.desafio_android.util.PaginationScrollListener
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_repositories_list.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoriesListFragment : Fragment() {
    // interfaces
    private var rService: RepositoriesService? = null
    // model
    private var repositories: Repositories? = null
    private var repositoryList: LinkedHashSet<Repository> = linkedSetOf()
    // Layouts
    private var linearLayoutManager: LinearLayoutManager? = null
    // Adapter
    private var adapter: RepositoriesAdapter? = null
    // Utils
    private var isLoading: Boolean = false
    private var isLastPage: Boolean = false
    private var currentElement: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        rService = RetrofitClient().getClient().create(RepositoriesService::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        inflater.inflate(R.layout.fragment_repositories_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Git repos"
        if (repositoryList.size > 0 ) { bind() }
        else { loadPage() }
        swipe_refresh_layout.setOnRefreshListener {
            isLoading = false
            isLastPage = false
            currentElement = 1
            repositoryList.clear()
            loadPage()
        }
    }

    fun loadPage(){
        rService?.searchRepositories("language:Java", "stars", currentElement)?.enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                pb_repository.visibility = View.GONE
                swipe_refresh_layout.isRefreshing = false
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                repositories = Gson().fromJson((response.body()?.string()), Repositories::class.java)
                repositories?.items?.forEach { repositoryList.add(it!!) }
                if(currentElement > 1) {
                    isLoading = false
                    pb_repository.visibility = View.GONE
                    adapter?.addAll(repositoryList)
                    rv_repository.adapter?.notifyDataSetChanged()
                } else { bind() }
                currentElement++
                if (currentElement == repositories?.total_count)
                    isLastPage = true
            }
        })
    }

    fun bind() {
        linearLayoutManager = LinearLayoutManager(context)
        rv_repository.apply {
            layoutManager = linearLayoutManager
            adapter = RepositoriesAdapter(context!!, repositoryList) {
                val fragment = PullListFragment()
                val bundle = Bundle()
                bundle.putString("login", repositoryList.elementAt(it).owner.login)
                bundle.putString("repo", repositoryList.elementAt(it).name)
                fragment.arguments = bundle
                fragmentManager?.beginTransaction()?.replace(R.id.container, fragment)?.addToBackStack(null)?.commit()
            }
            addOnScrollListener(object : PaginationScrollListener(linearLayoutManager!!) {
                override fun loadMoreItens() {
                    isLoading = true
                    loadPage()
                    pb_repository.visibility = View.VISIBLE
                }
                override fun isLastPage(): Boolean = isLastPage
                override fun isLoading(): Boolean = isLoading
            })
        }
        pb_repository.visibility = View.GONE
        swipe_refresh_layout.isRefreshing = false
    }
}
