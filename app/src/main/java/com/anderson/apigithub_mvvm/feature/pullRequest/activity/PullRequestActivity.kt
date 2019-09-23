package com.anderson.apigithub_mvvm.feature.pullRequest.activity

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import br.com.anderson.apigithub_mvvm.ui.generic.base.activity.BaseActivity
import com.anderson.apigithub_mvvm.R
import com.anderson.apigithub_mvvm.data.presentation.PullRequestPresentation
import com.anderson.apigithub_mvvm.data.presentation.RepositoryPresentation
import com.anderson.apigithub_mvvm.databinding.ActivityPullRequestBinding
import com.anderson.apigithub_mvvm.feature.pullRequest.adapter.PullRequestAdapter
import com.anderson.apigithub_mvvm.feature.pullRequest.viewmodel.PullRequestViewModel

/**
 * Created by anderson on 22/09/19.
 */
class PullRequestActivity : BaseActivity<ActivityPullRequestBinding, PullRequestViewModel>() {

    companion object {
        const val REPO_OBJ = "repoObj"
    }

    private lateinit var repositoryPresentation: RepositoryPresentation
    private lateinit var pullRequestAdapter: PullRequestAdapter

    override fun getLayoutId(): Int {
        return R.layout.activity_pull_request
    }

    override fun getViewModelClass(): Class<PullRequestViewModel> = PullRequestViewModel::class.java

    @RequiresApi(Build.VERSION_CODES.O)
    override fun init() {
        bind.viewModel = viewModel

        repositoryPresentation = (getIntent().getExtras()?.getSerializable(REPO_OBJ) as? RepositoryPresentation)!!

        initToolbar()
        initListView()
    }

    private fun initToolbar(){
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = resources.getString(R.string.pull_requests)
    }

    private fun initListView(){
        viewModel.getListRepositoryLiveDate(repositoryPresentation.login, repositoryPresentation.name).observe(this, Observer {
            pullRequestAdapter = PullRequestAdapter(it as ArrayList<PullRequestPresentation>)

            bind.listView.adapter = pullRequestAdapter
            bind.progressBar.visibility = View.INVISIBLE
            onClickItem(it)
        })
    }

    private fun onClickItem(it: ArrayList<PullRequestPresentation> ){

        bind.listView.onItemClickListener = object : AdapterView.OnItemClickListener{

            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = it.get(position)

                var openUrl = Intent(Intent.ACTION_VIEW)
                openUrl.data = Uri.parse(selectedItem.htmlUrl)
                startActivity(openUrl)
            }

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
