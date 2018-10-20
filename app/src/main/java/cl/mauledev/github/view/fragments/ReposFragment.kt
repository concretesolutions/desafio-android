package cl.mauledev.github.view.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import cl.mauledev.github.R
import cl.mauledev.github.utils.ConnectionUtils
import cl.mauledev.github.utils.Constants
import cl.mauledev.github.view.lists.adapters.ReposAdapter
import cl.mauledev.github.view.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.fragment_repos.*
import android.support.v7.widget.RecyclerView
import android.util.Log
import timber.log.Timber


class ReposFragment: Fragment() {

    private var viewModel: MainViewModel? = null

    private lateinit var reposAdapter: ReposAdapter

    var loading = true
    var previousTotal = 0
    val visibleThreshold = 5

    var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        savedInstanceState?.let {
            initExtras(it)
        }

        initViewModel()
    }

    private fun initExtras(arguments: Bundle?) {
        page = arguments?.getInt(Constants.PAGE) ?: 1
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_repos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initSwipeRefresh()
        initRecycler()
        initSelectedRepo()
        initRepos()
        initReposFromRemote()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireActivity() as AppCompatActivity
        val supportActionBar = activity.supportActionBar

        supportActionBar?.setTitle(R.string.app_name)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(Constants.PAGE, page)
        super.onSaveInstanceState(outState)
    }

    private fun initSelectedRepo() {
        viewModel?.getSelectedRepo()?.observe(this, Observer {

            if (!ConnectionUtils.isConnected(requireContext()))
                return@Observer

            it?.let {
                val bundle = Bundle().apply {
                    this.putParcelable(Constants.REPO, it)
                }

                Navigation.findNavController(requireActivity(), R.id.nav_host)
                        .navigate(R.id.repoFragment, bundle)
            }
        })
    }

    private fun initAdapter() {
        reposAdapter = ReposAdapter(viewModel)
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
            page = 1
            previousTotal = 0
            initReposFromRemote()
        }

        swipeRefresh.setColorSchemeColors(ContextCompat.getColor(requireContext(),
                R.color.colorAccent))
    }

    private fun initRecycler() {
        repos.layoutManager = LinearLayoutManager(requireContext(),
                LinearLayoutManager.VERTICAL, false)
        repos.setHasFixedSize(true)

        var firstVisibleItem: Int
        var visibleItemCount: Int
        var totalItemCount: Int

        repos.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    val layoutManager = repos.layoutManager as LinearLayoutManager
                    visibleItemCount = layoutManager.childCount
                    totalItemCount = layoutManager.itemCount
                    firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

                    if (loading) {
                        if (totalItemCount > previousTotal) {
                            loading = false
                            previousTotal = totalItemCount
                        }
                    }
                    if (!loading && (totalItemCount - visibleItemCount)
                            <= (firstVisibleItem + visibleThreshold)) {
                        page += 1
                        viewModel?.getRepos(page,
                                ConnectionUtils.isConnected(requireContext()))
                        loading = true
                    }
                }
            }
        })

        repos.adapter = reposAdapter
    }

    private fun initRepos() {
        viewModel?.checkRepos()?.observe(this, Observer {
            reposAdapter.submitList(it)
            reposAdapter.notifyDataSetChanged()
        })
    }

    private fun initReposFromRemote() {
        viewModel?.getRepos(page, isConnected = ConnectionUtils.isConnected(requireContext()))
    }
}