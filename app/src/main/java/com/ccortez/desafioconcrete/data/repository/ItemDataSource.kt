package com.ccortez.desafioconcrete.data.repository

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.ccortez.desafioconcrete.data.local.dao.ItemDao
import com.ccortez.desafioconcrete.data.remote.ApiService
import com.ccortez.desafioconcrete.model.ItemEntity
import com.ccortez.desafioconcrete.model.Repositories
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class ItemDataSource @Inject constructor(
    private val apiService: ApiService,
    private val itemDao: ItemDao,
    private val scope: CoroutineScope
) : PageKeyedDataSource<Int, ItemEntity>() {

    private val TAG = "ItemDataSource"

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, ItemEntity>
    ) {
        fetchData(1, params.requestedLoadSize) {
            callback.onResult(it, null, 2)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, ItemEntity>) {
        val page = params.key
        fetchData(page, params.requestedLoadSize) {
            callback.onResult(it,page+1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, ItemEntity>) {
        val page = params.key
        fetchData(page, params.requestedLoadSize) {
            callback.onResult(it,page-1)
        }
    }

    private fun fetchData(page: Int, pageSize: Int, callback: (List<ItemEntity>) -> Unit) {
        scope.launch(getJobErrorHandler()) {
            val response = apiService.fetchItems(page)

            val remoteData = response.body()?.items

            // Check for response validation
            if (response.isSuccessful && remoteData != null) {
                // Save posts into the persistence storage
                itemDao.insertItems(remoteData)
                callback(remoteData)
            } else {
                // Something went wrong!
                postError(response.errorBody().toString())
            }
        }
    }

    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, e ->
        postError(e.toString())
    }

    private fun postError(message: String) {
        Log.e(TAG, "An error happened: $message")
    }
}