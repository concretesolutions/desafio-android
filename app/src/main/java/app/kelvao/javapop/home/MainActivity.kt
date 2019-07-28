package app.kelvao.javapop.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.kelvao.javapop.R
import app.kelvao.javapop.domain.network.response.RepositoryResponse
import app.kelvao.javapop.home.repositorieslist.RepositoriesDataSource
import app.kelvao.javapop.home.repositorieslist.RepositoryViewHolder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), HomeContract.IView {

    private val presenter: HomeContract.IPresenter = HomePresenter(this)
    private val dataSource = RepositoriesDataSource(onClickRepository = ::onRepositoryClick)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()
        presenter.fetchRepositories()
    }

    private fun setupRecyclerView() {
        repositoriesRecyclerView.apply {
            setHasFixedSize(true)
            adapter = dataSource
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
                    if (linearLayoutManager.itemCount <= linearLayoutManager.findLastVisibleItemPosition() + 2) {
                        presenter.fetchRepositories()
                    }
                }
            })
        }
    }

    private fun onRepositoryClick(repositoryViewHolder: RepositoryViewHolder) {

    }

    override fun showRepositoriesResult(repositories: List<RepositoryResponse>) {
        dataSource.setRepositories(repositories)
    }

    override fun showMoreRepositoriesResult(repositories: List<RepositoryResponse>) {
        dataSource.addRepositories(repositories)
    }

    override fun showLargeLoader() {
        largeLoader.visibility = View.VISIBLE
        largeLoader.alpha = 0f
        largeLoader.animate().apply {
            alpha(1f)
            duration = 400
            startDelay = 1000
            withEndAction { largeLoader.visibility = View.INVISIBLE }
        }.start()
    }

    override fun hideLargeLoader() {
        largeLoader.visibility = View.VISIBLE
        largeLoader.alpha = 1f
        largeLoader.animate().apply {
            alpha(0f)
            duration = 400
            startDelay = 1000
        }.start()
    }

    override fun showListLoader() {
        dataSource.isLoading = true
    }

    override fun hideListLoader() {
        dataSource.isLoading = false
    }

    override fun notifyFetchRepositoriesError() {
        Toast.makeText(this, getString(R.string.fetch_repositories_error), Toast.LENGTH_SHORT).show()

    }
}