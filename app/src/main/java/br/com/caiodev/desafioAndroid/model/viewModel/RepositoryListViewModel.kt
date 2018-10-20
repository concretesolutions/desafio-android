package br.com.caiodev.desafioAndroid.model.viewModel

import android.annotation.SuppressLint
import androidx.core.text.HtmlCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.caiodev.desafioAndroid.interfaces.RecyclerViewDataSource
import br.com.caiodev.desafioAndroid.model.RepositoryDataManager
import br.com.caiodev.desafioAndroid.model.RepositoryItemModel
import br.com.caiodev.desafioAndroid.model.RepositoryOwnerModel

class RepositoryListViewModel : ViewModel(), RecyclerViewDataSource<RepositoryItemModel> {

    private val dataManager = RepositoryDataManager()
    private var repoList = mutableListOf<RepositoryItemModel>()
    private var ownerModel = RepositoryOwnerModel()
    val state = MutableLiveData<Int>()

    @SuppressLint("CheckResult")
    fun getRepos(language: String,
        sortBy: String, page: Int, maxResults: Int
    ) {
        dataManager.getRepoList(language,
            sortBy, page, maxResults
        )
            .subscribe({ model ->

                state.postValue(onReposReceived)

                model.content?.let {
                    repoList = it
                }

            }, {
                state.postValue(onReposReceivedError)
            })
    }

    @SuppressLint("CheckResult")
    fun getOwnerData(ownerLogin: String) {
        dataManager.getOwnerData(ownerLogin)
            .subscribe({
                state.postValue(onRepoOwnerDataReceived)

            }, {
                state.postValue(onRepoOwnerDataReceivedError)
            })
    }

    fun getOwnerLogin(): String {
        return ownerModel.login
    }

    override fun getTotalCount(): Int {
        return repoList.size
    }

    override fun getViewType(position: Int): Int {
        return 0
    }

    override fun getItem(position: Int): RepositoryItemModel {
        return repoList[position]
    }

    fun a(): String {
        return HtmlCompat.fromHtml("", HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
    }

    companion object {
        const val onReposReceived = 0
        const val onReposReceivedError = 1
        const val onRepoOwnerDataReceived = 2
        const val onRepoOwnerDataReceivedError = 3
    }
}