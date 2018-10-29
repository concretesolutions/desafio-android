package br.com.andreyneto.desafioandroid.ui.repos

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import br.com.andreyneto.desafioandroid.R
import br.com.andreyneto.desafioandroid.model.Repo
import kotlinx.android.synthetic.main.activity_repos.*
import android.support.v7.widget.RecyclerView
import br.com.andreyneto.desafioandroid.ui.EndlessScrollListener


class ReposActivity : AppCompatActivity(), ReposContract.View {

    private var mPresenter: ReposContract.Presenter? = null
    private var repoList: MutableList<Repo> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repos)
        ReposPresenter(this)
    }

    override fun setPresenter(presenter: ReposContract.Presenter) {
        mPresenter = presenter
    }

    override fun onStart() {
        super.onStart()
        mPresenter?.start()
        rvRepos.adapter = RepoAdapter(repoList, this, mPresenter!!)
        rvRepos.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvRepos.addOnScrollListener(object : EndlessScrollListener(rvRepos.layoutManager as LinearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                mPresenter?.getData(page)
            }
        })
    }

    override fun showData(items: List<Repo>) {
        repoList.addAll(items)
        rvRepos.adapter?.notifyDataSetChanged()
    }
}
