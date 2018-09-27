package br.com.desafio.concrete.view.repository


import android.os.Bundle
import android.os.Parcelable
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import br.com.desafio.concrete.R
import br.com.desafio.concrete.base.BaseActivity
import br.com.desafio.concrete.extension.ActivityAnimation
import br.com.desafio.concrete.extension.launchActivity
import br.com.desafio.concrete.model.Repository
import br.com.desafio.concrete.view.about.AboutActivity
import br.com.desafio.concrete.view.pullrequest.PullRequestsActivity
import br.com.desafio.concrete.view.repository.adapter.RepositoryAdapter
import kotlinx.android.synthetic.main.repository_list_activity.*
import kotlinx.android.synthetic.main.toolbar_include.*
import org.koin.android.ext.android.inject
import ru.alexbykov.nopaginate.paginate.NoPaginate

class RepositoryListActivity : BaseActivity(), RepositoryListContract.View {

    companion object {
        const val REPOSITORY_EXTRA = "repositoryExtra"
        const val RECYCLER_STATE_EXTRA = "recyclerState"
    }

    private val presenter by inject<RepositoryListContract.Presenter> ()

    private  var noPaginate: NoPaginate? = null
    private var adapter: RepositoryAdapter? = null
    private var recyclerState: Parcelable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.repository_list_activity)

        setupToolbar(toolbar, R.string.app_name, -1, false)

        presenter.attachView(this)

        fetchRepositories(savedInstanceState)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.mn_about -> launchActivity<AboutActivity>(animation = ActivityAnimation.TRANSLATE_UP)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun fetchRepositories(savedInstanceState: Bundle?){
        if(savedInstanceState == null){
            presenter.fetchRepositories("java")
        }else{
            val list = savedInstanceState.getParcelableArrayList<Repository>(REPOSITORY_EXTRA)
            recyclerState = savedInstanceState.getParcelable(RECYCLER_STATE_EXTRA)
            if(list != null && list.isNotEmpty()){
                onRepositoriesLoaded(list)
            }else{
                presenter.fetchRepositories("java")
            }
        }
    }

    override fun onStop() {
        presenter.detachView()
        super.onStop()
    }

    override fun onRepositoriesLoaded(repositories: List<Repository>) {
        setupRecyclerView(repositories)
        setupEndlessScroll()
    }

    private fun setupRecyclerView(repositories: List<Repository>) {
        val layoutManager = LinearLayoutManager(this)
        recyclerState?.let {
            layoutManager.onRestoreInstanceState(it)
        }
        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(DividerItemDecoration(this, layoutManager.orientation))
        adapter = RepositoryAdapter(repositories as ArrayList<Repository>, this)
        recyclerView.adapter = adapter
        swipeRefreshLayout.setOnRefreshListener{
            presenter.refreshList()
        }
    }

    private fun setupEndlessScroll(){

        noPaginate = NoPaginate
                        .with(recyclerView)
                        .setOnLoadMoreListener {
                            presenter.loadMore()
                        }
                        .build()

    }

    override fun onLoadMoreComplete(repositories: List<Repository>) {
        adapter?.addMoreItems(repositories)
    }

    override fun showPaginateError(show: Boolean) {
        noPaginate?.showError(show)
    }

    override fun setPaginateNoMoreData(show: Boolean) {

    }

    override fun showPaginateLoading(show: Boolean) {

        noPaginate?.showLoading(show)
    }

    override fun showLoadingIndicator(loadingVisible: Boolean) {
        swipeRefreshLayout.isRefreshing = loadingVisible
    }

    override fun onListItemClicked(repository: Repository) {
        val extras = Bundle()
        extras.putParcelable(PullRequestsActivity.REPOSITORY_EXTRA, repository)
        launchActivity<PullRequestsActivity>(extras, ActivityAnimation.TRANSLATE_LEFT)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        adapter?.let {
            outState?.let{bundle ->
                bundle.putParcelableArrayList(REPOSITORY_EXTRA, it.getDataSource())
                bundle.putParcelable(RECYCLER_STATE_EXTRA, recyclerView.layoutManager.onSaveInstanceState())
            }
        }
    }

    override fun showUnexpectedError(throwable: Throwable?) {
        super.showUnexpectedError(throwable)
        unexpectedError.visibility = View.VISIBLE

    }

}
