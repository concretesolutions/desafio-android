package com.pedrenrique.githubapp.features.pr

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pedrenrique.githubapp.core.data.Failure
import com.pedrenrique.githubapp.core.data.PullRequest
import com.pedrenrique.githubapp.core.ext.supportActionBar
import com.pedrenrique.githubapp.core.ext.supportActivity
import com.pedrenrique.githubapp.core.platform.Adapter
import com.pedrenrique.githubapp.core.platform.BaseViewHolder
import com.pedrenrique.githubapp.core.platform.EndlessRecyclerViewScrollListener
import com.pedrenrique.githubapp.features.common.TypesFactoryAdapter
import com.pedrenrique.githubapp.features.common.viewholder.EmptyViewHolder
import kotlinx.android.synthetic.main.fragment_pull_requests.*
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.viewModel


class PullRequestsFragment : Fragment() {
    private val args by navArgs<PullRequestsFragmentArgs>()
    private val pullRequestsViewModel by currentScope.viewModel<PullRequestsViewModel>(this)

    private val prAdapter by lazy {
        val typesFactory = TypesFactory()
        Adapter(typesFactory)
    }

    private val RecyclerView.linearLayoutManager: LinearLayoutManager
        get() = layoutManager as LinearLayoutManager
    private lateinit var endlessRecyclerViewScrollListener: EndlessRecyclerViewScrollListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(
            com.pedrenrique.githubapp.R.layout.fragment_pull_requests,
            container,
            false
        )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        supportActivity?.title = args.repository.fullName
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        pullRequestsViewModel.load(args.repository)
        pullRequestsViewModel.state.observe(this, Observer {
            prAdapter.replace(it?.data ?: listOf())
        })
        rvPullRequests.setup()
    }

    private fun RecyclerView.setup() {
        layoutManager = LinearLayoutManager(context)
        endlessRecyclerViewScrollListener = EndlessRecyclerViewScrollListener(linearLayoutManager) {
            pullRequestsViewModel.loadMore(args.repository)
        }

        val decoration = DividerItemDecoration(context, linearLayoutManager.orientation)
        addItemDecoration(decoration)

        adapter = prAdapter
        setHasFixedSize(true)
        addOnScrollListener(endlessRecyclerViewScrollListener)
    }

    inner class TypesFactory : TypesFactoryAdapter() {
        override fun type(failure: Failure.Empty) =
            com.pedrenrique.githubapp.R.layout.item_pull_requests_empty

        override fun holder(type: Int, view: View): BaseViewHolder<*> {
            if (type == com.pedrenrique.githubapp.R.layout.item_pull_requests_empty)
                return EmptyViewHolder(view)
            return super.holder(type, view)
        }

        override fun click(pr: PullRequest) {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(pr.url)
            startActivity(i)
        }

        override fun click(failure: Failure) {
            when (failure) {
                Failure.Empty ->
                    supportActivity?.onBackPressed()
                is Failure.Full ->
                    pullRequestsViewModel.load(args.repository)
                is Failure.Item ->
                    pullRequestsViewModel.loadMore(args.repository)
            }
        }
    }
}