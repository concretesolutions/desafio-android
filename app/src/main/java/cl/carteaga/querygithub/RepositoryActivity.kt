package cl.carteaga.querygithub

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import cl.carteaga.querygithub.classes.*
import cl.carteaga.querygithub.models.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import cl.carteaga.querygithub.classes.EndlessRecyclerViewScrollListener



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
        callRepositoryService()?.enqueue(object: Callback<Repository> {
            override fun onFailure(call: Call<Repository>,t: Throwable) {
            }

            override fun onResponse(call: Call<Repository>,
                                    response: Response<Repository>) {
                if(response.code() == 200) {
                    adapterRepository.add(response.body()?.items)
                    progressBar.visibility = View.GONE
                }
            }
        })
    }

    private fun  callRepositoryService(): Call<Repository>? {
        return repositoryService?.getRepositories(QUERY_JAVA, QUERY_SORT, currentPage)
    }
}
