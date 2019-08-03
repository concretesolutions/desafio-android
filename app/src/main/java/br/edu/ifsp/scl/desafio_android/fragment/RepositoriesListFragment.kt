package br.edu.ifsp.scl.desafio_android.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.edu.ifsp.scl.desafio_android.R
import br.edu.ifsp.scl.desafio_android.adapter.RepositoriesAdapter
import br.edu.ifsp.scl.desafio_android.api.RepositoriesService
import br.edu.ifsp.scl.desafio_android.api.RetrofitClient
import br.edu.ifsp.scl.desafio_android.model.Repositories
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

    // Layouts
    private var linearLayoutManager: LinearLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        rService = RetrofitClient().getClient().create(RepositoriesService::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        inflater.inflate(R.layout.fragment_repositories_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadFirstPage()
    }

    fun loadFirstPage(){
        rService?.searchRepositories("language:Java", "stars", 1)?.enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                pb_repository.visibility = View.GONE
                //swipe_refresh_layout.isRefreshing = false
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                repositories = Gson().fromJson((response.body()?.string()), Repositories::class.java)
                //produtos?.content?.forEach { produtoList.add(it!!) }
                //currentElement += produtos?.numberOfElements!!
                bind()
                //if (currentElement == produtos?.totalElements)
                //    isLastPage = true
            }
        })
    }

    fun bind() {
        linearLayoutManager = LinearLayoutManager(context)
        rv_repository.apply {
            layoutManager = linearLayoutManager
            adapter = RepositoriesAdapter(context!!, repositories!!) {

            }
        }
        pb_repository.visibility = View.GONE

    }
}
