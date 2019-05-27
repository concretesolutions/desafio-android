package com.losingtimeapps.javahub.ui.home

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.losingtimeapps.javahub.R
import com.losingtimeapps.javahub.application.JavaHubApplication
import com.losingtimeapps.javahub.common.di.modules.ActivityModule
import com.losingtimeapps.javahub.common.presentation.BaseInjectingActivity
import com.losingtimeapps.javahub.ui.home.pullrequest.PullRequestFragment
import com.losingtimeapps.javahub.ui.home.repository.RepositoryFragment
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

            when (it) {
                RepositoryFragment::class.java.name -> {

                }
                PullRequestFragment::class.java.name -> {
                    setTittle(viewModel.title)
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
        viewModel.tag = RepositoryFragment::class.java.name
        viewModel.title = getString(R.string.app_name)
        setTittle(viewModel.title)
        viewModel.repositoryFragment = RepositoryFragment.newInstance()

        supportFragmentManager.beginTransaction()
            .replace(R.id.frame, viewModel.repositoryFragment!!, RepositoryFragment::class.java.name)
            .commit()


    }

    private fun setTittle(title: String) {
        supportActionBar?.title = title
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
        initBackButton()

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
            toolbar.navigationIcon = null

        }
        super.onBackPressed()

    }
}
