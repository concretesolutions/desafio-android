package com.losingtimeapps.javahub.ui.home

import android.os.Bundle
import android.view.View
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

    private var tag: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showRepositoryFragment()
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
        tag = RepositoryFragment::class.java.name
        setSupportActionBar(toolbar)
        setTittle(getString(R.string.app_name))

        if (supportFragmentManager.findFragmentByTag(RepositoryFragment::class.java.name) == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame, RepositoryFragment.newInstance(), RepositoryFragment::class.java.name)
                .commit()
        } else {
            val repoF =
                supportFragmentManager.findFragmentByTag(RepositoryFragment::class.java.name) as RepositoryFragment
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame, repoF, RepositoryFragment::class.java.name)
                .commit()
        }

    }

    fun setTittle(id: String) {
        supportActionBar!!.title = id
    }

    fun showPullRequestFragment(ownerName: String, repoName: String) {
        tag = PullRequestFragment::class.java.name
        setTittle(repoName)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationIcon(R.drawable.left_arrow)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        supportFragmentManager.beginTransaction()
            .replace(
                R.id.frame,
                PullRequestFragment.newInstance(ownerName, repoName),
                PullRequestFragment::class.java.name
            )
            .addToBackStack(RepositoryFragment::class.java.name).commit()

    }

    override fun onBackPressed() {
        if (tag == PullRequestFragment::class.java.name) {
            setTittle(getString(R.string.app_name))
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            tag = RepositoryFragment::class.java.name
        }
        super.onBackPressed()

    }
}
