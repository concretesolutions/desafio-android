package br.com.andreyneto.desafioandroid.ui.pulls

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import br.com.andreyneto.desafioandroid.R
import br.com.andreyneto.desafioandroid.model.Pull
import br.com.andreyneto.desafioandroid.model.Repo
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_pulls.*
import kotlinx.android.synthetic.main.list_repo.*


class PullsActivity : AppCompatActivity(), PullsContract.View {

    private var mPresenter: PullsContract.Presenter? = null
    private var pullList: MutableList<Pull> = mutableListOf()

    private val repo by lazy {
        Gson().fromJson<Repo>(
            intent.getStringExtra("repo"),
            Repo::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pulls)
        PullsPresenter(this, repo)
        populateRepo()
    }

    private fun populateRepo() {
        lblRepoTitle.text = repo.name
        lblRepoDescripition.text = repo.description
        lblFork.text = repo.forks.toString()
        lblStars.text = repo.stars.toString()
    }

    override fun onStart() {
        super.onStart()
        mPresenter?.start()
        rvPulls.adapter = PullAdapter(pullList, this, mPresenter!!)
        rvPulls.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    override fun showData(items: List<Pull>) {
        pullList.addAll(items)
        rvPulls.adapter?.notifyDataSetChanged()
    }

    override fun setPresenter(presenter: PullsContract.Presenter) {
        mPresenter = presenter
    }
}
