package com.rafaelpereiraramos.desafioAndroid.view.repo

import android.app.SearchManager
import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import com.rafaelpereiraramos.desafioAndroid.R
import com.rafaelpereiraramos.desafioAndroid.core.ViewModelFactory
import com.rafaelpereiraramos.desafioAndroid.database.model.RepoTO
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_repo.*
import javax.inject.Inject

/**
 * Created by Rafael P. Ramos on 12/10/2018.
 */
class RepoActivity : AppCompatActivity(), HasSupportFragmentInjector {

    companion object {
        private const val LAST_SEARCH_QUERY: String = "last_search_query"
        private const val DEFAULT_QUERY = "Java"
    }

    @Inject lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    @Inject lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: RepoViewModel

    private val adapter = RepoPagedListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(RepoViewModel::class.java)

        val query = savedInstanceState?.getString(LAST_SEARCH_QUERY) ?: DEFAULT_QUERY

        setUI()
        subscribe()

        viewModel.searchRepo(query)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(LAST_SEARCH_QUERY, viewModel.lastQueryValue())
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_repo_menu, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager

        (menu.findItem(R.id.search).actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }

                override fun onQueryTextSubmit(query: String?): Boolean {
                    search(query!!)
                    return true
                }
            })
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector

    private fun subscribe() {
        viewModel.repos.observe(this, Observer<PagedList<RepoTO>> {
            Log.d("Activity", "list: ${it?.size}")
            //showEmptyList(it?.size == 0)
            adapter.submitList(it)
        })

        viewModel.networkError.observe(this, Observer<String> {
            Toast.makeText(this, "\uD83D\uDE28 Wooops ${it}", Toast.LENGTH_LONG).show()
        })
    }

    private fun setUI() {
        toolbar.setTitle(R.string.app_name)
        toolbar.setTitleTextColor(Color.WHITE)

        repo_list.adapter = adapter

        setSupportActionBar(toolbar)
    }

    private fun search(query: String) {
        repo_list.scrollToPosition(0)
        viewModel.searchRepo(query)
        adapter.submitList(null)
    }
}
