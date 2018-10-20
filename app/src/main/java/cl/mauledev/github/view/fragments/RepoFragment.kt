package cl.mauledev.github.view.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import cl.mauledev.github.R
import cl.mauledev.github.data.model.Repo
import cl.mauledev.github.utils.ConnectionUtils
import cl.mauledev.github.utils.Constants
import cl.mauledev.github.view.lists.adapters.PullRequestsAdapter
import cl.mauledev.github.view.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.fragment_repo.*

class RepoFragment: Fragment() {

    private var viewModel: MainViewModel? = null

    lateinit var reposAdapter: PullRequestsAdapter

    private var repo: Repo? = null

    private var recyclerState: Parcelable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        savedInstanceState?.let {
            initExtras(it)
        } ?: run {
            initExtras(arguments)
        }

        initViewModel()
    }

    private fun initExtras(arguments: Bundle?) {
        repo = arguments?.getParcelable(Constants.REPO)
        recyclerState = arguments?.getParcelable(Constants.RECYCLER_STATE)
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_repo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initSwipeRefresh()
        initRecycler()
        initPullRequests()
        initSelectedPullRequest()
    }

    private fun initSelectedPullRequest() {
        viewModel?.getSelectedPullRequest()?.observe(this, Observer {
            if (!ConnectionUtils.isConnected(requireContext()))
                return@Observer

            it?.let {
                val bundle = Bundle().apply {
                    this.putString(Constants.PR_TITLE, it.title)
                    this.putString(Constants.PR_URL, it.url)
                }

                Navigation.findNavController(requireActivity(), R.id.nav_host)
                        .navigate(R.id.pullRequestFragment, bundle)
            }
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireActivity() as AppCompatActivity
        val supportActionBar = activity.supportActionBar

        if (repo?.name?.isNotEmpty() == true)
            supportActionBar?.title = repo?.name

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(Constants.REPO, repo)
        outState.putParcelable(Constants.RECYCLER_STATE, recyclerState)
        super.onSaveInstanceState(outState)
    }

    override fun onStop() {
        super.onStop()
        recyclerState = pullRequests.layoutManager?.onSaveInstanceState()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel?.getSelectedRepo()?.call()
        viewModel?.clearPullRequests()
    }

    private fun initAdapter() {
        reposAdapter = PullRequestsAdapter(viewModel)
    }

    private fun initSwipeRefresh() {

        viewModel?.checkIsLoading()?.observe(this, Observer {
            it?.let {
                swipeRefresh.isRefreshing = it
            } ?: run {
                swipeRefresh.isRefreshing = false
            }
        })

        swipeRefresh.setOnRefreshListener {
            initPullRequestsFromRemote()
        }

        swipeRefresh.setColorSchemeColors(ContextCompat.getColor(requireContext(),
                R.color.colorAccent))
    }

    private fun initRecycler() {
        pullRequests.layoutManager = LinearLayoutManager(requireContext(),
                LinearLayoutManager.VERTICAL, false)
        pullRequests.setHasFixedSize(true)
        pullRequests.adapter = reposAdapter
    }

    private fun initPullRequests() {
        viewModel?.checkPullRequests()?.observe(this, Observer {
            reposAdapter.submitList(it)
            pullRequests.layoutManager?.onRestoreInstanceState(recyclerState)
        })
    }

    override fun onResume() {
        super.onResume()
        initPullRequestsFromRemote()
    }

    private fun initPullRequestsFromRemote() {
        repo?.let {
            viewModel?.getPullRequests(it,
                    isConnected = ConnectionUtils.isConnected(requireContext()))

        }
    }
}