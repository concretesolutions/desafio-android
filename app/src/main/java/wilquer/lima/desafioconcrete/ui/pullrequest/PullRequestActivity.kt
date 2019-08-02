package wilquer.lima.desafioconcrete.ui.pullrequest

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.pullrequest_activity.*
import org.jetbrains.anko.toast
import wilquer.lima.desafioconcrete.R
import wilquer.lima.desafioconcrete.data.model.PullRequest
import wilquer.lima.desafioconcrete.util.Constants
import wilquer.lima.desafioconcrete.util.RecyclerClickListener
import wilquer.lima.desafioconcrete.util.adapter.RecyclerPullRequestAdapter

class PullRequestActivity : AppCompatActivity(), PullRequestContract.View, RecyclerClickListener {

    private val presenter = PullRequestPresenter(this)
    private var creator = ""
    private var repository = ""
    private val listPr = mutableListOf<PullRequest>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pullrequest_activity)

        window.navigationBarColor = Color.BLACK
        creator = intent.getStringExtra(Constants.CREATOR)
        repository = intent.getStringExtra(Constants.REPOSITORY_NAME)

        toolbarPr.title = repository
        setSupportActionBar(toolbarPr)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        recyclePr.apply {
            layoutManager = LinearLayoutManager(this@PullRequestActivity)
            adapter = RecyclerPullRequestAdapter(listPr, this@PullRequestActivity)
        }

        presenter.getPullRequests(creator, repository)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun setProgress(active: Boolean) {
        if (active) {
            progressPr?.visibility = View.VISIBLE
        } else {
            progressPr?.visibility = View.INVISIBLE
        }
    }

    override fun pullrequests(listPr: List<PullRequest>?) {
        this.listPr.addAll(listPr!!)
        recyclePr.adapter?.notifyDataSetChanged()
    }

    override fun error(msg: String) {
        toast(msg)
    }

    override fun onRecyclerClickListener(position: Int) {
        toast(position.toString())
    }
}