package cl.carteaga.querygithub

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import cl.carteaga.querygithub.adapters.AdapterRepository
import cl.carteaga.querygithub.api.GitHubApi
import cl.carteaga.querygithub.api.GitHubEndPoints
import cl.carteaga.querygithub.models.HeadRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import cl.carteaga.querygithub.utils.EndlessRecyclerViewScrollListener



const val QUERY_JAVA = "language:Java"
const val QUERY_SORT = "stars"
const val QUERY_PAGE_START = 1

class RepositoryActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapterRepository: AdapterRepository
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var scrollListener: EndlessRecyclerViewScrollListener
    private var currentPage = QUERY_PAGE_START
    private var repositoryService: GitHubEndPoints? = null
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository)

        progressBar = findViewById(R.id.progressBar)
        recyclerView = findViewById(R.id.rcRepository)
        recyclerView.setHasFixedSize(true)
        viewManager = LinearLayoutManager(this)
        recyclerView.layoutManager = viewManager
        scrollListener = object : EndlessRecyclerViewScrollListener(viewManager as LinearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                currentPage++
                loadPage()
            }
        }
        recyclerView.addOnScrollListener(scrollListener)
        repositoryService = GitHubApi.getClient()?.create(GitHubEndPoints::class.java)
        adapterRepository = AdapterRepository(mutableListOf())
        recyclerView.adapter = adapterRepository
        loadPage()
    }

    private fun loadPage() {
        progressBar.visibility = View.VISIBLE
        callRepositoryService()?.enqueue(object: Callback<HeadRepository> {
            override fun onFailure(call: Call<HeadRepository>,t: Throwable) {
            }

            override fun onResponse(call: Call<HeadRepository>,
                                    response: Response<HeadRepository>) {
                if(response.code() == 200) {
                    adapterRepository.add(response.body()?.items)
                    progressBar.visibility = View.GONE
                }
            }
        })
    }

    private fun  callRepositoryService(): Call<HeadRepository>? {
        return repositoryService?.getRepositories(QUERY_JAVA, QUERY_SORT, currentPage)
    }
}
