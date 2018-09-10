package com.concrete.desafioandroid.features.repos

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.concrete.desafioandroid.R
import com.concrete.desafioandroid.data.models.Repo
import com.concrete.desafioandroid.features.base.BaseActivity
import com.concrete.desafioandroid.features.pulls.PullsActivity
import com.concrete.desafioandroid.features.repos.adapter.ReposAdapter
import com.concrete.desafioandroid.utils.*
import kotlinx.android.synthetic.main.activity_repo.*
import org.kodein.di.Kodein
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance


class ReposActivity : BaseActivity<ReposView>(), ReposView {

    override val kodein: Kodein by closestKodein()

    override val layoutId: Int = R.layout.activity_repo
    override val presenter: ReposPresenter by instance()

    private val reposList = ArrayList<Repo>()
    private lateinit var adapter: ReposAdapter
    private var isLoading = false

    private val OFFSET = 10
    private val URL_GARBAGE = "{/number}"

    override fun setPresenter() {
        presenter.attachView(this)
    }

    override fun onCreate() {
        reposRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
        reposRecyclerView.setHasFixedSize(true)

        adapter = ReposAdapter(reposList, applicationContext
        ) {
            val intent = Intent(applicationContext, PullsActivity::class.java)
            intent.putExtra(INTENT_EXTRA_PULL_TITLE, it.name)
            intent.putExtra(INTENT_EXTRA_PULL_URL, it.pullsUrl.replace(URL_GARBAGE, EMPTY_STRING))

            startActivity(intent)
        }
        reposRecyclerView.adapter = adapter
        reposRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                val lastPosition = (recyclerView?.layoutManager as LinearLayoutManager)
                        .findLastVisibleItemPosition()

                if (lastPosition >= reposList.size - OFFSET && !isLoading) {
                    presenter.page++
                    presenter.getRepos(true)
                }
            }
        })

        presenter.getRepos(!hasSavedInstances)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putParcelableArrayList(EXTRA_LIST, reposList);
        outState?.putInt(EXTRA_CURRENT_PAGE, presenter.page)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        reloadList(savedInstanceState?.getParcelableArrayList<Repo>(EXTRA_LIST)!!)
        presenter.onRestoreInstance(savedInstanceState.getInt(EXTRA_CURRENT_PAGE))
    }

    override fun onGetReposListSuccess(reposList: List<Repo>) {
        reloadList(reposList)
    }

    override fun onFetchDetails(position: Int) {
        adapter.notifyItemChanged(position)
    }

    override fun showError(messsage: String?) {
        showMessage(messsage)
    }

    override fun loading(loading: Boolean) {
        isLoading = loading
    }

    private fun reloadList(list: List<Repo>) {
        reposList.addAll(list)
        adapter.notifyDataSetChanged()
    }

}
