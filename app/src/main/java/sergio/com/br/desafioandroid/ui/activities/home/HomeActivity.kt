package sergio.com.br.desafioandroid.ui.activities.home

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_home.*
import sergio.com.br.desafioandroid.R
import sergio.com.br.desafioandroid.listeners.GitListListener
import sergio.com.br.desafioandroid.models.GitItemsModel
import sergio.com.br.desafioandroid.ui.activities.BaseActivity
import sergio.com.br.desafioandroid.ui.activities.pull_request.PullRequestActivity
import sergio.com.br.desafioandroid.ui.adapters.SearchListRecycleAdapter
import sergio.com.br.desafioandroid.ui.view_models.HomeViewModel
import sergio.com.br.desafioandroid.utils.Utils

class HomeActivity : BaseActivity(), GitListListener {
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var adapter: SearchListRecycleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        init()
        setCustomBar(getString(R.string.home_activity_title_text), false)
    }

    override fun populateData() {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        addAPIObservables(homeViewModel)

        if (!homeViewModel.hasLoaded) {
            homeViewModel.hasLoaded = true
            loadSearchList()
        }

        homeViewModel.searchMutableLiveData.observe(this, Observer {
            if (it == null) {
                homeViewModel.hasStoppedPaging = true

                //prevent crashes
                if (::adapter.isInitialized) {
                    adapter.hasStoppedPaging = true
                }

                Utils.showSimpleMessage(
                    this,
                    getString(R.string.api_error_text),
                    getString(R.string.unexpected_api_error_text)
                )
                return@Observer
            }

            if (!::adapter.isInitialized) {
                setRecycleListView(it)
            } else if (it.size == 0) {
                homeViewModel.hasStoppedPaging = true
                adapter.hasStoppedPaging = true
            } else {
                adapter.notifyDataSetChanged()
            }
        })
    }

    fun loadSearchList() {
        homeViewModel.loadSearchList()
    }

    fun setRecycleListView(searchList: ArrayList<GitItemsModel>) {
        adapter = SearchListRecycleAdapter(searchList, this, this)
        adapter.hasStoppedPaging = homeViewModel.hasStoppedPaging

        recyclerView.setLayoutManager(LinearLayoutManager(this))
        recyclerView.setAdapter(adapter)
    }

    override fun onPaging() {
        loadSearchList()
    }

    override fun onItemCliked(item: GitItemsModel) {
        val intent = Intent(this, PullRequestActivity::class.java)
        intent.putExtra("repositoryName", item.name)
        intent.putExtra("ownerName", item.owner.userName)
        startActivity(intent)
        overridePendingTransition(R.anim.right_in, R.anim.left_out)
    }
}
