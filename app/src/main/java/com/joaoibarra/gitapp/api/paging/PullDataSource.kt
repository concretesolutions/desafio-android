package com.joaoibarra.gitapp.api.paging

import android.arch.paging.ItemKeyedDataSource
import android.util.Log
import com.joaoibarra.gitapp.api.GitApi
import com.joaoibarra.gitapp.api.model.Pull
import io.reactivex.disposables.CompositeDisposable

class PullDataSource (
        private val gitApi: GitApi,
        private val compositeDisposable: CompositeDisposable,
        private val user: String,
        private val repo: String
): ItemKeyedDataSource<Int, Pull>(){
    
    private var pageNumber = 1

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Pull>) {
        createObservable(pageNumber , callback, null)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Pull>) {
        createObservable(pageNumber + 1, null, callback)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Pull>) {
        //Empty method
    }

    override fun getKey(item: Pull): Int {
        return item.id
    }

    private fun createObservable(requestedPage: Int,
                                 initialCallback: LoadInitialCallback<Pull>?,
                                 callback: LoadCallback<Pull>?) {
        pageNumber = requestedPage
        if(requestedPage > 0) {
            compositeDisposable.add(
                gitApi.searchPullByRepository(user, repo, requestedPage)
                    .subscribe(
                        { response ->
                            initialCallback?.onResult(response)
                            callback?.onResult(response)
                        },
                        { t ->
                            Log.d("Error On Load", "Error loading page: $requestedPage", t)
                        }
                    )
            )
        }
    }

}
