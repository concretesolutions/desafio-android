package com.joaoibarra.gitapp.api.paging

import android.arch.paging.ItemKeyedDataSource
import android.util.Log
import com.joaoibarra.gitapp.api.GitApi
import com.joaoibarra.gitapp.api.model.Item
import io.reactivex.disposables.CompositeDisposable



class RepositoryDataSource (
        private val gitApi: GitApi,
        private val compositeDisposable: CompositeDisposable
): ItemKeyedDataSource<Int, Item>(){
    private var pageNumber = 1

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Item>) {
        createObservable(pageNumber , callback, null)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Item>) {
        createObservable(pageNumber + 1, null, callback)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Item>) {
        createObservable( pageNumber - 1, null, callback)
    }

    override fun getKey(item: Item): Int {
        return item.id
    }



    private fun createObservable(requestedPage: Int,
                                 initialCallback: LoadInitialCallback<Item>?,
                                 callback: LoadCallback<Item>?) {
        pageNumber = requestedPage
        if(requestedPage > 0) {
            compositeDisposable.add(
                    gitApi.searchByQuery(getDataSearch(requestedPage))
                            .subscribe(
                                    { response ->
                                        Log.d("NGVL", "Loading page: $requestedPage")
                                        initialCallback?.onResult(response.items)
                                        callback?.onResult(response.items)
                                    },
                                    { t ->
                                        Log.d("NGVL", "Error loading page: $requestedPage", t)
                                    }
                            )
            );
        }
    }

    private fun getDataSearch(requestedPage: Int): HashMap<String, String>{
        val data = HashMap<String, String>()
        data["q"] = "language:Java"
        data["sort"] = "stars"
        data["page"] = requestedPage.toString()
        return data
    }
}