package cl.jesualex.desafio_android.repo.presentation.fragment

import android.view.View
import android.widget.Toast
import cl.jesualex.desafio_android.R
import cl.jesualex.desafio_android.base.presentation.Fragment
import cl.jesualex.desafio_android.base.presentation.ItemAdapterListener
import cl.jesualex.desafio_android.repo.data.domain.entity.Pull
import cl.jesualex.desafio_android.repo.presentation.adapter.PullAdapter
import cl.jesualex.desafio_android.repo.presentation.contract.PullContract
import cl.jesualex.desafio_android.repo.presentation.presenter.PullPresenter
import kotlinx.android.synthetic.main.fragment_pull.*
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager


/**
 * Created by jesualex on 2019-05-28.
 */

class PullFragment: Fragment(), PullContract.View{
    private val pullAdapter = PullAdapter()
    private val presenter = PullPresenter()
    private val posKey = "pos"

    private var fullName: String = ""

    override fun initView(view: View) {
        showProgress(true)
        pullAdapter.pulls = ArrayList()
        pullRv.adapter = pullAdapter

        if(pullAdapter.itemClickListener == null){
            pullAdapter.itemClickListener = ItemAdapterListener { item, _ ->
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(item.html_url))
                startActivity(browserIntent)
            }
        }

        pullSwipeLayout.setOnRefreshListener {
            tryAgainButton.visibility = View.GONE
            showProgress(true)
            presenter.updatePulls(fullName)
        }

        tryAgainButton.setOnClickListener {
            it.visibility = View.GONE
            showProgress(true)
            presenter.updatePulls(fullName)
        }

        presenter.setView(this)
        presenter.updatePulls(fullName)
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_pull
    }

    override fun onSaveInstanceState(outState: Bundle) {
        if(pullRv.layoutManager is LinearLayoutManager){
            outState.putInt(posKey, (pullRv.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition())
        }

        super.onSaveInstanceState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        if(pullRv.layoutManager is LinearLayoutManager && savedInstanceState != null) {
            pullRv.scrollToPosition(savedInstanceState.getInt(posKey))
        }
    }

    override fun setPulls(pulls: List<Pull>?) {
        showProgress(false)

        if(pullSwipeLayout.isRefreshing){
            pullSwipeLayout.isRefreshing = false
            pullRv.scrollToPosition(0)
        }

        if(pulls == null){
            Toast.makeText(context, R.string.errorGeneral, Toast.LENGTH_LONG).show()
            tryAgainButton.visibility = View.VISIBLE
        }else{
            pullAdapter.pulls = pulls
        }
    }

    override fun setTotals(opened: Int, closed: Int) {
        closedTv.text = getString(R.string.closed, closed)
        openedTv.text = getString(R.string.opened, opened)
    }

    override fun showProgress(show: Boolean) {
        container.visibility = if(show) View.GONE else View.VISIBLE
        progressBar.visibility = if(!show) View.GONE else View.VISIBLE
    }

    fun setFullName(fullName: String){
        this.fullName = fullName
    }
}