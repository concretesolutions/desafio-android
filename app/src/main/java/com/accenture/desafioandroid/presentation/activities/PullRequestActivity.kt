package com.accenture.desafioandroid.presentation.activities
import android.os.Bundle
import android.view.MenuItem
import com.accenture.desafioandroid.R
import com.accenture.desafioandroid.presentation.fragment.PullRequestFragment
import kotlinx.android.synthetic.main.activity_pull_request.*

class PullRequestActivity : BaseActivity() {


    override val layout: Int
        get() = R.layout.activity_pull_request

    override fun onCreateView(savedInstanceState: Bundle?) {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)


        val owner = intent.getStringExtra("owner")
        val repo = intent.getStringExtra("repo")

        tv_title.text = repo

        val pullRequestFragment = PullRequestFragment.newInstance(owner, repo)
        pushFragment(pullRequestFragment, "PullRequestFragment")

     }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> {
                onBackPressed()
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
