package com.concrete.desafio_android.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.concrete.desafio_android.R
import com.concrete.desafio_android.contract.RepositoriesContract
import com.concrete.desafio_android.presenter.RepositoriesPresenter
import com.concrete.desafio_android.domain.Repository
import com.concrete.desafio_android.ui.adapter.RepositoryListAdapter
import com.concrete.desafio_android.ui.listener.EndlessRecyclerViewScrollListener
import kotlinx.android.synthetic.main.activity_home.list_java_repositories
import com.concrete.desafio_android.util.REPOSITORY_TAG


class HomeActivity : AppCompatActivity(), RepositoriesContract.View {

    private val repositoryList = ArrayList<Repository>()

    private val presenter: RepositoriesContract.Presenter =
        RepositoriesPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        presenter.getRepositories()
        setListAdapter()
    }

    private fun setListAdapter() {
        list_java_repositories.adapter = RepositoryListAdapter(repositoryList, this) {
            startPullRequestListActivity(it)
        }
        list_java_repositories.addOnScrollListener(object: EndlessRecyclerViewScrollListener(list_java_repositories.layoutManager as LinearLayoutManager){
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                presenter.getRepositories()
            }
        })
        val dividerItemDecoration = DividerItemDecoration(
            list_java_repositories.context,
            list_java_repositories.layoutManager!!.layoutDirection
        )
        dividerItemDecoration.setDrawable(getDrawable(R.drawable.list_item_divider)!!)
        list_java_repositories.addItemDecoration(dividerItemDecoration)
    }

    private fun startPullRequestListActivity(repository: Repository) {
        val intent = Intent(this, PullRequestListActivity::class.java)
        intent.putExtra(REPOSITORY_TAG, repository)
        startActivity(intent)
    }

    override fun showList(repositories: ArrayList<Repository>) {
        val curSize = list_java_repositories.adapter!!.itemCount
        repositoryList.addAll(repositories)
        list_java_repositories.adapter?.notifyItemRangeInserted(curSize, repositoryList.size - 1)
    }

    override fun showErrorMessage(messageId: Int) {
        val message = resources.getString(messageId)
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList("repositoryList", repositoryList)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.getParcelableArrayList<Repository>("repositoryList")?.let {
            repositoryList.clear()
            repositoryList.addAll(it)
        }
    }
}