package com.rafaelpereiraramos.desafioAndroid.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.rafaelpereiraramos.desafioAndroid.App
import com.rafaelpereiraramos.desafioAndroid.api.GithubService
import com.rafaelpereiraramos.desafioAndroid.database.AppDatabase
import com.rafaelpereiraramos.desafioAndroid.database.dao.PullDAO
import com.rafaelpereiraramos.desafioAndroid.database.model.PullTO
import com.rafaelpereiraramos.desafioAndroid.database.model.RepoTO
import com.rafaelpereiraramos.desafioAndroid.di.Named
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executor
import javax.inject.Inject

/**
 * Created by Rafael P. Ramos on 14/10/2018.
 */
class PullRepository @Inject constructor(
        val database: AppDatabase,
        val pullDAO: PullDAO,
        @Named("diskIOExecutor") val ioExecutor: Executor,
        @Named("networkExecutor") val networkExecutor: Executor,
        val service: GithubService
) : PagedList.BoundaryCallback<PullTO>() {

    private var repoName: String? = null
    private var ownerLogin: String? = null
    private var lastRequestedPage = 1
    val networkErrors = MutableLiveData<String>()
    private var isRequestInProgress = false

    override fun onZeroItemsLoaded() {
        requestAndSaveData()
    }

    override fun onItemAtEndLoaded(itemAtEnd: PullTO) {
        requestAndSaveData()
    }

    fun insert(pulls: List<PullTO>, insertFinished: ()-> Unit) {
        ioExecutor.execute {
            pulls.forEach {it.repoOwnerLogin = ownerLogin; it.repoName = repoName}
            pullDAO.insert(pulls)
            insertFinished()
        }
    }

    fun getPulls(repo: RepoTO): DataSource.Factory<Int, PullTO> {
        return this.getPulls(repo.owner.login, repo.name)
    }

    fun getPulls(ownerLogin: String, repoName: String): DataSource.Factory<Int, PullTO> {
        this.ownerLogin = ownerLogin
        this.repoName = repoName

        return pullDAO.getPulls(ownerLogin, repoName)
    }

    private fun requestAndSaveData() {
        if (isRequestInProgress) return

        isRequestInProgress = true

        networkExecutor.execute {
            service.getPull(ownerLogin!!, repoName!!, lastRequestedPage, App.NETWORK_SNAPSHOT_SIZE)
                    .enqueue(object : Callback<List<PullTO>> {
                        override fun onFailure(call: Call<List<PullTO>>, t: Throwable) {
                            networkErrors.postValue(t.message)
                            t.printStackTrace()
                            isRequestInProgress = false
                        }

                        override fun onResponse(call: Call<List<PullTO>>, response: Response<List<PullTO>>) {
                            if (response.isSuccessful) {
                                val pulls = response.body() ?: emptyList()
                                insert(pulls) {
                                    lastRequestedPage++
                                    isRequestInProgress = false
                                }
                            } else {
                                networkErrors.postValue(response.errorBody()?.string())
                                isRequestInProgress = false
                            }
                        }
                    })
        }
    }
}