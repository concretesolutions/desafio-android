package matheusuehara.github.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.activity_repository.*
import matheusuehara.github.R
import matheusuehara.github.contract.RepositoryContract
import matheusuehara.github.model.Repository
import matheusuehara.github.presenter.RepositoryPresenterImpl
import matheusuehara.github.view.adapters.RepositoryAdapter
import matheusuehara.github.view.listeners.EndlessRecyclerViewScrollListener
import java.util.ArrayList

class RepositoryActivity : AppCompatActivity(), RepositoryContract.View {

    private var adapter: RepositoryAdapter = RepositoryAdapter(ArrayList(), this)
    private var presenter:RepositoryContract.Presenter = RepositoryPresenterImpl(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository)
        rvRepositories.adapter = adapter
        var linearLayoutManager = LinearLayoutManager(this)
        rvRepositories.layoutManager = linearLayoutManager
        rvRepositories.addItemDecoration(DividerItemDecoration(this, linearLayoutManager.orientation))

        presenter.getRepositories(0)

        rvRepositories.addOnScrollListener(object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                presenter.getRepositories(page)
            }
        })
    }

    override fun updateRepositories(repositoryResult: List<Repository>) {
        adapter.repositories.addAll(repositoryResult)
        adapter.notifyDataSetChanged()
    }

    override fun showProgressBar(){
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideProgressBar(){
        progress_bar.visibility = View.GONE
    }

    override fun showNetworkError(){
        Snackbar.make(
                frame_layout,
                R.string.connection_error,
                Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.try_again
                ) {presenter.getRepositories(0)}.show()
    }

    override fun showEmptyRepositoryMessage() {
        Snackbar.make(frame_layout,
                R.string.empty_result,
                Snackbar.LENGTH_INDEFINITE).show()
    }


}
