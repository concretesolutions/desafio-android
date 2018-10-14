package com.rafaelpereiraramos.desafioAndroid.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.rafaelpereiraramos.desafioAndroid.api.GithubService
import com.rafaelpereiraramos.desafioAndroid.database.AppDatabase
import com.rafaelpereiraramos.desafioAndroid.database.dao.PullDAO
import com.rafaelpereiraramos.desafioAndroid.database.model.PullTO
import com.rafaelpereiraramos.desafioAndroid.di.Named
import java.util.concurrent.Executor
import javax.inject.Inject

/**
 * Created by Rafael P. Ramos on 14/10/2018.
 */
class PullRepository @Inject constructor(
        val database: AppDatabase,
        val pullDAO: PullDAO,
        @Named("diskIOExecutor") val ioExecutor: Executor,
        val service: GithubService
) : PagedList.BoundaryCallback<PullTO>() {

    private var ownerName: String? = null
    private var repoName: String? = null
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
            //pullDAO
        }
    }

    fun getPulls(ownerName: String, repoName: String): DataSource.Factory<Int, PullTO> {
        this.ownerName = ownerName
        this.repoName = repoName

        return pullDAO.getPulls()
    }

    private fun requestAndSaveData() {
        if (isRequestInProgress) return

        isRequestInProgress = true


    }
}