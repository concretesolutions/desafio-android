package com.losingtimeapps.javahub.ui.home

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.losingtimeapps.javahub.R
import com.losingtimeapps.javahub.application.JavaHubApplication
import com.losingtimeapps.javahub.common.di.modules.ActivityModule
import com.losingtimeapps.javahub.common.presentation.BaseInjectingActivity
import com.losingtimeapps.javahub.ui.home.pullrequest.PullRequestFragment
import com.losingtimeapps.javahub.ui.home.repository.RepositoryFragment
import com.losingtimeapps.presentation.ui.home.pullrequest.PullRequestViewModel
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : BaseInjectingActivity<HomeActivityComponent>() {
    override val layoutId: Int
        get() = R.layout.activity_main


    lateinit var viewModel: HomeActivityViewModel
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        viewModel = ViewModelProviders.of(this, HomeActivityViewModelFactory())
            .get(HomeActivityViewModel::class.java)
        viewModel.liveData.observe(this, Observer {
            setTittle(viewModel.title)
            when (it) {
                RepositoryFragment::class.java.name -> {
                }
                PullRequestFragment::class.java.name -> {
                    initBackButton()
                }

                else ->
                    showRepositoryFragment()
            }

        })
        viewModel.initHomeActivity()
        setTittle(viewModel.title)

    }

    override fun onInject(component: HomeActivityComponent) {
        component.inject(this)
    }

    override fun createComponent(): HomeActivityComponent {
        val app = application as JavaHubApplication
        val activityModule = ActivityModule(this)
        return app.getComponent().createHomeActivityComponent(activityModule)
    }


    private fun showRepositoryFragment() {
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        viewModel.tag = RepositoryFragment::class.java.name
        viewModel.title = getString(R.string.app_name)
        setTittle(viewModel.title)
        viewModel.repositoryFragment = RepositoryFragment.newInstance()

        supportFragmentManager.beginTransaction()
            .replace(R.id.frame, viewModel.repositoryFragment!!, RepositoryFragment::class.java.name)
            .commit()


    }

    fun setTittle(id: String) {
        supportActionBar!!.title = id
    }

    fun initBackButton() {
        toolbar.setNavigationIcon(R.drawable.left_arrow)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    fun showPullRequestFragment(ownerName: String, repoName: String) {
        viewModel.tag = PullRequestFragment::class.java.name
        viewModel.title = repoName
        setTittle(viewModel.title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationIcon(R.drawable.left_arrow)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        viewModel.pullrequestFragment = PullRequestFragment.newInstance(ownerName, repoName)
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.frame,
                viewModel.pullrequestFragment!!,
                PullRequestFragment::class.java.name
            )
            .addToBackStack(RepositoryFragment::class.java.name).commit()

    }

    override fun onBackPressed() {
        if ((viewModel.tag) == PullRequestFragment::class.java.name) {
            viewModel.title = getString(R.string.app_name)
            viewModel.tag = RepositoryFragment::class.java.name
            setTittle(viewModel.title)
            supportActionBar?.setDisplayHomeAsUpEnabled(false)

        }
        super.onBackPressed()

    }
}
