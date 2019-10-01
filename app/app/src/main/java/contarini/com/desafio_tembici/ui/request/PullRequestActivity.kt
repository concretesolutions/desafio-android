package contarini.com.desafio_tembici.ui.request

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import contarini.com.br.ui.base.BaseActivity
import contarini.com.desafio_tembici.Constants
import contarini.com.desafio_tembici.R
import contarini.com.desafio_tembici.data.extensions.setup
import contarini.com.desafio_tembici.data.models.PullRequestResponse
import contarini.com.desafio_tembici.data.models.RepositoriesResponse
import kotlinx.android.synthetic.main.activity_pull_request.*
import org.jetbrains.anko.intentFor

class PullRequestActivity : BaseActivity(), PullRequestContract.View {

    private val mPresenter : PullRequestContract.Presenter by lazy {
        val presenter = PullRequestPresenter()
        presenter.attachView(this)
        presenter
    }

    lateinit var layoutManager : LinearLayoutManager
    private lateinit var repositoriesResponse : RepositoriesResponse

    private val mAdapter by lazy {
        val adapter = PullRequestAdapter(context, object : PullRequestAdapter.OnItemClickListener{
            override fun onItemClicked(item: PullRequestResponse) {
            }
        })
        layoutManager = LinearLayoutManager(context)
        rvPullRequest.setup(adapter, layoutManager = layoutManager)
        adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_request)
        setup()
    }

    private fun setup(){
        repositoriesResponse = intent.getSerializableExtra(Constants.REPOSITORY) as RepositoriesResponse

        mPresenter.getPullRequest(repositoriesResponse.owner.login, repositoriesResponse.name)

        setToolbar(repositoriesResponse.name, true)
    }

    override fun displayError(msg: String?) {
        Snackbar.make(contentPull, msg!!, Snackbar.LENGTH_LONG)
            .setAction(getString(R.string.error)) { mPresenter.getPullRequest(repositoriesResponse.owner.login, repositoriesResponse.name) }
            .show()
    }

    override fun displayPullRequests(requests : List<PullRequestResponse>, open : Int, closed : Int) {
        if(requests.isNotEmpty()){
            mAdapter.setRepositories(requests)
            mAdapter.notifyDataSetChanged()

            tvOpened.text = open.toString()
            tvClosed.text = closed.toString()
        } else {
            displayError(getString(R.string.pull_request_error))
        }
    }

    override fun showLoading(loading: Boolean) {
        if(loading){
            pbPullRequest.visibility = View.VISIBLE
        } else {
            pbPullRequest.visibility = View.GONE
        }
    }

}

fun Context.createPullRequestIntent(item : RepositoriesResponse) = intentFor<PullRequestActivity>(
    Constants.REPOSITORY to item
)