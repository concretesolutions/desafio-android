package com.bassul.mel.app.feature.repositoriesList.view


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bassul.mel.app.*
import com.bassul.mel.app.domain.Item
import com.bassul.mel.app.endpoint.GithubAPI
import com.bassul.mel.app.ext.AlertDialogUtils.Companion.createAndShowAlertDialog
import com.bassul.mel.app.feature.repositoriesList.interactor.RepoInteractorImpl
import com.bassul.mel.app.feature.pullRequestsList.view.PullRequestActivity
import com.bassul.mel.app.feature.repositoriesList.presenter.RepoPresenterImpl
import com.bassul.mel.app.feature.repositoriesList.RepoListContract
import com.bassul.mel.app.feature.repositoriesList.repository.RepoRepositoryImpl
import com.bassul.mel.app.feature.repositoriesList.view.adapter.RepositoryAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.collections.ArrayList

class RepoActivity : AppCompatActivity(), RepoListContract.View {

    private val presenter: RepoListContract.Presenter by lazy {
        RepoPresenterImpl(this)
    }

    private val repository: RepoListContract.Repository by lazy {
        RepoRepositoryImpl(GithubAPI())
    }

    private val interactor: RepoListContract.Interactor by lazy {
        RepoInteractorImpl(presenter, repository)
    }

    private var adapter: RepositoryAdapter? = null
    private var pages = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        initRepositoriesCard()
    }

    private fun initRecyclerView() {
        arRecyclerViewRepositories.layoutManager = LinearLayoutManager(this)
        adapter = RepositoryAdapter(this, mutableListOf(), itemClickListener = itemOnClick)
        arRecyclerViewRepositories.adapter = adapter
        arRecyclerViewRepositories.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                if (linearLayoutManager != null && isBottomOfList(linearLayoutManager)) {
                    interactor.loadRepositories(++pages)
                    //initRepositoriesCard(pages : Int) TODO
                }
            }
        })
    }

    fun isBottomOfList(llm: LinearLayoutManager): Boolean {
        return llm.findLastCompletelyVisibleItemPosition() == adapter!!.items.size - 1
    }

    override fun initRepositoriesCard() {
        //habilita o loading aqui
        interactor.loadRepositories(pages)
    }

    /*private fun initRepositoriesCard(pages : Int) {
        interactor.loadRepositories(pages)
    }*/

    override fun showCard(repositories: ArrayList<Item>) {
        setLoadingState(false)
        adapter?.let{
            it.addItems(repositories)

        }
    }

    override fun showErrorCard(errorMessage: Int) {
        createAndShowAlertDialog(errorMessage, this)
        setLoadingState(false)
    }

    val itemOnClick: (Item) -> Unit = { item ->
        openPullRequesActivity(item)
    }

    fun openPullRequesActivity(item: Item) {
        val intent = Intent(this, PullRequestActivity::class.java)
        intent.putExtra("login", item.owner.login)
        intent.putExtra("nameRepository", item.name)
        startActivity(intent)
    }

    //nao preciso mais
    override fun onStop() {
        super.onStop()
        setLoadingState(false)
    }

    //tirar o override se nao for usar no presenter
    override fun setLoadingState(isLoading : Boolean){
        if(isLoading){
            arProgressbar?.visibility = View.VISIBLE
            arRecyclerViewRepositories?.visibility = View.GONE
        }else{
            arProgressbar?.visibility = View.GONE
            arRecyclerViewRepositories?.visibility = View.VISIBLE
        }
    }
}
