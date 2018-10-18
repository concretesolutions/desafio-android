package matheusuehara.github.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import kotlinx.android.synthetic.main.activity_repository.*
import matheusuehara.github.R
import matheusuehara.github.api.GitHubApi
import matheusuehara.github.model.Repository
import matheusuehara.github.model.RepositoryResponse
import matheusuehara.github.view.adapters.RepositoryAdapter
import matheusuehara.github.view.listeners.EndlessRecyclerViewScrollListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.ArrayList

class RepositoryActivity : AppCompatActivity() {

    private var allRepositories = ArrayList<Repository>()
    private var adapter: RepositoryAdapter? = null
    private var pagina = 1

    companion object {
        private val language = "language:Java"
        private val sort = "stars"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository)

        this.getRepositories(pagina)

        adapter = RepositoryAdapter(allRepositories, this)

        rvRepositories.adapter = adapter

        var linearLayoutManager = LinearLayoutManager(this)

        rvRepositories.layoutManager = linearLayoutManager

        val mDividerItemDecoration = DividerItemDecoration(this, linearLayoutManager.orientation
        )
        rvRepositories.addItemDecoration(mDividerItemDecoration)

        val scrollListener: EndlessRecyclerViewScrollListener = object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                pagina++
                getRepositories(pagina)
            }
        }

        rvRepositories.addOnScrollListener(scrollListener)

    }

    fun getRepositories(pagina: Int) {

        val retrofit = Retrofit.Builder()
                .baseUrl(getString(R.string.URL_BASE))
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val service = retrofit.create<GitHubApi>(GitHubApi::class.java)

        val callRepositories = service.getRepositories(language, sort, pagina)

        callRepositories.enqueue(object : Callback<RepositoryResponse> {
            override fun onResponse(call: Call<RepositoryResponse>, response: Response<RepositoryResponse>) {
                if (response.code() == 200) {

                    val repositoryResponse = response.body()

                    val moreRepositories = repositoryResponse!!.items

                    val curSize = adapter?.getItemCount()

                    if (moreRepositories != null) {
                        allRepositories.addAll(moreRepositories)
                    }

                    if (curSize != null) {
                        adapter?.notifyItemRangeInserted(curSize, allRepositories.size - 1)
                    }

                } else {
                    Log.d("FALHA NA REQUISIÇÃO ", "CÓDIGO:" + response.code())
                    Log.d(" URL ", call.request().url().toString())
                }
            }

            override fun onFailure(call: Call<RepositoryResponse>, t: Throwable) {
                Log.d("FALHA NA REQUISIÇÃO ", "SEM CONEXAO COM A INTERNET")
            }
        })
    }

}
