package app.kelvao.javapop.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.kelvao.javapop.R
import app.kelvao.javapop.databinding.ActivityMainBinding
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

        presenter.onViewReady()
    }

    private fun setupRecyclerView() {
        repositoriesRecyclerView.apply {
            setHasFixedSize(true)
            adapter = dataSource
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        }
    }

    private fun onRepositoryClick(repositoryViewHolder: RepositoryViewHolder) {

    }

    override fun showRepositoriesResult(repositories: List<RepositoryResponse>) {
        dataSource.setRepositories(repositories)
    }

    override fun notifyFetchRepositoriesSuccess() {

    }

    override fun notifyFetchRepositoriesError() {

    }
}