package com.bassul.mel.app.feature.repositoriesList.view


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bassul.mel.app.R
import com.bassul.mel.app.domain.Item
import com.bassul.mel.app.endpoint.GithubAPI
import com.bassul.mel.app.ext.AlertDialogUtils.Companion.createAndShowAlertDialog
import com.bassul.mel.app.feature.pullRequestsList.view.PullRequestActivity
import com.bassul.mel.app.feature.repositoriesList.RepoListContract
import com.bassul.mel.app.feature.repositoriesList.interactor.RepoInteractorImpl
import com.bassul.mel.app.feature.repositoriesList.presenter.RepoPresenterImpl
import com.bassul.mel.app.feature.repositoriesList.repository.RepoRepositoryImpl
import com.bassul.mel.app.feature.repositoriesList.view.adapter.RepositoryAdapter
import kotlinx.android.synthetic.main.activity_main.*

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
        initRepositoriesCard(pages)
    }

    private fun initRecyclerView() {
        repoRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = RepositoryAdapter(this, mutableListOf(), itemClickListener = itemOnClick)
        repoRecyclerView.adapter = adapter
        addOnScrollListener()
    }

    private fun addOnScrollListener() {
        repoRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                if (linearLayoutManager != null && isBottomOfList(linearLayoutManager)) {
                    initRepositoriesCard(++pages)
                }
            }
        })
    }

    private fun isBottomOfList(llm: LinearLayoutManager): Boolean {
        return llm.findLastCompletelyVisibleItemPosition() == adapter!!.items.size - 1
    }

    override fun initRepositoriesCard(pages: Int) {
        interactor.loadRepositories(pages)
    }

    override fun showCard(repositories: ArrayList<Item>) {
        hideLoading()
        adapter?.addItems(repositories)
    }

    override fun showErrorRepoList(errorMessage: Int) {
        hideLoading()
        createAndShowAlertDialog(errorMessage, this)
    }

    private val itemOnClick: (Item) -> Unit = { item ->
        openPullRequesActivity(item)
    }

    private fun openPullRequesActivity(item: Item) {
        val intent = Intent(this, PullRequestActivity::class.java)
        intent.putExtra("login", item.owner.login)
        intent.putExtra("nameRepository", item.name)
        startActivity(intent)
    }

    override fun onStop() {
        super.onStop()
        hideLoading()
    }

    private fun hideLoading() {
        repoProgressbar.visibility = View.GONE
        repoRecyclerView.visibility = View.VISIBLE
    }

    private fun showLoading() {
        repoProgressbar.visibility = View.VISIBLE
        repoRecyclerView.visibility = View.GONE
    }
}
