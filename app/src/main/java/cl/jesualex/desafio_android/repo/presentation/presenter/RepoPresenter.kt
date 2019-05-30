package cl.jesualex.desafio_android.repo.presentation.presenter

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import cl.jesualex.desafio_android.R
import cl.jesualex.desafio_android.base.remote.UseCaseObserver
import cl.jesualex.desafio_android.repo.data.domain.entity.Repo
import cl.jesualex.desafio_android.repo.data.local.repository.RepoLocalRepo
import cl.jesualex.desafio_android.repo.data.local.view_model.RepoViewModel
import cl.jesualex.desafio_android.repo.presentation.contract.RepoContract
import cl.jesualex.desafio_android.search.data.domain.entity.SearchBase
import cl.jesualex.desafio_android.search.domain.use_case.SearchJavaRepoByStartUseCase

/**
 * Created by jesualex on 2019-05-28.
 */
class RepoPresenter: RepoContract.Presenter {
    private val searchJavaRepoUseCase = SearchJavaRepoByStartUseCase()
    private val repoLocalRepo = RepoLocalRepo()
    private val firstPage = 1
    private val itemsToLoadMore = 5

    private var view: RepoContract.View? = null
    private var loadingMore = false
    private var pageIndex = firstPage + 1

    private lateinit var allRepoLiveData: LiveData<List<Repo>>

    override fun setView(view: RepoContract.View) {
        this.view = view
    }

    override fun updateJavaRepos(removeOld: Boolean) {
        updateJavaRepos(firstPage, removeOld)
    }

    override fun loadNewJavaRepoPage() {
        updateJavaRepos(pageIndex, false)
    }

    override fun subscribeRepoViewModel(fragment: Fragment) {
        if(!::allRepoLiveData.isInitialized){
            allRepoLiveData = ViewModelProviders.of(fragment)
                .get(RepoViewModel::class.java)
                .getAllLiveData()
        }

        allRepoLiveData.observe(fragment, Observer{
            it?.let { repos -> view?.updateRepos(repos) }
        })
    }

    override fun unsubscribeRepoViewModel(fragment: Fragment) {
        if(::allRepoLiveData.isInitialized) {
            allRepoLiveData.removeObservers(fragment)
        }
    }

    override fun setScrollListener(rv: RecyclerView) {
        rv.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if(rv.layoutManager is LinearLayoutManager) {
                    val pos = (rv.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()

                    if(!loadingMore && pos >= rv.adapter.itemCount - itemsToLoadMore){
                        loadingMore = true
                        loadNewJavaRepoPage()
                    }
                }
            }
        })
    }

    private fun updateJavaRepos(page: Int, removeOld: Boolean){
        searchJavaRepoUseCase.execute(page, object : UseCaseObserver<SearchBase>(){
            override fun onNext(value: SearchBase) {
                super.onNext(value)

                if(page != firstPage){
                    pageIndex++
                    loadingMore = false
                }else{

                }

                if(removeOld){
                    repoLocalRepo.removeAllCascade()
                }

                repoLocalRepo.save(value.items)
            }

            override fun onError(e: Throwable) {
                super.onError(e)
                view?.showErrorMessage(R.string.error_update)
                loadingMore = false
            }
        })
    }
}