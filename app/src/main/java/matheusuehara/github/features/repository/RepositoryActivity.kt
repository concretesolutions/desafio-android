package matheusuehara.github.features.repository

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.activity_repository.rvRepositories
import kotlinx.android.synthetic.main.activity_repository.progress_bar
import kotlinx.android.synthetic.main.activity_repository.frame_layout
import matheusuehara.github.R
import matheusuehara.github.contract.RepositoryContract
import matheusuehara.github.model.Repository
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

        presenter.getRepositories()

        rvRepositories.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    val visibleItemCount = linearLayoutManager.childCount
                    val totalItemCount = linearLayoutManager.itemCount
                    val pastVisiblesItems = linearLayoutManager.findFirstVisibleItemPosition()
                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount && progress_bar.visibility != View.VISIBLE ) {
                        presenter.getRepositories()
                    }
                }
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
                ) {presenter.getRepositories()}.show()
    }

    override fun showEmptyRepositoryMessage() {
        Snackbar.make(frame_layout,
                R.string.empty_result,
                Snackbar.LENGTH_INDEFINITE).show()
    }


}
