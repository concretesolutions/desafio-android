package br.com.guilherme.solution.ui.repositoryList

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.guilherme.solution.R
import br.com.guilherme.solution.models.Repository
import br.com.guilherme.solution.models.Response
import br.com.guilherme.solution.module.ActivityModule
import br.com.guilherme.solution.ui.pullRequests.PullRequestsActivity
import br.com.guilherme.solution.util.EXTRA_REPO
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import br.com.guilherme.solution.component.DaggerActivityComponent

class MainActivity : AppCompatActivity(), RepositoryListContract.View,
    RepositoryAdapter.onItemClickListener {

    @Inject
    lateinit var presenter: RepositoryListContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        injectDependency()

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

    override fun loadDataSuccess(response: Response) {
        val adapter = RepositoryAdapter(applicationContext, response.items.toMutableList(), this)
        recycler_view_repositories!!.setLayoutManager(LinearLayoutManager(applicationContext))
        recycler_view_repositories!!.setAdapter(adapter)
    }

    override fun itemDetail(repository: Repository) {
        intent = Intent(applicationContext, PullRequestsActivity::class.java)
        intent.putExtra(EXTRA_REPO, repository)
        startActivity(intent)
    }

    private fun injectDependency() {
        val activityComponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .build()

        activityComponent.inject(this)
    }

    private fun initView() {
        presenter.loadData("language:Java", "stars", 1)
    }
}
