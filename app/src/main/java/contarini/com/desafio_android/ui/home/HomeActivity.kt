package contarini.com.desafio_android.ui.home

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import contarini.com.br.ui.base.BaseActivity
import contarini.com.desafio_android.R
import contarini.com.desafio_android.data.extensions.setup
import contarini.com.desafio_android.data.extensions.startActivitySlideTransition
import contarini.com.desafio_android.data.models.BaseRepositoriesResponse
import contarini.com.desafio_android.data.models.RepositoriesResponse
import contarini.com.desafio_android.ui.request.createPullRequestIntent
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity(), HomeContract.View {

    lateinit var layoutManager : LinearLayoutManager
    private var pageCount : Int = 1
    lateinit var mList : ArrayList<RepositoriesResponse>
    private var doubleBackToExitPressedOnce : Boolean = false

    private val mPresenter : HomeContract.Presenter by lazy {
        val presenter = HomePresenter()
        presenter.attachView(this)
        presenter
    }

    private val mAdapter by lazy {
        val adapter = HomeAdapter(context, object : HomeAdapter.OnItemClickListener{
            override fun onItemClicked(item: RepositoriesResponse) {
                mPresenter.onClickItem(item)
            }
        })
        layoutManager = LinearLayoutManager(context)
        rvHome.setup(adapter, layoutManager = layoutManager)
        adapter
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setup()
        setListeners()
        mPresenter.getIncidents(pageCount)
    }

    private fun setup(){
        setToolbar(getString(R.string.fragment_home), false)
    }

    private fun setListeners(){
        rvHome.addOnScrollListener( object  : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val childCount = layoutManager.childCount
                val visibleItemPosition = layoutManager.findFirstCompletelyVisibleItemPosition()

                if(progressBar.visibility == View.GONE){
                    if(visibleItemPosition + childCount == mAdapter.list.size){
                        pageCount++
                        mPresenter.getIncidents(pageCount)
                    }
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }

    override fun displayError(msg: String?) {
        Snackbar.make(contentHome, msg!!, Snackbar.LENGTH_LONG)
            .setAction(getString(R.string.error)) { mPresenter.getIncidents(pageCount) }
            .show()
    }

    override fun displayIncidents(incidents: BaseRepositoriesResponse) {
        mList = incidents.items
        mAdapter.setRepositories(incidents.items)
        mAdapter.notifyDataSetChanged()
        showList()
    }

    override fun loadIncidentsDetail(item: RepositoriesResponse) {
        startActivitySlideTransition(createPullRequestIntent(item))
    }

    override fun showLoading(loading: Boolean) {
        if(loading){
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    private fun showList(){
        rvHome.visibility = View.VISIBLE
    }

    override fun onBackPressed() {
        if(doubleBackToExitPressedOnce){
            super.finish()
        }
        doubleBackToExitPressedOnce = true
        Toast.makeText(this, getString(R.string.exit), Toast.LENGTH_SHORT).show()

        Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
    }

}

