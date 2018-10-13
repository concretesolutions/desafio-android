package com.rafaelpereiraramos.desafioAndroid.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.rafaelpereiraramos.desafioAndroid.api.GithubService
import com.rafaelpereiraramos.desafioAndroid.api.RepoSearchResponse
import com.rafaelpereiraramos.desafioAndroid.database.dao.RepoDAO
import com.rafaelpereiraramos.desafioAndroid.database.model.RepoTO
import com.rafaelpereiraramos.desafioAndroid.di.Named
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executor
import javax.inject.Inject

/**
 * Created by Rafael P. Ramos on 13/10/2018.
 */
class RepoRepository @Inject constructor(
        private val service: GithubService,
        private val repoDAO: RepoDAO,
        @Named("diskIOExecutor") private val ioExecutor: Executor)
    : PagedList.BoundaryCallback<RepoTO>() {

    companion object {
        private const val NETWORK_CHUNCK_SIZE = 35
        //private val LOCAL_SNAPSHOT_SIZE = 10
    }

    private lateinit var query: String
    private var lastRequestedPage = 1
    val networkErrors = MutableLiveData<String>()
    private var isRequestInProgress = false

    override fun onZeroItemsLoaded() {
        requestAndSaveData()
    }

    override fun onItemAtEndLoaded(itemAtEnd: RepoTO) {
        requestAndSaveData()
    }

    fun insert(repos: List<RepoTO>, insertFinished: ()-> Unit) {
        ioExecutor.execute {
            repoDAO.insert(repos)
            insertFinished()
        }
    }

    fun search(query: String): DataSource.Factory<Int, RepoTO> = fetchLocalData(query)

    private fun fetchLocalData(query: String): DataSource.Factory<Int, RepoTO> {
        treatWhiteSpace(query)
        return repoDAO.reposByName(this.query)
    }

    private fun treatWhiteSpace(str: String) {
        this.query = "%${str.replace(' ', '%')}%"
    }

    private fun requestAndSaveData() {
        if (isRequestInProgress) return

        isRequestInProgress = true

        service.searchRepos(query, lastRequestedPage, NETWORK_CHUNCK_SIZE).enqueue(
                object : Callback<RepoSearchResponse> {
                    override fun onFailure(call: Call<RepoSearchResponse>, t: Throwable) {
                        networkErrors.postValue(t.message)
                        isRequestInProgress = false
                    }

                    override fun onResponse(call: Call<RepoSearchResponse>,
                                            response: Response<RepoSearchResponse>) {
                        if (response.isSuccessful) {
                            val repos = response.body()?.items ?: emptyList()
                            insert(repos) {
                                lastRequestedPage++
                                isRequestInProgress = false
                            }
                        } else {
                            networkErrors.postValue(response.errorBody()?.string())
                            isRequestInProgress = false
                        }
                    }

                }
        )
    }
}