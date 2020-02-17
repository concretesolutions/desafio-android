package com.concrete.desafio_android.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.concrete.desafio_android.R
import com.concrete.desafio_android.contract.RepositoriesContract
import com.concrete.desafio_android.data.domain.Repository
import com.concrete.desafio_android.presenter.RepositoriesPresenter
import com.concrete.desafio_android.ui.adapter.RepositoryListAdapter
import com.concrete.desafio_android.ui.listener.EndlessRecyclerViewScrollListener
import kotlinx.android.synthetic.main.fragment_repositories.list_java_repositories

class RepositoriesFragment : Fragment(), RepositoriesContract.View {

    val REPOSITORY_LIST = "repositoryList"

    private val repositoryList = ArrayList<Repository>()

    private val presenter: RepositoriesContract.Presenter =
        RepositoriesPresenter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_repositories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.setTitle(R.string.repositories_fragment_label)
        presenter.getRepositories()
        setListAdapter()
        addListDivider()
        setTitle()
    }

    private fun setTitle() {
        activity?.setTitle(R.string.repositories_fragment_label)
    }

    private fun setListAdapter() {
        list_java_repositories.adapter = RepositoryListAdapter(repositoryList, context!!) {
            val action =
                RepositoriesFragmentDirections.actionRepositoriesFragmentToPullRequestFragment(it)
            findNavController().navigate(action)
        }
        list_java_repositories.addOnScrollListener(object :
            EndlessRecyclerViewScrollListener(list_java_repositories.layoutManager as LinearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                presenter.getRepositories()
            }
        })
    }

    private fun addListDivider() {
        val dividerItemDecoration = DividerItemDecoration(
            list_java_repositories.context,
            DividerItemDecoration.VERTICAL
        )
        dividerItemDecoration.setDrawable(resources.getDrawable(R.drawable.list_item_divider)!!)
        list_java_repositories.addItemDecoration(dividerItemDecoration)
    }

    override fun showList(repositories: ArrayList<Repository>) {
        val curSize = list_java_repositories.adapter!!.itemCount
        repositoryList.addAll(repositories)
        list_java_repositories.adapter?.notifyItemRangeInserted(curSize, repositoryList.size - 1)
    }

    override fun showErrorMessage(messageId: Int) {
        val message = resources.getString(messageId)
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(REPOSITORY_LIST, repositoryList)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        savedInstanceState?.let { _ ->
            savedInstanceState.getParcelableArrayList<Repository>(REPOSITORY_LIST)?.let {
                repositoryList.clear()
                repositoryList.addAll(it)
            }
        }
    }
}
