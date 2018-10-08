package com.joaoibarra.gitapp.api.paging

import android.arch.paging.ItemKeyedDataSource
import android.util.Log
import com.joaoibarra.gitapp.api.GitApi
import com.joaoibarra.gitapp.api.model.Repo
import io.reactivex.disposables.CompositeDisposable



class RepositoryDataSource (
        private val gitApi: GitApi,
        private val compositeDisposable: CompositeDisposable
): ItemKeyedDataSource<Int, Repo>(){
    private var pageNumber = 1

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Repo>) {
        createObservable(pageNumber , callback, null)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Repo>) {
        createObservable(pageNumber + 1, null, callback)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Repo>) {
        createObservable( pageNumber - 1, null, callback)
    }

    override fun getKey(item: Repo): Int {
        return item.id
    }

    private fun createObservable(requestedPage: Int,
                                 initialCallback: LoadInitialCallback<Repo>?,
                                 callback: LoadCallback<Repo>?) {
        pageNumber = requestedPage
        if(requestedPage > 0) {
            compositeDisposable.add(
                gitApi.searchByQuery("language:Java","stars", requestedPage)
                    .subscribe(
                        { response ->
                            initialCallback?.onResult(response.items)
                            callback?.onResult(response.items)
                        },
                        { t ->
                            Log.d("Error On Load", "Error loading page: $requestedPage", t)
                        }
                    )
            )
        }
    }
}