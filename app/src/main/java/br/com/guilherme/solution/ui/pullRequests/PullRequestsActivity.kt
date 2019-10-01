package br.com.guilherme.solution.ui.pullRequests

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.guilherme.solution.R
import br.com.guilherme.solution.component.DaggerActivityComponent
import br.com.guilherme.solution.models.PullRequest
import br.com.guilherme.solution.models.Repository
import br.com.guilherme.solution.module.ActivityModule
import br.com.guilherme.solution.util.EXTRA_REPO
import kotlinx.android.synthetic.main.activity_pull_requests.*
import kotlinx.android.synthetic.main.content_pull_requests.*
import javax.inject.Inject

class PullRequestsActivity : AppCompatActivity(), PullRequestsContract.View,
    PullRequestAdapter.onItemClickListener {

    @Inject
    lateinit var presenter: PullRequestsContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_requests)
        setSupportActionBar(toolbar)
        injectDependency()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        presenter.attach(this)
        presenter.subscribe()
        initView()
    }

    override fun showErrorMessage(error: String) {
        val alert = AlertDialog.Builder(applicationContext)
        alert.setTitle(getString(R.string.atention))
        alert.setMessage(error)
        alert.setNeutralButton(
            getString(R.string.ok),
            { dialog, id ->
                dialog.dismiss()
            }
        )
        alert.create()

        alert.show()
    }

    override fun loadDataSuccess(pullRequests: List<PullRequest>) {
        val adapter = PullRequestAdapter(applicationContext, pullRequests.toMutableList(), this)
        recycler_view_pull_request!!.setLayoutManager(LinearLayoutManager(applicationContext))
        recycler_view_pull_request!!.setAdapter(adapter)
    }

    override fun itemDetail(pull: PullRequest) {
        val openURL = Intent(Intent.ACTION_VIEW, Uri.parse(pull.html_url))
        startActivity(openURL)
    }

    private fun injectDependency() {
        val activityComponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .build()

        activityComponent.inject(this)
    }

    private fun initView() {
        val repo = intent.getParcelableExtra<Repository>(EXTRA_REPO)
        presenter.loadData(repo!!.owner.login, repo.name)
    }
}
