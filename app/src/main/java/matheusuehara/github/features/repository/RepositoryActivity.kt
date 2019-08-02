package matheusuehara.github.features.repository

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_repository.rvRepositories
import kotlinx.android.synthetic.main.activity_repository.root_layout
import matheusuehara.github.R
import matheusuehara.github.data.model.Repository
import matheusuehara.github.data.model.ViewStateModel
import matheusuehara.github.features.pullrequests.PullRequestActivity
import matheusuehara.github.util.Constants.INTENT_REPOSITORY_NAME
import matheusuehara.github.util.Constants.INTENT_REPOSITORY_OWNER_NAME
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepositoryActivity : AppCompatActivity(), RepositoryClickListener {

    private lateinit var layoutManager: LinearLayoutManager
    private var repositoryAdapter: RepositoryAdapter = RepositoryAdapter(ArrayList(), this)
    private val repositoryViewModel: RepositoryViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository)
        initRepositories()
        initObservable()
    }

    private fun initRepositories() {
        layoutManager = LinearLayoutManager(this)
        rvRepositories.layoutManager = layoutManager
        rvRepositories.adapter = repositoryAdapter
        rvRepositories.setHasFixedSize(true)
        rvRepositories.addItemDecoration(DividerItemDecoration(this, layoutManager.orientation))
    }

    private fun initObservable() {

        repositoryViewModel.getRepositories().observe(this, Observer { stateModel ->
            when (stateModel.status) {
                ViewStateModel.Status.LOADING -> {
                    repositoryAdapter.startLoading()
                }
                ViewStateModel.Status.SUCCESS -> {
                    repositoryAdapter.stopLoading()
                    stateModel.model?.items?.let { repositories ->
                        if (repositories.isEmpty()) showEmptyRepositoryMessage()
                        else {
                            repositoryAdapter.addRepositories(repositories)
                            rvRepositories.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                                    if (dy > 0) {
                                        val visibleItemCount = layoutManager.childCount
                                        val totalItemCount = layoutManager.itemCount
                                        val pastVisiblesItems = layoutManager.findFirstVisibleItemPosition()
                                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount && !repositoryAdapter.isLoading()) {
                                            repositoryViewModel.loadRepositories()
                                        }
                                    }
                                }
                            })
                        }
                    }
                }
                ViewStateModel.Status.ERROR -> {
                    repositoryAdapter.stopLoading()
                    showNetworkError()
                }
            }
        })
    }

    private fun showNetworkError() {
        Snackbar.make(
                root_layout,
                R.string.connection_error,
                Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.try_again
                ) { repositoryViewModel.loadRepositories() }.show()
    }

    private fun showEmptyRepositoryMessage() {
        Snackbar.make(root_layout,
                R.string.empty_result,
                Snackbar.LENGTH_INDEFINITE).show()
    }

    override fun onClick(repository: Repository) {
        try {
            val i = Intent(this, PullRequestActivity::class.java)
            i.putExtra(INTENT_REPOSITORY_NAME, repository.name)
            i.putExtra(INTENT_REPOSITORY_OWNER_NAME, repository.owner.login)
            startActivity(i)
        } catch (e: ArrayIndexOutOfBoundsException) {
            e.printStackTrace()
        }
    }


}
