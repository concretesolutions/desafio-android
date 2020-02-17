package com.concrete.desafio_android.ui.fragment


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import com.concrete.desafio_android.R
import com.concrete.desafio_android.contract.PullRequestsContract
import com.concrete.desafio_android.data.domain.PullRequest
import com.concrete.desafio_android.data.domain.Repository
import com.concrete.desafio_android.presenter.PullRequestsPresenter
import com.concrete.desafio_android.ui.adapter.PullRequestListAdapter
import kotlinx.android.synthetic.main.fragment_pull_request.pull_request_list


class PullRequestFragment : Fragment(), PullRequestsContract.View {

    val PULL_REQUESTS_LIST = "pullRequests"
    private lateinit var repository: Repository
    private val presenter: PullRequestsContract.Presenter =
        PullRequestsPresenter(this)
    private val pullRequests = ArrayList<PullRequest>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pull_request, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: PullRequestFragmentArgs by navArgs()
        repository = args.repository
        if (savedInstanceState == null){
            presenter.getPullRequests(repository.owner.login, repository.name)
        }
        setTitle()
        setRequestsList()
        addListDivider()
    }

    private fun addListDivider() {
        val dividerItemDecoration = DividerItemDecoration(
            pull_request_list.context,
            DividerItemDecoration.VERTICAL
        )
        dividerItemDecoration.setDrawable(resources.getDrawable(R.drawable.list_item_divider, resources.newTheme()))
        pull_request_list.addItemDecoration(dividerItemDecoration)
    }

    private fun setRequestsList() {
        context?.let {context -> pull_request_list.adapter = PullRequestListAdapter(pullRequests, context) {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it.html_url)))
        } }
    }

    private fun setTitle() {
        activity?.title = presenter.makeTitle(repository.name)
    }

    override fun showList(pullReqs: ArrayList<PullRequest>) {
        pullRequests.addAll(pullReqs)
        pull_request_list.adapter?.notifyDataSetChanged()
    }

    override fun showErrorMessage(messageId: Int) {
        val message = resources.getString(messageId)
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(PULL_REQUESTS_LIST, pullRequests)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        savedInstanceState?.let { _ ->
            savedInstanceState.getParcelableArrayList<PullRequest>(PULL_REQUESTS_LIST)?.let {
                pullRequests.clear()
                pullRequests.addAll(it)
            }
        }
    }
}
