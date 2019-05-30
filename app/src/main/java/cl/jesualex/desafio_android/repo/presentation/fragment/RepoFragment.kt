package cl.jesualex.desafio_android.repo.presentation.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cl.jesualex.desafio_android.R
import cl.jesualex.desafio_android.base.presentation.Fragment
import cl.jesualex.desafio_android.base.presentation.ItemAdapterListener
import cl.jesualex.desafio_android.repo.data.domain.entity.Repo
import cl.jesualex.desafio_android.repo.presentation.adapter.RepoAdapter
import cl.jesualex.desafio_android.repo.presentation.contract.RepoContract
import cl.jesualex.desafio_android.repo.presentation.presenter.RepoPresenter
import kotlinx.android.synthetic.main.fragment_repo.*

/**
 * Created by jesualex on 2019-05-28.
 */
class RepoFragment: Fragment(), RepoContract.View {
    private val presenter = RepoPresenter()
    private val repoAdapter = RepoAdapter()
    private val pullFragment = PullFragment()
    private val posKey = "pos"

    override fun initView(view: View) {
        presenter.setView(this)
        presenter.updateJavaRepos(true)

        repoAdapter.itemClickListener = ItemAdapterListener { item, _ ->
            pullFragment.setFullName(item.full_name)

            fragmentManager
                ?.beginTransaction()
                ?.add(R.id.fragmentContainer, pullFragment)
                ?.addToBackStack(null)
                ?.commit()
        }

        repoRv.adapter = repoAdapter
    }

    override fun onResume() {
        super.onResume()
        presenter.subscribeRepoViewModel(this)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        if(repoRv.layoutManager is LinearLayoutManager){
            outState.putInt(posKey, (repoRv.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition());
        }

        super.onSaveInstanceState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        if(repoRv.layoutManager is LinearLayoutManager && savedInstanceState != null) {
            repoRv.scrollToPosition(savedInstanceState.getInt(posKey));
        }
    }

    override fun onPause() {
        super.onPause()
        presenter.unsubscribeRepoViewModel(this)
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_repo
    }

    override fun updateRepos(repos: List<Repo>) {
        if(repos.isNotEmpty()){
            repoAdapter.repos = repos
        }
    }
}