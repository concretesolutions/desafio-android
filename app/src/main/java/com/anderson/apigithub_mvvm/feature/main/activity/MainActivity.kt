package com.anderson.apigithub_mvvm.feature.main.activity

import android.app.Activity
import android.content.Intent
import android.opengl.Visibility
import android.util.Log
import android.view.View
import android.widget.AbsListView
import android.widget.AdapterView
import androidx.lifecycle.Observer
import br.com.anderson.apigithub_mvvm.ui.generic.base.activity.BaseActivity
import com.anderson.apigithub_mvvm.R
import com.anderson.apigithub_mvvm.data.presentation.RepositoryPresentation
import com.anderson.apigithub_mvvm.databinding.ActivityMainBinding
import com.anderson.apigithub_mvvm.feature.main.adapter.RepositoryAdapter
import com.anderson.apigithub_mvvm.feature.main.viewmodel.MainViewModel
import com.anderson.apigithub_mvvm.feature.pullRequest.activity.PullRequestActivity
import com.anderson.apigithub_mvvm.feature.pullRequest.activity.PullRequestActivity.Companion.REPO_OBJ

/**
 * Created by anderson on 21/09/19.
 */
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    private lateinit var repositoryAdapter: RepositoryAdapter
    private var currentPage: Int = 1
    private var previousTotalItemCount: Int = 1
    private var visibleThreshold : Int = 1
    private var loading = true
    private var end = false

    private lateinit var activity: Activity

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getViewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    override fun init() {
        bind.viewModel = viewModel
        activity = this

        initToolbar()
        initPage1()
        initScroll()
    }

    private fun initToolbar(){
        supportActionBar?.title = resources.getString(R.string.app_name)
    }

    private fun initPage1(){
        viewModel.getListRepositoryLiveDate(currentPage).observe(this, Observer {
            repositoryAdapter = RepositoryAdapter(it as ArrayList<RepositoryPresentation>)

            bind.listView.adapter = repositoryAdapter

            loading = false
            bind.progressBar.visibility = View.INVISIBLE
            onClickItem(it)
        })
    }

    private fun initScroll(){

        bind.listView.setOnScrollListener(object : AbsListView.OnScrollListener{

            override fun onScroll(view: AbsListView?, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {
                if(!end){
                    if (totalItemCount < previousTotalItemCount) {
                        currentPage = 1
                        previousTotalItemCount = totalItemCount
                        if (totalItemCount == 0) {
                            loading = true
                            bind.progressBar.visibility = View.VISIBLE
                        }
                    }

                    if (loading && (totalItemCount > previousTotalItemCount)) {
                        loading = false
                        previousTotalItemCount = totalItemCount
                        currentPage++
                    }

                    if (!loading && (firstVisibleItem + visibleItemCount + visibleThreshold) >= totalItemCount ) {

                        loading = true
                        bind.progressBar.visibility = View.VISIBLE
                        viewModel.getListRepositoryLiveDate(currentPage).observe(activity as MainActivity, Observer {
                            if(it != null){
                                repositoryAdapter = RepositoryAdapter(it as ArrayList<RepositoryPresentation>)

                                bind.listView.adapter = repositoryAdapter

                                bind.listView.setSelection(firstVisibleItem + 2)

                                onClickItem(it)
                            }else{
                                end = true
                            }

                            loading = false
                            bind.progressBar.visibility = View.INVISIBLE
                        })
                    }

                }

            }

            override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {
            }

        })
    }

    private fun onClickItem(it: ArrayList<RepositoryPresentation> ){

        bind.listView.onItemClickListener = object : AdapterView.OnItemClickListener{

            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = it.get(position)
                Log.e("Click", selectedItem.name)

                val intent = Intent(this@MainActivity, PullRequestActivity::class.java)
                intent.putExtra(REPO_OBJ, selectedItem)

                startActivity(intent)
            }

        }
    }
}
