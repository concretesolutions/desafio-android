package com.hotmail.fignunes.desafioconcretesolution.presentation.gitrepository

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hotmail.fignunes.desafioconcretesolution.R
import com.hotmail.fignunes.desafioconcretesolution.common.BaseActivity
import com.hotmail.fignunes.desafioconcretesolution.databinding.ActivityGitRepositoryBinding
import com.hotmail.fignunes.desafioconcretesolution.presentation.gitrepository.adapter.GitRepositoryAdapter
import com.hotmail.fignunes.desafioconcretesolution.presentation.pullrequest.PullRequestActivity
import com.hotmail.fignunes.skytestefabionunes.model.GitRepository
import com.hotmail.fignunes.skytestefabionunes.model.GitRepositoryItem
import kotlinx.android.synthetic.main.activity_git_repository.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class GitRepositoryActivity : BaseActivity(), GitRepositoryContract, GitRepositoryAdapter.ClickGitRepositoryItem,
    GitRepositoryAdapter.SetPosition {

    companion object {
        const val GIT_REPOSITORY_ITEM = "gitRepositoryItem"
    }

    private var isLoading = false
    private val GIT_REPOSITORY = "gitRepository"
    private val POSITION = "position"
    private var positionGitRepository: Int = 0
    private lateinit var adapter: GitRepositoryAdapter
    private var gitRepository: GitRepository? = null
    private var page = 1

    private val presenter: GitRepositoryPresenter by inject { parametersOf(this) }
    private lateinit var binding: ActivityGitRepositoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_git_repository)
        binding.presenter = presenter
        backButton()

        if (savedInstanceState == null) {
            presenter.onCreate(page)
        } else {
            gitRepository = savedInstanceState.getParcelable(GIT_REPOSITORY)
            positionGitRepository = savedInstanceState.getInt(POSITION)
            initializeAdapter(gitRepository!!)
        }
    }

    override fun initializeSwipe() {
        gitRepositorySwipe.setOnRefreshListener {
            positionGitRepository = 0
            page = 1
            presenter.searchGitRepository(page)
        }
    }

    override fun initializeList(gitRepository: GitRepository) {
        if(page == 1) {
            this.gitRepository = gitRepository
        } else {
            this.gitRepository!!.total_count = gitRepository.total_count
            this.gitRepository!!.incomplete_results = gitRepository.incomplete_results
            this.gitRepository!!.items += gitRepository.items
        }
    }

    override fun initializeAdapter(gitRepository: GitRepository) {
        adapter = GitRepositoryAdapter(this, this.gitRepository!!.items, this, this)
        gitRepositoryRecyclerview.adapter = adapter
        positionList()
    }

    override fun setFinishLoading() {
        isLoading = false
    }

    override fun initializeScroolListener() {
        gitRepositoryRecyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?

                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == gitRepository!!.items.size - 1) {
                        positionGitRepository--
                        page++
                        presenter.searchGitRepository(page)
                        isLoading = true
                    }
                }
            }
        })
    }

    override fun swipeOff() {
        gitRepositorySwipe.isRefreshing = false
    }

    private fun positionList() {
        gitRepositoryRecyclerview.layoutManager!!.scrollToPosition(positionGitRepository)
    }

    override fun set(position: Int) {
        Log.i("pagina","Posiçao >> " + positionGitRepository.toString())
        positionGitRepository = position
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun click(gitRepositoryItem: GitRepositoryItem) {
        startActivity<PullRequestActivity>(GIT_REPOSITORY_ITEM to gitRepositoryItem)
    }

    override fun message(error: Int) {
        toast(error)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(GIT_REPOSITORY, gitRepository)
        outState.putInt(POSITION, positionGitRepository)
        super.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        presenter.dispose()
        super.onDestroy()
    }
}